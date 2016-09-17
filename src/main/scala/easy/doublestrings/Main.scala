package easy.doublestrings

import scala.io.StdIn

/**
 * http://www.codechef.com/problems/DOUBLE
 */
object Main {
  def main(args: Array[String]) {
    val num = StdIn.readInt()
    for (i <- 0 until num) {
      val palindromeSize = StdIn.readInt()
      val result = if (palindromeSize % 2 == 0) palindromeSize else palindromeSize - 1
      println(result)
    }
  }
}