package usaco.buylower

/**
  * Convert the sequence of prices into a list of lists of lists, then send it to ResultExtractor.
  * @param array a list of pirces to search
  * @author Barry Becker
  */
class BuyLowerSolver(var array: IndexedSeq[Int]) {

  private val cache: Array[List[List[Int]]] = Array.fill(array.length) { Nil }

  def solve(): String = {
    // loop from the right so we can avoid recursion
    for (i <- array.length - 1 to 0 by -1)
      findLongestStartingWith(i)

    new ResultExtractor(cache).getResult
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
    var longest = if (lists.nonEmpty) lists.max(Ordering.by((_: (_, Int))._2))
      else (Nil, 0)
    val longestLength = longest._2
    if (longestLength > 0) {
      val longestLists = lists.filter(_._2 == longestLength).map(_._1)
      longest = (longestLists.flatMap(_.head).distinct.toList +: longestLists(0).tail, longestLength)
    }
    longest._1
  }
}
