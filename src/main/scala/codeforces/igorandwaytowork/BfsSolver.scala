package codeforces.igorandwaytowork

import codeforces.igorandwaytowork.CityMap.Location
import scala.collection.mutable

class BfsSolver extends Solver {

  def isSolvable(map: CityMap): Boolean = {

    def positionOrder(p: Position) = -p.numTurns
    val queue = mutable.PriorityQueue[Position]()(Ordering.by(positionOrder))
    queue.enqueue(map.startingPositions(): _*)
    var visited = Set[Location](map.home)

    while (queue.nonEmpty) {
      val currentPos = queue.dequeue()
      visited += currentPos.location
      if (map.isOffice(currentPos.location)) return true
      val nbrs = map.neighborsOf(currentPos).filter(p => !visited.contains(p.location) && p.numTurns <= 2)
      queue.enqueue(nbrs :_*)
    }
    false
  }
}