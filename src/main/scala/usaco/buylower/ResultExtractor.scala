package usaco.buylower

/**
  * Given the lists of lists corresponding to the longest sequence at each position,
  * find the longest sequence and how often it occurs.
  * At each position of the array there will be a representation of the longest list.
  * Something like List( List(44), List(32, 31), List(3))
  * Since the second term has 2 value, this represents 2 possible longest lists: 44, 32, 3 or 44, 31, 3.
  */
class ResultExtractor(cache: Array[List[List[Int]]]) {

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