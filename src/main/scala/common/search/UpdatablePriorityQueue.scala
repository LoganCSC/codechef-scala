/** Copyright by Barry G. Becker, 2016. Licensed under MIT License: http://www.opensource.org/licenses/MIT  */
package common.search

/**
  * @author Barry Becker
  */
trait UpdatablePriorityQueue[S, T] {

  /** @return the node with the lowest priority */
  def pop: Node[S, T]

  /**
    * Find the node with given state, and update its priority
    * If the node is not currently in the heap, it is added.
    *
    * @param node node
    * @return true if the node was found and updated
    */
  def addOrUpdate(node: Node[S, T]): Boolean

  def add(node: Node[S, T]): Boolean

  def size: Int

  def isEmpty: Boolean
}
