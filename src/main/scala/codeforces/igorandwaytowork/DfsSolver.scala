package codeforces.igorandwaytowork

import codeforces.igorandwaytowork.CityMap.Location


class DfsSolver extends Solver {

  def isSolvable(map: CityMap): Boolean = {

    var stack = List[Position]()
    map.startingPositions().foreach(p => stack = p :: stack)
    var visited = Set[Location](map.home)

    while (stack.nonEmpty) {
      val currentPos = stack.head
      stack = stack.tail
      visited += currentPos.location
      if (map.isOffice(currentPos.location)) return true
      val nbrs = map.neighborsOf(currentPos).filter(p => !visited.contains(p.location) && p.numTurns <= 2)
      nbrs.foreach(p => stack = p :: stack)
    }
    false
  }
}