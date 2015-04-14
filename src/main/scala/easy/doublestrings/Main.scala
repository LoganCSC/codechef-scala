package easy.doublestrings

/**
 * http://www.codechef.com/problems/DOUBLE
 */
object Main {
  def main(args: Array[String]) {
    val testCount = readInt()
    for (i <- 0 until testCount) {
      val palindromeSize = readInt()
      println(if (palindromeSize % 2 == 0) palindromeSize else palindromeSize - 1)
    }
  }
}