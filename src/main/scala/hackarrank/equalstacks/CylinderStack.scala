package hackarrank.equalstacks

import scala.collection.mutable.ListBuffer

/**
  * Represents a stack of cylinders. Maintains current height so it does not need to be recomputed.
  */
class CylinderStack(cylinderHeights: Array[Int])  {

  private val stack = cylinderHeights.to[ListBuffer]
  private var currentHeight = stack.sum

  /** remove the top cylinder from the top of the stack. */
  def removeTop() = currentHeight -= stack.remove(0)

  def getHeight = currentHeight
}
