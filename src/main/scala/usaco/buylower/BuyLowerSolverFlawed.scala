package usaco.buylower


/**
  * Convert the sequence of prices into an array of longest sequences, then send it to ResultExtractor.
  * This was my first attempt. It does some nice things with lists, and finds the right longest length,
  * but in the end, it did not always find the correct number of duplicates.
  * @param array a list of prices to search
  * @author Barry Becker
  */
class BuyLowerSolverFlawed(var array: IndexedSeq[Int]) {

  private val cache: Array[List[List[Int]]] = Array.fill(array.length) { Nil }

  def solve(): String = {
    // loop from the right so we can avoid recursion
    for (i <- array.length - 1 to 0 by -1)
      findLongestStartingWith(i)

    getResult
  }

  private def findLongestStartingWith(i: Int): List[List[Int]] = {
    val v = array(i)
    if (cache(i) == Nil)
      cache(i) = if (i == array.length - 1) List(List(v)) else createLongestList(v, getMaxListToRight(i))
    cache(i)
  }

  private def createLongestList(v: Int, list: List[List[Int]]) = list match {
    case Nil => List(List(v))
    case head :: Nil if head.exists(v > _) => List(v) +: List(head.filter(v > _))
    case head :: Nil => List(v +: head)
    case head :: tail if head.contains(v) => list
    case head :: tail if head.exists(v > _) => List(v) +: head.filter(_ < v) +: tail
  }

  /** @return the longest list to the right of i */
  private def getMaxListToRight(i: Int): List[List[Int]] = {
    val v = array(i)
    val lists = cache.slice(i + 1, cache.length).filter(_.head.exists(v > _)).map(list => (list, list.length))
    var longest = if (lists.nonEmpty) lists.max(Ordering.by((_: (_, Int))._2)) else (Nil, 0)
    val longestLength = longest._2
    if (longestLength > 0) {
      val longestLists = lists.filter(_._2 == longestLength).map(_._1)
      longest = (longestLists.flatMap(_.head).distinct.toList +: longestLists(0).tail, longestLength)
    }
    longest._1
  }

  def getResult: String = {
    val longest: Int = cache.map(_.length).max
    val longestEntries = cache.zipWithIndex.filter(_._1.length == longest)
    var totalOccurrences = BigInt(0)
    var startValueSet: Set[Int] = Set()
    for (idx <- longestEntries.indices) {
      val list = longestEntries(idx)._1
      val value = list.head.head
      // only count the first seq with same start value
      if (!startValueSet.contains(value)) {
        startValueSet += value
        totalOccurrences += list.map(x => BigInt(x.length)).product
      }
    }

    s"$longest $totalOccurrences"
  }
}
