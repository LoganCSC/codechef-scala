package hackarrank.maximumsubarray

/**
  * @param data An array of integers to be processed
  */
class ArrayData(data: Array[Int]) {

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
    retValue(maxSubSum)
  }

  def largestSubArraySum(): Int = {
    retValue(data.filter(_ > 0).sum)
  }

  def retValue(maxSubSum: Int) = if (maxSubSum == 0) data.max else maxSubSum
}
