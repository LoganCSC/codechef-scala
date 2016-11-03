package hackarrank.castleonthegrid2

import common.Location
import Grid._

import scala.collection.mutable

object Grid {
  val EMPTY = 0
  val BLOCKED = -9
  // the 4 compass directions E, W, S, N
  val DIRECTIONS = List(new Location(1, 0), new Location(-1, 0), new Location(0, 1), new Location(0, -1))
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
      if (nextNode.location == goal)
        return nextNode.step
      else {
        floodFrom(nextNode)
      }
    }
    -1
  }

  def floodFrom(node: Node) = {
    val newStep = node.step + 1
    DIRECTIONS.foreach(dir => {
      var currentPos = node.location
      while (isDirectionOpen(currentPos, dir)) {
        currentPos = new Location(currentPos.row + dir.row, currentPos.col + dir.col)
        setValue(currentPos, newStep)
        queue.enqueue(new Node(currentPos, newStep))
      }
    })
  }

  def isDirectionOpen(currentLoc: Location, dir: Location): Boolean = {
    val r = currentLoc.row + dir.row
    val c = currentLoc.col + dir.col
    if (inBounds(r, c)) {
      val value = data(r)(c)
      value == EMPTY
    } else false
  }

  def inBounds(r: Int, c: Int) = r >= 0 && r < size && c >= 0 && c < size
  def getValue(loc: Location) = data(loc.row)(loc.col)
  def setValue(loc: Location, value: Int) = { data(loc.row)(loc.col) = value }
}
