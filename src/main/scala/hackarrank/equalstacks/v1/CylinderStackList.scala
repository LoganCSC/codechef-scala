package hackarrank.equalstacks.v1

import hackarrank.equalstacks.ICylinderStackList

/**
  * Represents N stacks of cylinders where each cylinder has the same diameter, but they may vary in height.
  */
class CylinderStackList(cylinderHeights: Seq[Array[Int]]) extends ICylinderStackList {

  private val stackList = cylinderHeights.map(new CylinderStack(_)).toList

  override def findMaxHeightWhenEqual(): Int = {
    removeBlocksUntilEqual()
    getMaxHeight
  }

  /** Remove blocks from the tallest stacks until all stacks are equal height */
  private def removeBlocksUntilEqual(): Unit = while (!allHeightsEqual()) removeBlocksFromTallestStacks()

  private def allHeightsEqual(): Boolean = stackList.map(_.getHeight).toSet.size == 1

  /** remove the block(s) from the tallest stack(s). There could be more than one tallest. */
  private def removeBlocksFromTallestStacks(): Unit = {
    val maxHt = getMaxHeight
    stackList.filter(_.getHeight == maxHt).foreach(_.removeTop())
  }

  private def getMaxHeight: Int = stackList.map(_.getHeight).max
}
