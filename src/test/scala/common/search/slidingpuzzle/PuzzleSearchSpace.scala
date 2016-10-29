package common.search.slidingpuzzle

import common.search.AbstractSearchSpace

/**
  * @author Barry Becker
  */
class PuzzleSearchSpace(override val initialState: Board) extends AbstractSearchSpace[Board, Transition](initialState) {
  def isGoal(state: Board): Boolean = state.hamming == 0

  def legalTransitions(state: Board): List[Transition] = state.getNeighborTransitions

  def transition(state: Board, transition: Transition): Board = state.applyTransition(transition)

  def distanceFromGoal(state: Board): Int = state.manhattan

  override def getCost(transition: Transition): Int = 1
}
