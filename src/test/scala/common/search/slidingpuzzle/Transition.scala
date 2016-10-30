package common.search.slidingpuzzle

import common.Location


/**
  * Move from one board state to another by shifting 1 tile to the space position. Immutable.
 *
  * @author Barry Becker
  */
class Transition(val spacePosition: Location, val tilePosition: Location) {

  override def toString: String = "from " + tilePosition + " to " + spacePosition
}
