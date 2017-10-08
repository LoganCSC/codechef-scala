package usaco.buylower

/**
  * Convert the sequence of prices into an array of longest sequences, then send it to ResultExtractor.
  * Takes 0.52 seconds to run test cases
  * @param prices a list of prices to search
  */
class BuyLowerSolverRiyaOrig(var prices: IndexedSeq[Int]) {
  private val N: Int = prices.length
  private val len = Array.ofDim[Int](N)
  private val num = Array.ofDim[BigInt](N)

  def solve(): String = {
    var outA: Int = 0
    var outB: BigInt = 0

    for (i <- 0 until N) {
      var oldLongestLen: Int  = 0
      val p = prices(i)
      for (j <- 0 until i) {
        if (prices(j) > p )
          oldLongestLen = oldLongestLen max len(j)
      }
      val longestLen = oldLongestLen + 1
      len(i) = longestLen

      // total(i) == 1 whenever we hit a number larger than any before
      num(i) = if (longestLen == 1) 1 else 0

      for (j <- (i - 1) to 0 by -1) {
        if (len(j) == oldLongestLen && prices(j) > p)
          num(i) += num(j)
        if (len(j) == longestLen && p == prices(j)) {
          // this is to prevent an earlier duplicate entry from counting in the sum
          len(j) -= 1
        }
      }
      outA = outA max longestLen
    }

    for (i <- 0 until N)
      if (len(i) == outA) outB += num(i)

    s"$outA $outB"
  }
}
