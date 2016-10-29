package common.search.slidingpuzzle

import common.geometry.Location

/**
  * Move from one board state to another by shifting 1 tile to the space position
  *
  * @author Barry Becker
  */
class Transition(var spacePosition: Location, var tilePosition: Location) {

  def getSpacePosition: Location = spacePosition

  def getTilePosition: Location = tilePosition

  override def toString: String = "from " + tilePosition + " to " + spacePosition
}
