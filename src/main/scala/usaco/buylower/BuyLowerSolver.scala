package usaco.buylower

import scala.collection.immutable.HashMap

/**
  * Convert the sequence of prices into a list of lists of lists, then send it to ResultExtractor.
  */
class BuyLowerSolver(var prices: IndexedSeq[Int]) {

  private var cache: Array[List[List[Int]]] = Array.fill(prices.length) { Nil }

  def solve(): String = {
    // loop from the right so we can avoid recursion
    for (i <- prices.length - 1 to 0 by -1)
      findLongestFrom(i, prices)

    new ResultExtractor(cache, prices).getResult
  }

  /** Might have to replace the recursive call with a stack, or do tail recursion */
  private def findLongestFrom(i: Int, array: IndexedSeq[Int]): List[List[Int]] = {
    val v = array(i)
    if (cache(i) == Nil) {
      if (i == array.length - 1)
        cache(i) = List(List(array(i)))
      else {
        val list = getMaxListToRight(i, array)
        val result: List[List[Int]] =
          if (list.isEmpty)
            List(List(v))
          else if (list.tail.isEmpty && list.head.exists(v > _))
            List(v) +: List(list.head.filter(v > _))
          else if (list.tail.isEmpty)
            List(v +: list.head)
          else if (list.head.contains(v))
            list
          else if (list.head.exists(v > _))
            List(v) +: list.head.filter(_ < v) +: list.tail
          else if (list.tail.head.forall(v > _))
            (v +: list.head) +: list.tail.head +: list.tail.tail
          else list

        //println(result)
        cache(i) = result
      }
    }
    cache(i)
  }

  /** @return the longest list to the right of i) */
  private def getMaxListToRight(i: Int, array: IndexedSeq[Int]): List[List[Int]] = {
    val v = array(i)
    val lists = (i + 1 until array.length)
      .map(cache(_))
      .filter(_.head.exists(v > _))
      .map(list => (list, list.length))
    var longest = if (lists.nonEmpty) lists.reduceRight((a, b) => if (a._2 > b._2) a else b)
      else (Nil, 0)
    val longestLength = longest._2
    if (longestLength > 0) {
      val longestLists = lists.filter(_._2 == longestLength).map(_._1)
      longest = (longestLists.flatMap(_.head).distinct.toList +: longestLists(0).tail, longestLength)
    }
    longest._1
  }
}
