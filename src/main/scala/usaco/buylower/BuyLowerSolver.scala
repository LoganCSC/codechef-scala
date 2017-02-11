package usaco.buylower

/**
  * Convert the sequence of prices into a list of lists of lists.
  * For example, the sequence 10, 20, 30, 25, 35, 15, 55, 45, 27, 40, 60
  * should produce (
  *  ((10, 20, 30)),
  *  ((20, 30), (15),
  *  ((30), (25), (15)),
  *  ((35), (15,27)),
  *  ((55), (45), (27),
  *  ((60))
  * )
  */
class BuyLowerSolver(var prices: Seq[Int]) {

  private var lists: List[List[List[Int]]] = List()


  def solve(): String = {
    for (v <- prices)
      lists = addToLists(v)

    new ResultExtractor(lists).getResult
  }

  private def addToLists(v: Int): List[List[List[Int]]] = {
    if (lists.isEmpty || valueLargerThanAny(v))
      lists +:= List(List(v))

    for (list <- lists) yield addValueToList(v, list)
  }

  private def valueLargerThanAny(v: Int): Boolean =
    !lists.exists(_.head.head > v)   // list => list.head.nonEmpty &&

  private def addValueToList(v: Int, list:List[List[Int]]): List[List[Int]] = {
    list
  }

}
