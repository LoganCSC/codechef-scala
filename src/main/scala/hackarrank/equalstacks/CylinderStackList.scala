package hackarrank.equalstacks

import scala.collection.mutable.ListBuffer


/**
  * Represents N stacks of cylinders where each cylinder has the same diameter, but they may vary in height.
  * You can change the height of a stack by removing and discarding its topmost cylinder any number of times.
  */
class CylinderStackList(cylinderHeights: Seq[Array[Int]])  {

  val stackList = cylinderHeights.map(_.to[ListBuffer]).toList

  /** Remove blocks from the tallest stacks until all stacks are equal height */
  def removeBlocksUntilEqual() = {
    while (!allHeightsEqual()) {
      removeBlocksFromTallestStacks()
    }
  }

  def allHeightsEqual() = stackList.map(_.sum).toSet.size == 1

  /** remove the block(s) from the tallest stack(s). There could be more than one tallest. */
  def removeBlocksFromTallestStacks(): Unit = {
    val maxHt = getMaxHeight
    stackList.filter(_.sum == maxHt).foreach(_.remove(0))
  }

  def getMaxHeight: Int = stackList.map(_.sum).max
}
