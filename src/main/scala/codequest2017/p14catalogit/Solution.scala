package codequest2017.p14catalogit

import scala.collection.mutable
import scala.collection.mutable.ListBuffer
import scala.io.StdIn

/**
  * @author Barry Becker
  */
object Solution extends App {


  val cache = mutable.Map[Long, (Int, Int)](1L -> (1, 1))

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


  def getSmallestAntiPrimeBiggerThan(a: Int): Int = {
    val lastMostFactors = cache(a - 1)._2
    for (i <- a to 10000000) {
      if (getNumFactors(i) > lastMostFactors)
        i
    }
    a
  }

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