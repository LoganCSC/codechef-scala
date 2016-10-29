/** Copyright by Barry G. Becker, 2016. Licensed under MIT License: http://www.opensource.org/licenses/MIT  */
package common.search

/**
  * Link node for a state in the global search space.
  * Contains an immutable state and a transition that got us to this state from the last one.
  * The estimated future cost is used for sorting nodes.
  * S is the state type
  * T is the transition from one state to the next.
  *
  * Represents a state and how we got to it from the last state. Immutable
  *
  * @param state               the current state state
  * @param transition          the transformation that got to this state
  * @param previous                the previous state
  * @param pathCost            cost from initial position to the state represented by this node
  * @param estimatedFutureCost the cost of getting here plus the estimated future cost to get to the finish.
  *           IOW, an estimate of the total eventual cost of the path from start to goal running through this node
  *
  * @author Barry Becker
  */
class Node[S, T](val state: S, val transition: Option[T], var previous: Option[Node[S, T]], val pathCost: Int,
                 val estimatedFutureCost: Int) extends Comparable[Node[S, T]] {

  def this(state: S, transition: Option[T], prev: Option[Node[S, T]]) {
    this(state, transition, prev, 0, 1)
  }

  def this(state: S) {
    this(state, None, None, 0, 1)
  }

  /**
    * Use this only when there is no transition to this node.
    *
    * @param initialState        initial state
    * @param estimatedFutureCost the cost of getting here plus the estimated future cost to get to the finish.
    */
  def this(initialState: S, estimatedFutureCost: Int) {
    this(initialState, null, null, 0, estimatedFutureCost)
  }

  /** @return state in the global search space */
  def getState: S = state

  def getPathCost: Int = pathCost

  /**
    * @return An estimate of how much it will cost to go from this state to the goal state
    */
  def getEstimatedFutureCost: Int = estimatedFutureCost

  def getPrevious: Option[Node[S, T]] = previous

  def setPrevious(p: Node[S, T]) {
    previous = Some(p)
  }

  def compareTo(otherNode: Node[S, T]): Int = getEstimatedFutureCost - otherNode.getEstimatedFutureCost

  override def equals(other: Any): Boolean = {
    if (this.equals(other)) return true
    if (other == null || (getClass ne other.getClass)) return false
    val node: Node[_, _] = other.asInstanceOf[Node[_, _]]
    state == node.state
  }

  override def hashCode: Int = state.hashCode

  /**
    * @return a list of nodes from the start state to this state.
    */
  def asTransitionList: Seq[T] = {
    var solution: List[T] = List()
    var node: Node[S, T] = this
    while (node.transition.isDefined) {
      solution = node.transition.get +: solution
      node = node.previous.get
    }
    solution
  }

  override def toString: String = "[" + state + ", pathCost=" + pathCost + " totalCost=" + estimatedFutureCost + "]"
}
