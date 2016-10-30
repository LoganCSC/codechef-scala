package common.search.slidingpuzzle

/**
  * Node for a state in the global search space.
  * A search node is a board, the number of moves made to reach the board, and the previous search node.
  * @param state    the current state state
  * @param previous     the previous state
  * @param numSteps cost from initial position to the state represented by this node.
  * @author Barry Becker
  */
//@Immutable
class Node(val state: Board, var previous: Node, val numSteps: Int) extends Comparable[Node] {

  def this(state: Board, prev: Node) {
    this(state, prev, 0)
  }

  /**
    * Use this only when there is no transition to this node.
    *
    * @param initialState initial state
    * @param pathCost     cost from initial position to the state represented by this node.
    */
  def this(initialState: Board, pathCost: Int) {
    this(initialState, null, pathCost)
  }

  /** @return state in the global search space */
  def getState: Board = state

  def getPreviousBoard: Board = if (previous == null) null
  else previous.getState

  /** @return number of steps to get from the initial state to the current one */
  def getNumSteps: Int = numSteps

  def getPriority: Int = getNumSteps + state._manhattan // * MULTIPLIER + state.hamming();

  override def toString: String = "[" + state + ", numSteps=" + numSteps + "]"

  def compareTo(n: Node): Int = this.getPriority - n.getPriority
}
