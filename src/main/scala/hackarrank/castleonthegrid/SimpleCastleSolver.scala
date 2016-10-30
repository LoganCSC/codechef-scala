package hackarrank.castleonthegrid

import common.geometry.Location
import common.search.{AStarSearch, HeapPriorityQueue, SearchSpace, UpdatablePriorityQueue}

/** Uses a naive brute force approach */
class SimpleCastleSolver(var startState: Board) {

  solveAssumingSolvable(startState)
  private var solutionTransitions: Option[Seq[Location]] = _

  /** this is faster and simpler if we know its solvable */
  private def solveAssumingSolvable(initial: Board) = {
    val searcher: SimpleSearch = new SimpleSearch(new CastleSearchSpace(initial))
    solutionTransitions = searcher.solve
  }

  /** @return true if the initial board is solvable */
  def isSolvable: Boolean = solutionTransitions.isDefined

  /** @return min number of moves to solve initial board; -1 if unsolvable */
  def moves: Int = if (solutionTransitions.isDefined) solutionTransitions.get.size else -1

  def transitions: Seq[Location] = solutionTransitions.get
}