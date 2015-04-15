package easy.chefluck

/**
 * http://www.codechef.com/problems/CHEFLUCK
 */
object Main {
  def main(args: Array[String]) {
    val testCount = readInt()
    for (i <- 0 until testCount) {
      println(count4(readInt()))
    }
  }

  def count4(n: Int): Int = {
    var i = 0
    while (n - (4 * i) >= 0) {
      val c4 = n - (4 * i)
      if (c4 % 7 == 0)
        return c4
      i += 1
    }

    -1
  }
}