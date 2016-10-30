package hackarrank.castleonthegrid

import common.geometry.Location


/**
  * Move from one board state to another by moving the current position as if it were a rook.
  * @author Barry Becker
  */
class Transition(val newPosition: Location) {

  override def toString: String = " to " + newPosition
}
