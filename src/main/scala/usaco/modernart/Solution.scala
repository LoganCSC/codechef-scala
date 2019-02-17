package usaco.modernart

import scala.io.StdIn



/**
  * Given a sequence of stock prices find the sequence where you can buy lower each time.
  */
object Solution extends App {

  def readCanvas(): Array[Array[Int]] = {
    val num = StdIn.readLine().toInt
    val a = Array.ofDim[Int](num, num)
    for (y <- 0 until num)
      for (x <- 0 until num)
        a(y)(x) = StdIn.readInt()
    a
  }

  println(new ModernArtSolver(readCanvas()).solve())
}
