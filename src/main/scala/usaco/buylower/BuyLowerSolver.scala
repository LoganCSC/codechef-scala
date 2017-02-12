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

  private def createLongestList(v: Int, list: List[List[Int]]) = {
    if (list.isEmpty) List(List(v))
    else if (list.tail.isEmpty && list.head.exists(v > _)) List(v) +: List(list.head.filter(v > _))
    else if (list.tail.isEmpty) List(v +: list.head)
    else if (list.head.contains(v)) list
    else if (list.head.exists(v > _)) List(v) +: list.head.filter(_ < v) +: list.tail
    else if (list.tail.head.forall(v > _)) (v +: list.head) +: list.tail.head +: list.tail.tail
    else list
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
