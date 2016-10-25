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
      localBest = shortenFromLeftByOne(localBest)
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

  private def shortenFromLeftByOne(best: Int): Int = {
    var localBest = best
    //println("shorten " + startPosition + " " + endPosition)
    if (startPosition < endPosition) {
      val firstHt = heights(startPosition)
      removeHeightFromCountMap(firstHt)
      startPosition += 1
      localBest = (endPosition - startPosition + 1) * currentMinHeight
      localBest = shortenFromRight(localBest)
      //println("shortenFromLeft currMinHt = " + currentMinHeight)
    } else if (startPosition < heights.length - 1) {
      startPosition += 1
      endPosition += 1
      //println("moving both forward : " + startPosition + " " + endPosition)
      heightCountMap -= currentMinHeight
      currentMinHeight = heights(startPosition)
      heightCountMap = heightCountMap + (currentMinHeight -> 1)
      localBest = (endPosition - startPosition + 1) * currentMinHeight
    }
    localBest
  }

  private def shortenFromRight(localBest: Int) = {
    var newBest = localBest
    while (oneShorterIsBetter(localBest)) {
      removeHeightFromCountMap(heights(endPosition))
      endPosition -= 1
      newBest = (endPosition - startPosition + 1) * currentMinHeight
    }
    newBest
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


  private def oneShorterIsBetter(currentBest: Int): Boolean = {
    if (startPosition < endPosition) {
      val len = endPosition - startPosition
      println("minHt="+ currentMinHeight)
      val newMinHt = if (currentMinHeight == heights(endPosition) && heightCountMap(currentMinHeight) == 1)
        heightCountMap.keys.toList.sorted.apply(1) else currentMinHeight
      val newArea = len * newMinHt
      //println("newArea = " + newArea + " curBest = " + currentBest)
      newArea > currentBest
    }
    else false
  }

  private def shortenByOne() = {
    val htAtEnd = heights(endPosition)
    if (heightCountMap(htAtEnd) == 1) {
      heightCountMap = heightCountMap - htAtEnd
      if (htAtEnd == currentMinHeight)
        heightCountMap.keys.min
    }
    else heightCountMap = heightCountMap + (htAtEnd -> (heightCountMap(htAtEnd) - 1))
    endPosition -= 1
    (endPosition - startPosition + 1) * currentMinHeight
  }

}