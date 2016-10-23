package hackarrank.largestrectangle

/**
  * Created by barry on 10/23/2016.
  */
class Region(heights: Array[Int]) {
  private var heightsMap: Map[Int, Int] = heights.groupBy(identity).mapValues(_.length)
  private val heightList = heightsMap.keys.toList.sorted
  var minHeightIdx = 0
  var startPosition = 0
  var endPosition = heightList.length - 1

  def findMaxArea() = {
    var best = heightList.head * heights.length

    for (i <- heights.indices) {
      // try to improve on best by reducing on the right
      startPosition = i
      while (oneShorterIsBetter(best)) {
        best = shortenByOne()
      }
      while (oneLongerIsBetter(best)) {
        best = lengthenByOne()
      }
      val area = shortenFromLeftByOne()
      if (area > best) best = area
    }
    best
  }

  private def oneShorterIsBetter(currentBest: Int): Boolean = {
    val len = endPosition - startPosition
    val minHt = heightList(minHeightIdx)
    val newMinHt = if (minHt == heights(endPosition) && heightsMap(minHt) == 1)
      heightList(minHeightIdx + 1) else minHt
    (len * newMinHt) < currentBest
  }

  private def shortenByOne() = {
    val htAtEnd = heights(endPosition)
    if (heightsMap(htAtEnd) == 1) {
      heightsMap = heightsMap - htAtEnd
      if (htAtEnd == heightList(minHeightIdx))
        minHeightIdx += 1
    }
    else heightsMap = heightsMap + (htAtEnd -> (heightsMap(htAtEnd) - 1))
    endPosition -= 1
    (endPosition - startPosition + 1) * heightList(minHeightIdx)
  }

  private def oneLongerIsBetter(currentBest: Int): Boolean = {
    if (endPosition < heights.length) {
      val len = endPosition + 1 - startPosition
      val minHt = heightList(minHeightIdx)
      val newMinHt = if (minHt > heights(endPosition + 1))
        heights(endPosition + 1) else minHt
      (len * newMinHt) < currentBest
    }
    else false
  }

  private def lengthenByOne() = {
    endPosition += 1
    val htAtEnd = heights(endPosition)
    if (heightsMap.contains(htAtEnd)) {
      heightsMap = heightsMap + (htAtEnd -> (heightsMap(htAtEnd) + 1))
    }
    else {
      heightsMap + (htAtEnd -> 1)
      if (htAtEnd < heightList(minHeightIdx)) minHeightIdx -= 1
    }
    endPosition -= 1
    (endPosition - startPosition + 1) * heightList(minHeightIdx)
  }

  private def shortenFromLeftByOne(): Int = {
    val firstHt = heights(startPosition)
    if (heightsMap(firstHt) == 1) {
      heightsMap -= firstHt
      if (firstHt == heightList(minHeightIdx)) {
        minHeightIdx += 1
      }
    }
    startPosition += 1
    (endPosition - startPosition + 1) * heightList(minHeightIdx)
  }

}