package hackarrank.equalstacks.v2

import hackarrank.equalstacks.ICylinderStackList

/**
  * In this approach, find the tallest subset sum height which has the same value for all stacks.
  * Take the sums of all possible heights (from the bottom) and adds them to a map where the key is the
  * height and the value is the count of stacks that have that value.
  * The largest key that has value == number of stacks is the answer.
  *
  * @param cylinderHeights arrays of heights. Each array gives heights from top to bottom in the stack.
  */
class CylinderStackList(cylinderHeights: Seq[Array[Int]]) extends ICylinderStackList {

  def findMaxHeightWhenEqual(): Int = {
    var htMap = Map[Int, Int]() // contains all possible heights for all stacks as keys
    val heightLimit = cylinderHeights.map(_.sum).min
    for (stack <- cylinderHeights) {
      htMap = addHeights(htMap, stack, heightLimit)
    }
    val filtered = htMap.filter(p => p._2 == cylinderHeights.length)
    filtered.keys.max
  }

  /** @return map from sum to number of blocks in the stack */
  private def addHeights(htMap: Map[Int, Int], stack: Array[Int], heightLimit: Int): Map[Int, Int] = {
    var sum = 0
    var map = htMap + (if (htMap.contains(0)) 0 -> (htMap(0) + 1) else 0 -> 1)

    var i = stack.length - 1
    while (i >= 0 && sum < heightLimit) {
      sum += stack(i)
      map += sum -> (if (map.contains(sum)) map(sum) + 1 else 1)
      i -= 1
    }
    map
  }

}
