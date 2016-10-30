package hackarrank.castleonthegrid

import common.geometry.Location


/**
  * Move from one board state to another by moving the current position as if it were a rook.
  * @author Barry Becker
  */
class Transition(val oldPosition: Location, val newPosition: Location) {

  override def toString: String = "from " + oldPosition + " to " + newPosition
}
