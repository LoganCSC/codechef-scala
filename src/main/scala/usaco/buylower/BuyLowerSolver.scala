package usaco.buylower

/**
  * Convert the sequence of prices into an array of longest sequences, then send it to ResultExtractor.
  * Based on algorithm from Riya Arora. Ported to scala from C++.
  * @param prices a list of prices to search
  */
class BuyLowerSolver(var prices: IndexedSeq[Int]) {
  private val N: Int = prices.length
  private val total = Array.ofDim[Int](N)
  private val num = Array.ofDim[BigInt](N)

  def solve(): String = {

    for (i <- 0 until N) {
      val p = prices(i)
      val use = (0 +: (0 until i).filter(p < prices(_)).map(total)).max
      total(i) = use + 1
      num(i) = if (total(i) == 1) 1 else 0

      val leftIndices = (i-1) to 0 by -1
      num(i) += leftIndices.filter(j => total(j) == use && p < prices(j)).map(num).sum
      leftIndices.filter(j => total(j) == total(i) && p == prices(j)).foreach(j => total(j) -= 1)
    }

    val outA: Int = total.max
    val outB: BigInt = (0 until N).filter(total(_) == outA).map(num).sum
    s"$outA $outB"
  }
}
