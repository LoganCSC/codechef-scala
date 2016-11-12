package hackarrank.maximumsubarray

/**
  * An array of integers to be processed
  */
class ArrayData(data: Array[Int]) {
  val maxValue = data.max

  def largestContiguousSubArray(): Int = {
    var maxSubSum = 0
    var sum = 0
    var start = 0

    while (start < data.length) {
      var i = start
      sum = 0
      while (i < data.length) {
        sum += data(i)
        if (sum > maxSubSum) maxSubSum = sum
        i += 1
      }
      start += 1
    }
    if (maxSubSum == 0) maxValue else maxSubSum
  }

  def largestSubArray(): Int = {
    val lsum = data.filter(_ > 0).sum
    if (lsum == 0) maxValue else lsum
  }
}
