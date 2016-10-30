package hackarrank.castleonthegrid

import common.search.AbstractSearchSpace


/**
  * @author Barry Becker
  */
class CastleSearchSpace(initialState: Board) extends AbstractSearchSpace[Board, Transition](initialState) {

  def isGoal(state: Board): Boolean = state.isAtGoal

  def legalTransitions(state: Board): List[Transition] = state.getNeighborTransitions

  def transition(state: Board, transition: Transition): Board = state.applyTransition(transition)

  def distanceFromGoal(state: Board): Int = state.estimatedStepsToGoal

  override def getCost(transition: Transition): Int = 1
}
