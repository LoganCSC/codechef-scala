package easy.chefluck

import scala.io.StdIn

/**
 * http://www.codechef.com/problems/CHEFLUCK
 */
object Main extends App {

  val testCount = StdIn.readInt()
  for (i <- 0 until testCount) {
    println(count4(StdIn.readInt()))
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