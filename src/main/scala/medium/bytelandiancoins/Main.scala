package medium.bytelandiancoins

import scala.collection.mutable
import scala.io.StdIn

/**
 * https://www.codechef.com/problems/COINS
 */
object Main {

  val cache: mutable.Map[Long, Long] = mutable.Map[Long, Long](0L -> 0L)

  def main(args: Array[String]) {

    Iterator.continually(StdIn.readLine)
      .takeWhile(x => {
        x != null && x != "" && x != null
      }).foreach(coin => println(getCoinValue(coin.toLong)))
  }


  def getCoinValue(coin: Long): Long = {
    if (cache.contains(coin)) {
      cache(coin)
    } else {
      val half = getCoinValue(coin / 2)
      val third = getCoinValue(coin / 3)
      val quarter = getCoinValue(coin / 4)
      val result = Math.max(coin, half + third + quarter)
      cache.put(coin, result)
      result
    }
  }

}
