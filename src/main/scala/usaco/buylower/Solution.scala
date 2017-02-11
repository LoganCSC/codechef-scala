package usaco.buylower

import scala.io.StdIn

/**
 * Given a sequence of stock prices find the sequence where you can buy lower each time.
 */
object Solution extends App {

  def readPrices(): IndexedSeq[Int] = {
    val numPrices = StdIn.readLine().toInt
    for (i <- 0 until numPrices) yield StdIn.readInt()
  }

  println(new BuyLowerSolver(readPrices()).solve())
}