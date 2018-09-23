package codeforces.igorandwaytowork

import scala.collection.mutable

class BfsSolver extends Solver {

  def isSolvable(map: CityMap): Boolean = {

    def positionOrder(p: Position) = -p.numTurns
    val queue = mutable.PriorityQueue[Position]()(Ordering.by(positionOrder))
    queue.enqueue(map.startingPositions(): _*)

    while (queue.nonEmpty) {
      val currentPos = queue.dequeue()
      if (map.isOffice(currentPos.location)) return true
      val nbrs = map.neighborsOf(currentPos).filter(p => p.numTurns <= 2)
      queue.enqueue(nbrs :_*)
    }
    false
  }
}