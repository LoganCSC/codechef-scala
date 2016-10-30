package hackarrank.castleonthegrid

import common.geometry.IntLocation


/**
  * Immutable
  * The grid does not change at all, only a current position within it, which is maintained separately
  */
class Grid(val start: IntLocation, val goal: IntLocation, elements: Array[Array[Char]]) {

  val size =  elements.length

  def isOpen(row: Int, col: Int) = elements(row)(col) != 'X'

  def findNumStepsToGoal(startRow: Int, startCol: Int, goalRow: Int, goalCol: Int): Int = {
    3
  }


  /*
  override def equals(other: Any): Boolean = {
    other match {
      case other: Grid => currentPosition == other.currentPosition && Arrays.equals(elements, other.elements)
      case _ => false
    }
  }


  override def hashCode: Int = {
    if (_hashCode < 0) _hashCode = Arrays.hashCode(_blocks)
    _hashCode
  }*/

}
