package hackarrank.largestrectangle

/**
  * Represents a 1 dimensional height field and a method to find a maximal area for a range with min height.
  */
class LinearHeightField(heights: Array[Int]) {
  private var heightCountMap: Map[Int, Int] = Map(heights(0) -> 1)
  var currentMinHeight = heights(0)
  var startPosition = 0
  var endPosition = 0

  def findMaxArea() = {
    var best = currentMinHeight
    var localBest = currentMinHeight

    while (startPosition < heights.length - 1) {
      // try to improve on best by reducing on the right
      while (oneLongerIsBetter(localBest)) {
        localBest = lengthenByOne()
        if (localBest > best) best = localBest
        //println(" new best after extending to [" + startPosition + ", " + endPosition +"] = " + best)
      }
      localBest = shortenFromLeftByOne()
      if (localBest > best) best = localBest
      //println("new best after shortening from left [" + startPosition + ", " + endPosition +"] = " + best)
    }
    best
  }

  private def oneLongerIsBetter(currentBest: Int): Boolean = {
    if (endPosition < heights.length - 1 && startPosition <= endPosition) {
      val len = endPosition - startPosition + 2
      val newMinHt = if (heights(endPosition + 1) < currentMinHeight)
        heights(endPosition + 1) else currentMinHeight
      val newArea = len * newMinHt
      //println("longer newArea = " + newArea + " (" + len +" * " + newMinHt +")    curBest = " + currentBest)
      newArea > currentBest
    }
    else false
  }

  private def lengthenByOne() = {
    endPosition += 1
    val htAtEnd = heights(endPosition)
    addHeightToCountMap(htAtEnd)
    (endPosition - startPosition + 1) * currentMinHeight
  }

  private def shortenFromLeftByOne(): Int = {
    //println("shorten " + startPosition + " " + endPosition)
    if (startPosition < endPosition) {
      val firstHt = heights(startPosition)
      removeHeightFromCountMap(firstHt)
      startPosition += 1
      removeFromRightElementsLessThanOrEqualTo(firstHt)
      //println("shortenFromLeft currMinHt = " + currentMinHeight)
    } else if (startPosition < heights.length - 1) {
      startPosition += 1
      endPosition += 1
      //println("moving both forward : " + startPosition + " " + endPosition)
      heightCountMap -= currentMinHeight
      currentMinHeight = heights(startPosition)
      heightCountMap = heightCountMap + (currentMinHeight -> 1)
    }
    (endPosition - startPosition + 1) * currentMinHeight
  }

  private def removeFromRightElementsLessThanOrEqualTo(height: Int) = {
    while (heights(endPosition) <= height && startPosition < endPosition) {
      removeHeightFromCountMap(heights(endPosition))
      endPosition -= 1
    }
  }

  private def addHeightToCountMap(height: Int) = {
    if (heightCountMap.contains(height)) {
      heightCountMap = heightCountMap + (height -> (heightCountMap(height) + 1))
    }
    else {
      heightCountMap += (height -> 1)
      if (height < currentMinHeight) currentMinHeight = height
    }
  }

  private def removeHeightFromCountMap(height: Int) = {
    println("htMap = " + heightCountMap.toList.mkString(", ") + " about to remove " + height)
    if (heightCountMap(height) == 1) {
      heightCountMap -= height
      if (height == currentMinHeight) {
        currentMinHeight = heightCountMap.keys.min
      }
    } else {
      heightCountMap = heightCountMap + (height -> (heightCountMap(height) - 1))
    }
  }


  /*
  private def oneShorterIsBetter(currentBest: Int): Boolean = {
    if (startPosition < endPosition) {
      val len = endPosition - startPosition
      val minHt = heightList(minHeightIdx)
      println("minHt="+ minHt)
      val newMinHt = if (minHt == heights(endPosition) && heightCountMap(minHt) == 1)
        heightList(minHeightIdx + 1) else minHt
      val newArea = (len * newMinHt)
      println("newArea = " + newArea + " curBest = " + currentBest)
      newArea > currentBest
    }
    else false
  }

  private def shortenByOne() = {
    val htAtEnd = heights(endPosition)
    if (heightCountMap(htAtEnd) == 1) {
      heightCountMap = heightCountMap - htAtEnd
      if (htAtEnd == heightList(minHeightIdx))
        minHeightIdx += 1
    }
    else heightCountMap = heightCountMap + (htAtEnd -> (heightCountMap(htAtEnd) - 1))
    endPosition -= 1
    (endPosition - startPosition + 1) * heightList(minHeightIdx)
  }*/

}