package hackarrank.equalstacks

/**
  * Represents N stacks of cylinders where each cylinder has the same diameter, but they may vary in height.
  */
class CylinderStackList(cylinderHeights: Seq[Array[Int]])  {

  private val stackList = cylinderHeights.map(new CylinderStack(_)).toList

  /** Remove blocks from the tallest stacks until all stacks are equal height */
  def removeBlocksUntilEqual(): Unit = while (!allHeightsEqual()) removeBlocksFromTallestStacks()

  def allHeightsEqual(): Boolean = stackList.map(_.getHeight).toSet.size == 1

  /** remove the block(s) from the tallest stack(s). There could be more than one tallest. */
  def removeBlocksFromTallestStacks(): Unit = {
    val maxHt = getMaxHeight
    stackList.filter(_.getHeight == maxHt).foreach(_.removeTop())
  }

  def getMaxHeight: Int = stackList.map(_.getHeight).max
}
