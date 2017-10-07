package usaco.subseqsummingtosevens

import scala.io.StdIn

/**
  * http://usaco.org/index.php?page=viewproblem2&cpid=595
  */
object Solution {

  def main(args: Array[String]) {
    val num = StdIn.readInt() // num list elements
    val list = Array.ofDim[Int](num)
    for (i <- 0 until num)
      list(i) = StdIn.readInt()
    val length = new SubSequenceFinder(7).findLongestSubSequence(list)

    println(length)
  }
}