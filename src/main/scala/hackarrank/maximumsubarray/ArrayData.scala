package hackarrank.maximumsubarray

/**
  * @param data An array of integers to be processed
  */
class ArrayData(data: Array[Int]) {
  val maxValue = data.max

  def largestContiguousSubArraySum(): Int = {
    var pos = 0
    var maxSubSum = 0
    var sum = 0

    while (pos < data.length) {
      sum += data(pos)
      if (sum > maxSubSum) maxSubSum = sum
      if (sum < 0) sum = 0
      pos += 1
    }
    if (maxSubSum == 0) maxValue else maxSubSum
  }

  def largestSubArraySum(): Int = {
    val lsum = data.filter(_ > 0).sum
    if (lsum == 0) maxValue else lsum
  }
}
