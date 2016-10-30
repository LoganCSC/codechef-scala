package hackarrank.castleonthegrid

import common.search.{AStarSearch, HeapPriorityQueue, SearchSpace, UpdatablePriorityQueue}

/** Employs A* search strategy to find optimal number of steps. Probably overkill. */
class AStarCastleSolver(var startState: Board,
                        val queue: UpdatablePriorityQueue[Board, Transition] = new HeapPriorityQueue[Board, Transition]) {

  solveAssumingSolvable(startState, queue)
  private var solutionTransitions: Option[Seq[Transition]] = _

  /** this is faster and simpler if we know its solvable */
  private def solveAssumingSolvable(initial: Board, queue: UpdatablePriorityQueue[Board, Transition]) {
    val space: SearchSpace[Board, Transition] = new CastleSearchSpace(initial)
    val searcher: AStarSearch[Board, Transition] = new AStarSearch[Board, Transition](space, queue)
    searcher.solve
    solutionTransitions = searcher.getSolution
  }

  /** @return true if the initial board is solvable */
  def isSolvable: Boolean = solutionTransitions.isDefined

  /** @return min number of moves to solve initial board; -1 if unsolvable */
  def moves: Int = if (solutionTransitions.isDefined) solutionTransitions.get.size else -1

  /** @return sequence of boards in a shortest solutionTransitions; null if unsolvable */
  def solution: Iterable[Board] = {
    if (!isSolvable) return null
    var list: List[Board] = Nil
    list +:= startState
    var previous: Board = startState

    for (trans <- solutionTransitions.get) {
      val newState: Board = previous.applyTransition(trans)
      list +:= newState
      previous = newState
    }
    list.reverse
  }
}