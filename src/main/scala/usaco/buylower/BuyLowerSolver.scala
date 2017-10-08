package usaco.buylower

/**
  * Convert the sequence of prices into an array of longest sequences, then send it to ResultExtractor.
  * Based on algorithm from Riya Arora. Ported to scala from C++.
  * @param prices a list of prices to search
  */
class BuyLowerSolver(var prices: IndexedSeq[Int]) {
  private val N: Int = prices.length
  private val len = Array.ofDim[Int](N)
  private val num = Array.ofDim[BigInt](N)

  def solve(): String = {

    for (i <- 0 until N) {
      val p = prices(i)
      val oldLongest = (0 +: (0 until i).filter(p < prices(_)).map(len)).max
      val longest = oldLongest + 1
      len(i) = longest
      num(i) = if (longest == 1) 1 else 0

      for (j <- (i-1) to 0 by -1) {
        if (len(j) == oldLongest && p < prices(j)) num(i) += num(j)
        if (len(j) == longest && p == prices(j)) len(j) -= 1
      }
    }

    val outA: Int = len.max
    val outB: BigInt = (0 until N).filter(len(_) == outA).map(num).sum
    s"$outA $outB"
  }
}
