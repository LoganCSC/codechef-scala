package hackarrank.triplesum

import scala.io.StdIn

/**
  * Given an array of N integers, each having a value between 1 and N, inclusive, but not necessarily distinct,
  * Return true if there are 3 numbers from that list that sum to N, otherwise return false.
  */
object Solution extends App {

  val numTestCases = StdIn.readLine().toInt
  for (i <- 0 until numTestCases) {
    StdIn.readLine() // ignore
    val array: Array[Int] = StdIn.readLine().split(" ").map(_.toInt)
    println(TripleSum(array).exists)
  }
}
