package usaco.subseqsummingtosevens

/**
  * Find larges subsequences in a list of integers that sum to a multiple of radix.
  * @param radix the number to mudulo by
  */
class SubSequenceFinder(radix: Int) {

  /** @return logest sequence using quadratic brute force approach */
  def findLongestSubSequence2(list: Seq[Int]): Int = {
    for (i <- list.length to 1 by - 1)
      for (j <- 0 to list.length - i)
        if (sumsToMultiple(list, j, j + i)) return i
    0
  }

  /** @return longest sequence using linear approach */
  def findLongestSubSequence(list: Seq[Int]): Int = {
    var last = Array.fill[Int](radix)(-1)
    var next = Array.fill[Int](radix)(-1)
    var longest = 0
    for (i <- list.indices) {
      val el = list(i)
      next(el) = 1
      for (j <- 0 until radix)
        if (last(j) >= 0)
          next((j + el) % radix) = last(j) + 1
      if (next(0) > longest) longest = next(0)
      last = next
      next = Array.fill[Int](radix)(-1)
    }
    longest
  }

  /** @return true if the specified sub sequence sums to a multiple of the radix */
  private def sumsToMultiple(list: Seq[Int], beginIdx: Int, endIdx: Int): Boolean = {
    val subsum = list.slice(beginIdx, endIdx).sum
    subsum % radix == 0
  }
}
