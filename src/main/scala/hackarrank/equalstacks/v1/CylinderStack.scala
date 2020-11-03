package hackarrank.equalstacks.v1

import scala.collection.mutable.ListBuffer

/**
  * Represents a stack of cylinders. Maintains current height so it does not need to be recomputed.
  * @param cylinderHeights the heights of the cylinders in the stack. The first is on top and the last is on the bottom,
  */
class CylinderStack(cylinderHeights: Array[Int])  {

  private val stack = cylinderHeights.to[ListBuffer]
  private var currentHeight = stack.sum

  /** remove the top cylinder from the top of the stack and update the height. */
  def removeTop(): Unit = currentHeight -= stack.remove(0)

  def getHeight: Int = currentHeight
}
