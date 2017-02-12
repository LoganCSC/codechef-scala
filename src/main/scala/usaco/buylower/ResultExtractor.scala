package usaco.buylower

/**
  * Given the lists of lists of lists find the longest sequence and how often it occurs
  * For example (
  *  ((30), (25, 26, 27), (15, 16)), // represents 6 longest sequences
  *  ((35), (15, 27)),
  *  ((55), (45), (27, 28)  // represents 2 longest sequences
  * ) would yield "3, 8" because the longest sequence is 3 and there are 6 + 2
  */
class ResultExtractor(cache: Array[List[List[Int]]], array: IndexedSeq[Int]) {

  def getResult: String = {

    val longest: Int = cache.map(_.length).max
    val longestEntries = cache.zipWithIndex.filter(_._1.length == longest)
    //println("longest = " + longestEntries.mkString("\n"))
    var totalOccurrences: BigInt = BigInt(0)
    var startValueSet: Set[Int] = Set()
    for (idx <- longestEntries.indices) {
      val value = array(longestEntries(idx)._2)
      val list = longestEntries(idx)._1
      if (!startValueSet.contains(value)) {
        startValueSet += value
        // consider the length of the first element to always be one.
        totalOccurrences += list.tail.map(x => BigInt(x.length)).product
      }
    }

    s"$longest $totalOccurrences"
  }


}
