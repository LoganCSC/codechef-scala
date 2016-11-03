package hackarrank.castleonthegrid

import common.Location


/** Use simplistic best first search approach */
class SimpleCastleSolver(var startState: Board) {

  solveAssumingSolvable(startState)
  var solutionTransitions: Option[Seq[Location]] = _

  /** this is faster and simpler if we know its solvable */
  private def solveAssumingSolvable(initial: Board) = {
    val searcher: SimpleSearch = new SimpleSearch(initial)
    solutionTransitions = searcher.solve
  }

  /** @return true if the initial board is solvable */
  def isSolvable: Boolean = solutionTransitions.isDefined

  /** @return min number of moves to solve initial board; -1 if unsolvable */
  def moves: Int = if (solutionTransitions.isDefined) solutionTransitions.get.size else -1
}