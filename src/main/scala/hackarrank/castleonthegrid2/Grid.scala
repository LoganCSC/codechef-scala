package hackarrank.castleonthegrid2

import common.Location
import Grid._
import scala.collection.mutable

object Grid {
  val EMPTY = 0
  val BLOCKED: Int = -9
  // the 4 compass directions E, W, S, N
  val DIRECTIONS = List(Location(1, 0), Location(-1, 0), Location(0, 1), Location(0, -1))
}

/** Immutable. The grid does not change at all, only a current position within it, which is maintained separately */
class Grid(val start: Location, val goal: Location, elements: Array[Array[Char]]) {

  val size =  elements.length
  val data: Array[Array[Int]] = elements.map(row =>row.map{
    case '.' => 0
    case 'X' => BLOCKED
    case bad: Char => throw new IllegalArgumentException("bad char =" + bad)
  })

  class Node(val location: Location, val step: Integer) {
    override def toString: String = "loc=" + location + " step=" + step
  }

  val queue = new mutable.Queue[Node]()
  queue.enqueue(new Node(start, 0))


  /** @return the minimum number of moves to get from start to finish. return -1 if no solution */
  def findMinimumMoves: Int = {
    while (queue.nonEmpty) {
      val nextNode = queue.dequeue()
      if (nextNode.location == goal) return nextNode.step
      else floodFrom(nextNode)
    }
    -1
  }

  def floodFrom(node: Node): Unit = {
    val newStep = node.step + 1
    DIRECTIONS.foreach(dir => {
      var currentPos = node.location
      while (isDirectionOpen(currentPos, dir, newStep)) {
        currentPos = Location(currentPos.row + dir.row, currentPos.col + dir.col)
        setValue(currentPos, newStep)
        queue.enqueue(new Node(currentPos, newStep))
      }
    })
  }

  def isDirectionOpen(currentLoc: Location, dir: Location, newStep: Int): Boolean = {
    val newLoc = Location(currentLoc.row + dir.row, currentLoc.col + dir.col)
    if (inBounds(newLoc)) {
      val value = getValue(newLoc)
      value == EMPTY || value >= newStep
    } else false
  }

  def inBounds(loc: Location): Boolean =
    loc.row >= 0 && loc.row < size && loc.col >= 0 && loc.col < size

  def getValue(loc: Location): Int = data(loc.row)(loc.col)
  def setValue(loc: Location, value: Int) = { data(loc.row)(loc.col) = value }
  override def toString = { data.map(_.mkString(",")).mkString("\n") }
}
