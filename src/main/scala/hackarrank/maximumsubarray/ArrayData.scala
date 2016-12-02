package hackarrank.maximumsubarray

import scala.annotation.tailrec

/**
  * @param data An array of integers to be processed
  */
class ArrayData(data: Array[Int]) {

  def largestContiguousSubArraySum(): Int = {
    @tailrec
    def loopSum(n: Int, currentSum: Int, currentMax: Int): Int = {
      if (n == 0) currentMax
      else {
        val c =  data(data.length - n)
        if (currentSum + c < 0) loopSum(n - 1, 0, currentMax)
        else loopSum(n - 1, currentSum + c, Math.max(currentSum + c, currentMax))
      }
    }
    retValue(loopSum(data.length, 0, 0))
  }

  def largestSubArraySum(): Int = retValue(data.filter(_ > 0).sum)

  def retValue(maxSubSum: Int) = if (maxSubSum == 0) data.max else maxSubSum
}
