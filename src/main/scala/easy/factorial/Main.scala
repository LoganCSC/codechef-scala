package easy.factorial

import scala.io.StdIn

object Main extends App {

  val testCount = StdIn.readInt()
  for (i <- 0 until testCount) {
    println(countTrailingZeros(StdIn.readInt()))
  }


  def countTrailingZeros(n: Int): Int = {
    var count = 0
    var m = n
    while (m != 0) {
      // First, count all the factors of 5.
      // Next, count all the 5x5.
      // Then, go on counting all the 5x5x5. And so on.
      m /= 5
      count += m
    }

    count
  }
}