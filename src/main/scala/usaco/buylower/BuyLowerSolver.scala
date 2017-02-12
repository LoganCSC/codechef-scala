package usaco.buylower

import scala.collection.immutable.HashMap

/**
  * Convert the sequence of prices into a list of lists of lists, then send it to ResultExtractor.
  */
class BuyLowerSolver(var prices: IndexedSeq[Int]) {

  private var cache = new HashMap[Int, List[List[Int]]]

  def solve(): String = {
    findLongestFrom(0, prices)
    new ResultExtractor(cache, prices).getResult
  }

  /** Might have to replace the recursive call with a stack, or do tail recursion */
  private def findLongestFrom(i: Int, array: IndexedSeq[Int]): List[List[Int]] = {
    val v = array(i)
    if (!cache.contains(i)) {
      if (i == array.length - 1)
        cache += i -> List(List(array(i)))
      else {
        val list = getMaxListToRight(i, array)
        println ("max list to right of " + array(i) + " is " + list)
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

        println(result)
        cache += i -> result
      }
    }
    cache(i)
  }

  /** @return the longest list to the right of i) */
  private def getMaxListToRight(i: Int, array: IndexedSeq[Int]): List[List[Int]] = {
    val v = array(i)
    val lists = (i + 1 until array.length)
      .map(findLongestFrom(_, array))
      .filter(_.head.exists(v > _))
      .map(list => (list, list.length))
    val longest = if (lists.nonEmpty) lists.reduceRight((a, b) => if (a._2 > b._2) a else b)
      else (Nil, 0)
    longest._1
  }
}
