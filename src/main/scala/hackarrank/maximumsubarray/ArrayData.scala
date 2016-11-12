package hackarrank.maximumsubarray

import scala.collection.mutable.ArrayBuffer

/**
  * An array of integers to be processed
  */
class ArrayData(data: Array[Int]) {
  val cdata = coalesce(data)
  val maxValue = data.max

  def largestContiguousSubArray(): Int = {

    var start = if (cdata(0) <= 0) 1 else 0
    var sum = 0
    var maxSubSum = 0

    while (start < cdata.length) {
      sum += cdata(start)
      val sumNext = sumNext2(start + 1)
      if (sumNext > 0) sum += sumNext
      if (sum > maxSubSum) maxSubSum = sum
      start += 3
    }
    if (maxSubSum == 0) maxValue else maxSubSum
  }

  /** @return the sum of the next two - which are always a negative followed by a positive */
  def sumNext2(idx: Int) = {
    if (idx + 1 < cdata.length)
      cdata(idx) + cdata(idx + 1)
    else if (idx < cdata.length) cdata(idx) else 0
  }

  /** @return the original data coalesced in to alternative positive and negative values */
  def coalesce(d: Array[Int]): Array[Int] = {
    var i = 0
    val cArray = ArrayBuffer[Int]()

    // first add negative run if any
    i = coalesceValue(i, v => v <= 0, cArray)

    while (i < data.length) {
      // alternate between pos and neg runs
      i = coalesceValue(i, v => v > 0, cArray)
      if (i < data.length)
        i = coalesceValue(i, v => v <= 0, cArray)
    }
    cArray.toArray
  }

  def coalesceValue(start: Int, comparisonFunc: (Int) => Boolean, cArray: ArrayBuffer[Int]): Int = {
    var sum = 0
    var i = start
    while (i < data.length && comparisonFunc(data(i))) {
      sum += data(i)
      i += 1
    }
    cArray.append(sum)
    i
  }

  def largestSubArray(): Int = {
    val lsum = cdata.filter(_ > 0).sum
    if (lsum == 0) maxValue else lsum
  }
}
