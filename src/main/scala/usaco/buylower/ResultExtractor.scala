package usaco.buylower

/**
  * Given the lists of lists corresponding to the longest sequence at each position,
  * find the longest sequence and how often it occurs.
  *
  */
class ResultExtractor(cache: Array[List[List[Int]]]) {

  def getResult: String = {

    val longest: Int = cache.map(_.length).max
    val longestEntries = cache.zipWithIndex.filter(_._1.length == longest)
    var totalOccurrences: BigInt = BigInt(0)
    var startValueSet: Set[Int] = Set() // only count the first seq with same start value
    for (idx <- longestEntries.indices) {
      val list = longestEntries(idx)._1
      val value = list.head.head
      if (!startValueSet.contains(value)) {
        startValueSet += value
        // consider the length of the first element to always be one.
        totalOccurrences += list.map(x => BigInt(x.length)).product
      }
    }

    s"$longest $totalOccurrences"
  }

}
