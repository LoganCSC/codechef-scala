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
  cache += (2 -> List(List(3)))



  def solve(): String = {
    findLongestFrom(0, prices)

    new ResultExtractor(cache).getResult
  }

  /** Might have to replace the recursive call with a stack, or do tail recursion */
  private def findLongestFrom(i: Int, array: IndexedSeq[Int]): List[List[Int]] = {
    if (cache.contains(i)) cache(i)
    else if (i == array.length) List(List(array(i)))
    else {
      val v = array(i)
      val list = findLongestFrom(i + 1, array)
      val result: List[List[Int]] =
        if (list.head.forall(v > _)) List(v) +: list
        else if (list.tail.nonEmpty && list.tail.head.forall(v > _))
          list.head +: (v +: list.tail.head) +: list.tail.tail
        else list

      println(result.mkString(","))
      cache += i -> result
      result
    }
  }

}
