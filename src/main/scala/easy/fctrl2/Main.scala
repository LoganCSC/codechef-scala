package easy.fctrl2

import scala.io.StdIn

object Main extends App {

  val testCount = StdIn.readInt()
  for (i <- 0 until testCount) {
    println(factorial(StdIn.readInt()))
  }

  def factorial(i: Int): String = {
    var r = BigInt(1)
    for (x <- 1 to i) {
      r *= x
    }

    r.toString()
  }
}