package usaco.buylower

import hackarrank.maximumsubarray.ArrayData

import scala.collection.mutable.ListBuffer
import scala.io.StdIn

/**
 * Given a sequence of stock prices find the sequence where you can buy lower each time.
 */
object Solution extends App {

  val numPrices = StdIn.readLine().toInt
  var ct = 0
  var prices: Array[Int] = Array.ofDim(numPrices)

  while (ct < numPrices) {
    prices(ct) = StdIn.readInt()
    ct += 1
  }

  println(new BuyLowerSolver(prices).solve())

}