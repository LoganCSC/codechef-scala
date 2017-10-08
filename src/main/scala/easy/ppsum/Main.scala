package easy.ppsum

import scala.io.StdIn

/**
 * https://www.codechef.com/problems/COINS
 */
object Main extends App {

  val numTests = StdIn.readInt()
  for (i <- 0 until numTests) {
    val line = StdIn.readLine()
    val terms = line.split(" ")
    println(ppSum(terms(0).toInt, terms(1).toInt))
  }

  def ppSum(recurse: Int, n: Int): Long =
      if (recurse == 1) sum(n) else sum(ppSum(recurse - 1, n))

  def sum(n: Long) : Long = n * (n + 1) / 2
}
