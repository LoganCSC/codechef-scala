package hackarrank.extremelydangerousvirus


import scala.collection.mutable
import scala.io.StdIn

/**
 * https://www.hackerrank.com/contests/rookierank/challenges/extremely-dangerous-virus
  * Key insight: A^B mod C = ( (A mod C)^B ) mod C
 */
object Solution {

  // The constant to mod by to keep power function from exploding
  val C = 1000000007L
  // dynamic programming cache
  val cache = mutable.Map[(BigDecimal, Long), BigDecimal]()

  def main(args: Array[String]) {
    val input = StdIn.readLine().split(" ").map(_.toLong)
    println(expNumCells(input(0).toInt, input(1).toInt, input(2)))
  }

  def expNumCells(a: Int, b: Int, t: Long): Long = {
    myPow((a + b) / 2.0, t).toLong
  }

  def myPow(base: BigDecimal, exp: Long): BigDecimal = {
    val key = (base, exp)
    if (exp == 1)
      base
    else if (cache.contains(key))
      cache(key)
    else {
      val pow1 = exp / 2
      val pow2 = exp - pow1
      val result = (myPow(base, pow1) % C * myPow(base, pow2)) % C
      cache.put(key, result)
      result
    }
  }
}