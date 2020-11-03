package hackarrank.equalstacks.v3

import hackarrank.equalstacks.ICylinderStackList

/**
  * Represents N stacks of cylinders where each cylinder has the same diameter, but they may vary in height.
  */
class CylinderStackList(cylinderHeights: Seq[Array[Int]]) extends ICylinderStackList {

  private val stackList = cylinderHeights.map(new CylinderStack(_)).toList

  /** Remove blocks from the tallest stacks until all stacks are equal height */
  def findMaxHeightWhenEqual(): Int = {
    var count = 0
    var i = 0
    var maxCandidateHeight = minStackHeight()

    while (count != stackList.size) {
      val stack = stackList(i)
      while (stack.getHeight > maxCandidateHeight) {
        stack.removeTop()
      }
      if (stack.getHeight == maxCandidateHeight) {
        count += 1
      } else {
        maxCandidateHeight = stack.getHeight
        count = 0
      }
      i = (i + 1) % stackList.size
    }
    stackList.head.getHeight
  }

  private def minStackHeight(): Int = {
    stackList.minBy(_.getHeight).getHeight
  }
}
