package hackarrank.castleonthegrid

import common.geometry.IntLocation


/**
  * Immutable
  * The grid does not change at all, only a current position within it, which is maintained separately
  */
class Grid(val start: IntLocation, val goal: IntLocation, elements: Array[Array[Char]]) {

  println("grid size = " + elements.length + " " + elements(0).length)
  val size =  elements.length

  def isOpen(row: Int, col: Int) = elements(row)(col) != 'X'
}
