package codeforces.igorandwaytowork

import CityMap._

object CityMap {
  val EMPTY = '.'
  val ROAD_WORK = '*'
  val HOME = 'S'
  val OFFICE = 'T'

  val UP = "up"
  val DOWN = "down"
  val LEFT = "left"
  val RIGHT = "right"
  val DIRECTIONS = Array(UP, DOWN, LEFT, RIGHT)

  type Location = Tuple2[Int, Int]

  def findLocation(c: Char, lines: Seq[String]): Location = {
    for (row <- lines.indices) {
      val col = lines(row).indexOf(c)
      if (col >= 0) return (row, col)
    }
    throw new IllegalArgumentException(s"Could not find $c in ${lines.mkString("\n")}")
  }
}


case class Position(location: Location, direction: String, numTurns: Int)

/**
  * @param lines A sequence of strings that define the Bankopolis grid/map.
  *              For example, S....
  *                           ****.
  *                           .....
  *                           .****
  *                           ..T..
  */
case class CityMap(lines: Seq[String]) {

  val home: Location = findLocation(HOME, lines)
  val office: Location = findLocation(OFFICE, lines)

  def isOffice(loc: Location): Boolean = loc == office

  def isInGrid(loc: Location): Boolean =
    loc._1 >= 0 && loc._1 < lines.length && loc._2 >= 0 && loc._2 < lines.head.length

  def isOpen(loc: Location, rowOffset: Int, columnOffset: Int): Boolean = {
    val newLoc: Location = (loc._1 + rowOffset, loc._2 + columnOffset)
    isInGrid(newLoc) && lines(newLoc._1)(newLoc._2) != ROAD_WORK
  }

  /** @return the (up to) 4 neighbors from the specified position */
  def neighborsOf(pos: Position): Seq[Position] = {
    var nbrs =  Seq[Position]()
    val loc = pos.location
    val turns = pos.numTurns
    for (dir <- DIRECTIONS) {
      dir match {
        case UP =>
          if (isOpen(loc, -1, 0))
            nbrs :+= Position((loc._1 - 1, loc._2), UP, if (pos.direction == UP) turns else turns + 1)
        case DOWN =>
          if (isOpen(loc, 1, 0))
            nbrs :+= Position((loc._1 + 1, loc._2), DOWN, if (pos.direction == DOWN) turns else turns + 1)
        case LEFT =>
          if (isOpen(loc, 0, -1))
            nbrs :+= Position((loc._1, loc._2 - 1), LEFT, if (pos.direction == LEFT) turns else turns + 1)
        case RIGHT =>
          if (isOpen(loc, 0, 1))
            nbrs :+= Position((loc._1, loc._2 + 1), RIGHT, if (pos.direction == RIGHT) turns else turns + 1)
      }
    }
    nbrs
  }

  def startingPositions(): Seq[Position] = neighborsOf(Position(home, "no direction", -1))
}
