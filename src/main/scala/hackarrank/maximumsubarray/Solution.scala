package hackarrank.maximumsubarray

import scala.io.StdIn

/**
 * https://www.hackerrank.com/contests/compsci-club7/challenges/maxsubarray
 */
object Solution {

  def main(args: Array[String]) {
    val numTestCases = StdIn.readLine().toInt
    for (i <- 0 until numTestCases) {
      StdIn.readLine() // ignore
      val array: ArrayData = new ArrayData(StdIn.readLine().split(" ").map(_.toInt))
      println(array.largestContiguousSubArraySum() + " " + array.largestSubArraySum)
    }
  }

}