package common.search

import scala.collection.mutable

/**
  * Describes the search space.
  *
  * @author Barry Becker
  */
abstract class AbstractSearchSpace[S, T](var initialState: S) extends SearchSpace[S, T] {

  def alreadySeen(state: S, seen: mutable.Set[S]): Boolean = {
    if (!seen.contains(state)) {
      seen.add(state)
      return false
    }
    true
  }

  def getCost(transition: T): Int = 1

  def refresh(state: S, numTries: Long) {
  }

  def finalRefresh(path: Seq[T], state: S, numTries: Long, elapsedMillis: Long) {
    // nothing be default
  }
}
