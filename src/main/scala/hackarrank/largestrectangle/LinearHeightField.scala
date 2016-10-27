package hackarrank.largestrectangle

/**
  * Represents a 1 dimensional height field and a method to find a maximal area for a range with min height.
  */
class LinearHeightField(heights: Array[Int]) {

  var minHeight = heights.min
  var startPosition = 0
  var endPosition = 0
  var best = heights.length * minHeight


  def findMaxArea() = {

    while (startPosition < heights.length) {
      val area = findLargestAreaAt(startPosition)
      if (area > best) best = area
      do  {
        startPosition += 1
      } while (startPosition < heights.length && heights(startPosition) == minHeight)
    }

    best
  }

  /** march outward from pos until best area is found */
  private def findLargestAreaAt(pos: Int) = {
    val currentHt = heights(pos)
    var low = pos
    var high = pos
    while (low > 0 && heights(low - 1) >= currentHt) low -= 1
    while (high < heights.length - 1 && heights(high + 1) >= currentHt) high += 1
    currentHt * (high - low + 1)
  }

}