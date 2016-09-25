package hackarrank.antiprimenumbers


import scala.collection.mutable
import scala.collection.mutable.ListBuffer
import scala.io.StdIn

/**
 * https://www.hackerrank.com/contests/rookierank/challenges/antiprime-numbers
 */
object Solution {

  // dynamic programming cache,
  // map from anti-prim number to (number of factors, max num factors to this point)
  val cache = mutable.Map[Long, (Int, Int)](1L -> (1, 1))

  def main(args: Array[String]) {
    val numQueries = StdIn.readInt()    // q

    // warm up the cache
    var maxFactorsSoFar = 1

    for (i <- 1 to 1000000) {
      val numFactors = getNumFactors(i)
      if (numFactors > maxFactorsSoFar)
        maxFactorsSoFar = numFactors
      cache.put(i, (numFactors, maxFactorsSoFar))
    }

    for (query <- 0 until numQueries) {
      println(getSmallestAntiPrimeBiggerThan(StdIn.readInt()))
    }

  }

  def getSmallestAntiPrimeBiggerThan(a: Int): Int = {
    val lastMostFactors = cache(a - 1)._2
    for (i <- a to 10000000) {
        if (getNumFactors(i) > lastMostFactors)
          i
    }
    a
  }

  /*
 def getNumFactors(num: Long): Int = {
   if (cache.contains(num))
     return cache(num)._1
   val limit = Math.sqrt(num).toInt + 1
   if (num == 1)
     1
   else if (num == 2 || num == 3)
     2
   else {
     for (i <- 2 to limit) {
       if (num % i == 0)
         return 1 + getNumFactors(num / i)
     }
   }

   2 // two factors 1 and itself
 }   */

  def getNumFactors(num: Long): Int = {
    findDivisors(num).length
  }

  def findDivisors(num: Long): List[Long] = {
    val factors: ListBuffer[Long] = ListBuffer(1)

    val sqrtNum = Math.sqrt(num).toInt
    for (i <- 2 to sqrtNum) {
      if (num % i == 0) {
        factors += i
        factors += num / i
      }
    }
    if (num != 1) factors += num
    factors.toList
  }
}