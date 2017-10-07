package usaco.subseqsummingtosevens

import scala.io.StdIn

/**
  * http://usaco.org/index.php?page=viewproblem2&cpid=595
  * Save each entry mod 7 to avoid averflows when summing subsequences.
  */
object Solution {

  def main(args: Array[String]) {
    val num = StdIn.readInt() // num list elements
    val list = Array.ofDim[Int](num)
    for (i <- 0 until num)
      list(i) = StdIn.readInt() % 7
    val length = new SubSequenceFinder(7).findLongestSubSequence(list)

    println(length)
  }
}