package codeforces.igorandwaytowork

import CityMap._

object CityMap {
  val ROAD_WORK = '*'
  val HOME = 'S'
  val OFFICE = 'T'

  val UP = "up"
  val DOWN = "down"
  val LEFT = "left"
  val RIGHT = "right"
  val DIRECTIONS = Array(UP, DOWN, LEFT, RIGHT)

  type Location = Tuple2[Int, Int]
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

  val home: Location = findLocation(HOME)
  val office: Location = findLocation(OFFICE)

  def isOffice(loc: Location): Boolean = loc == office

  /** @return the (up to) 4 neighbors from the specified position */
  def neighborsOf(pos: Position): Seq[Position] = {
    var nbrs =  List[Position]()
    val loc = pos.location
    val turns = pos.numTurns
    for (dir <- DIRECTIONS) {
      dir match {
        case UP =>
          if (isOpen(loc, -1, 0) && pos.direction != DOWN) {
            val newPos = Position((loc._1 - 1, loc._2), UP, if (pos.direction == UP) turns else turns + 1)
            if (newPos.numTurns < 2 || rowOrColMatchesOffice(newPos.location))
              nbrs +:= newPos
          }
        case DOWN =>
          if (isOpen(loc, 1, 0) && pos.direction != UP) {
            val newPos = Position((loc._1 + 1, loc._2), DOWN, if (pos.direction == DOWN) turns else turns + 1)
            if (newPos.numTurns < 2 || rowOrColMatchesOffice(newPos.location))
              nbrs +:= newPos
          }
        case LEFT =>
          if (isOpen(loc, 0, -1) && pos.direction != RIGHT) {
            val newPos = Position((loc._1, loc._2 - 1), LEFT, if (pos.direction == LEFT) turns else turns + 1)
            if (newPos.numTurns < 2 || rowOrColMatchesOffice(newPos.location))
              nbrs +:= newPos
          }

        case RIGHT =>
          if (isOpen(loc, 0, 1) && pos.direction != LEFT) {
            val newPos = Position((loc._1, loc._2 + 1), RIGHT, if (pos.direction == RIGHT) turns else turns + 1)
            if (newPos.numTurns < 2 || rowOrColMatchesOffice(newPos.location))
              nbrs +:= newPos
          }
      }
    }
    nbrs
  }

  def startingPositions(): Seq[Position] = neighborsOf(Position(home, "no direction", -1))

  private def rowOrColMatchesOffice(loc: Location): Boolean = loc._1 == office._1 || loc._2 == office._2

  private def isOpen(loc: Location, rowOffset: Int, columnOffset: Int): Boolean = {
    val newLoc: Location = (loc._1 + rowOffset, loc._2 + columnOffset)
    isInGrid(newLoc) && lines(newLoc._1)(newLoc._2) != ROAD_WORK
  }

  private def isInGrid(loc: Location): Boolean =
    loc._1 >= 0 && loc._1 < lines.length && loc._2 >= 0 && loc._2 < lines.head.length

  private def findLocation(c: Char): Location = {
    for (row <- lines.indices) {
      val col = lines(row).indexOf(c)
      if (col >= 0) return (row, col)
    }
    throw new IllegalArgumentException(s"Could not find $c in ${lines.mkString("\n")}")
  }
}