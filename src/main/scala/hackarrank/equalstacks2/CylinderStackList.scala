package hackarrank.equalstacks2

/**
  * In this approach, find the tallest subset sum height which has the same value for all stacks.
  * Take the sums of all possible heights (from the bottom) and add them to a map where the key is the
  * height and the value is the count of stacks that have that value.
  * The largest key that has value == number of stacks is the answer.
  * @param cylinderHeights arrays of heights. Each array gives heights from top to bottom in the stack.
  */
class CylinderStackList(cylinderHeights: Seq[Array[Int]]) {

  def heightAfterRemovingTallestUntilEqual(): Int = {

    var htMap = Map[Int, Int]()
    val heightLimit = cylinderHeights.map(_.sum).min
    for (a <- cylinderHeights) {
      htMap = addHeights(htMap, a, heightLimit)
    }
    val filtered = htMap.filter(p => p._2 == cylinderHeights.length)
    filtered.keys.max
  }

  /** @return map from sum to number of blocks in the stack */
  private def addHeights(htMap: Map[Int, Int], a: Array[Int], heightLimit: Int): Map[Int, Int] = {
    var sum = 0
    var map = if (htMap.contains(0)) htMap + (0 -> (htMap(0) + 1)) else htMap + (0 -> 1)

    var i = a.length - 1
    while (i >= 0 && sum < heightLimit) {
      sum += a(i)
      if (map.contains(sum)) {
        map += (sum -> (map(sum) + 1))
      } else {
        map += (sum -> 1)
      }
      i -= 1
    }
    map
  }

  private def findAllHeights(a: Array[Int]): Set[Int] = {
    var sum = 0
    (for (i <- a.indices) yield { sum += a(i); sum }).toSet
  }
}
