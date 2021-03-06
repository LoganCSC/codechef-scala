package hackarrank.castleonthegrid

import common.Location


/** Immutable. The grid does not change at all, only a current position within it, which is maintained separately */
class Grid(val start: Location, val goal: Location, elements: Array[Array[Char]]) {

  val size: Int =  elements.length
  def isOpen(row: Int, col: Int): Boolean = elements(row)(col) != 'X'
}
