/** Copyright by Barry G. Becker, 2016. Licensed under MIT License: http://www.opensource.org/licenses/MIT  */
package common.search

import scala.collection.mutable

/**
  * Represents the global search space of all states.
  * It must include an initial state and a goal state.
  * The type parameters S and T correspond to a state and a transition from one state to the next.
  * S and T should be immutable and should calculate and cache distance metrics in the constructor if possible.
  *
  * @author Barry Becker
  */
trait SearchSpace[S, T] extends Refreshable[S, T] {

  def initialState: S

  /** @return true if the state is the goal state. */
  def isGoal(state: S): Boolean

  /**
    * Try to produce this list efficiently,
    * and try to sort if by most promising transitions first.
    *
    * @return a list of legal next immutable transitions.
    */
  def legalTransitions(state: S): Seq[T]

  /** @return the state (immutable) that you get after applying the specified transition. */
  def transition(state: S, transition: T): S

  /**
    * Add the state to the seen set of state if not already seen.
    *
    * @param state to check
    * @param seen  Map of seen states.
    * @return true if the specified state was already seen (possibly taking into account symmetries).
    */
  def alreadySeen(state: S, seen: mutable.Set[S]): Boolean

  /**
    * @return estimate of the cost to reach the goal from the specified state.
    */
  def distanceFromGoal(state: S): Int

  /** @return the cost of making a single transition. Usually a constant like 1, but for some scenarios it matters. */
  def getCost(transition: T): Int
}