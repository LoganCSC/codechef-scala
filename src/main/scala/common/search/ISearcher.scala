/** Copyright by Barry G. Becker, 2016. Licensed under MIT License: http://www.opensource.org/licenses/MIT  */
package common.search

/**
  * Run two AStar searches simultaneously.
  * One searches from the start to the goal state, while
  * the other searches from the goal to the start state.
  * If ever one reaches a state in the visited list of the other, we are done.
  *
  * @author Barry Becker
  */
trait ISearcher[S, T] {

  /**  @return a sequence of transitions leading from the initial state to the goal state. */
  def solve: Seq[T]

  /** @return the solution - null until it is found */
  def getSolution: Seq[T]

  /** Tell the search to stop */
  def stop()
}




