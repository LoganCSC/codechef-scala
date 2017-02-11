package usaco.buylower

/**
  * Given the lists of lists of lists find the longest sequence and how often it occurs
  * For example (
  *  ((30), (25, 26, 27), (15, 16)), // represents 6 longest sequences
  *  ((35), (15, 27)),
  *  ((55), (45), (27, 28)  // represents 2 longest sequences
  * ) would yield "3, 8" because the longest sequence is 3 and there are 6 + 2
  */
class ResultExtractor(lists: List[List[List[Int]]]) {

  def getResult: String = {
    val numLists = lists.map(_.map(lst => BigInt.int2bigInt(lst.length)))
    val longest = numLists.map(_.length).max
    val longestLists = numLists.filter(_.length == longest)
    val numLongest: BigInt = longestLists.map(_.product).sum

    s"$longest $numLongest"
  }
}
