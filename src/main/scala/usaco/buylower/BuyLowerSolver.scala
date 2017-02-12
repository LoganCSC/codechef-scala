package usaco.buylower

import scala.collection.immutable.HashMap

/**
  * Convert the sequence of prices into a list of lists of lists, then send it to ResultExtractor.
  * For example, the sequence 10, 20, 30, 25, 35, 15, 55, 45, 27,
  * should produce (
  * (List(27), List(35, 30))
    *(List(27))
    *(List(27), List(45))
    *(List(27), List(45), List(55))
    *(List(27, 15), List(35))
    *(List(15), List(25), List(30))
    *(List(15), List(35, 30, 20))
    *(List(55, 35, 30, 20, 10))  )
  */
class BuyLowerSolver(var prices: IndexedSeq[Int]) {

  private var cache = new HashMap[Int, List[List[Int]]]

  def solve(): String = {
    findLongestFrom(0, prices)
    new ResultExtractor(cache, prices).getResult
  }

  /** Might have to replace the recursive call with a stack, or do tail recursion */
  private def findLongestFrom(i: Int, array: IndexedSeq[Int]): List[List[Int]] = {
    val v = array(i)
    if (!cache.contains(i)) {
      if (i == array.length - 1) {
        cache += i -> List(List(array(i)))
      }
      else {
        val list = findLongestFrom(i + 1, array)
        val result: List[List[Int]] =
          if (list.head.exists(v > _))
            List(v) +: list.head.filter(v > _) +: list.tail
          else if (list.tail.nonEmpty && list.tail.head.forall(v > _))
            (v +: list.head) +: list.tail.head +: list.tail.tail
          else list

        println(result.mkString(","))
        cache += i -> result
      }
    }
    cache(i)
  }

}
