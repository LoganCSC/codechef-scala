package usaco.buylower

/**
  * Convert the sequence of prices into an array of longest sequences, then send it to ResultExtractor.
  * @param prices a list of prices to search
  */
class BuyLowerSolverRiyaOrig(var prices: IndexedSeq[Int]) {
  private val N: Int = prices.length
  private val total = Array.ofDim[Int](N)
  private val num = Array.ofDim[BigInt](N)

  def solve(): String = {
    var outA: Int = 0
    var outB: BigInt = 0

    for (i <- 0 until N) {
      var use: Int  = 0
      for (j <- 0 until i) {
        if (prices(i) < prices(j))
          use = use max total(j)
      }
      total(i) = use + 1
      num(i) = if (total(i) == 1) 1 else 0

      for (j <- (i - 1) to 0 by -1) {
        if (total(j) == use && prices(i) < prices(j))
          num(i) += num(j)
        if (total(j) == total(i) && prices(i) == prices(j))
          total(j) -= 1
      }
      outA = outA max total(i)
    }

    for (i <- (N - 1) to 0 by -1)
      if (total(i) == outA) outB += num(i)

    s"$outA $outB"
  }
}
