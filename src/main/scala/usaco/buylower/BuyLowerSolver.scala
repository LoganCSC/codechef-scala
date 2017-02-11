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
    for (v <- prices) {
      addToLists(v)
    }

    println("lists = \n" + lists.map(_.mkString("(", ", ", ")")).mkString("\n"))
    getResult
  }

  private def addToLists(v: Int) = {
    if (lists.isEmpty || lists.exists(v > _.head.head))
      lists +:= List(List(v))
    lists = lists.map(addValueToList(v, _))
    lists.foreach {
      case first :: (second :: tail) =>
        if (first.forall(v > _) && second.exists(v < _))
          lists +:= List(v) +: second.filter(_ > v) +: tail
      case _ =>
    }
  }

  private def addValueToList(v: Int, list:List[List[Int]]): List[List[Int]] = list match {
    case first :: (second :: tail) =>
      if (first.forall(v > _) && second.forall(v < _))
        (v +: first) +: second +: tail
      else if (first.forall(v < _))
        List(v) +: first +: second +: tail
      else list
    case head :: Nil =>
      if (v > head.head) List(v +: head)
      else if (head.forall(v < _)) List(v) +: List(head)
      else list
  }

  private def getResult: String = {
    val numLists = lists.map(_.map(lst => BigInt.int2bigInt(lst.length)))
    val longest = numLists.map(_.length).max
    val longestLists = numLists.filter(_.length == longest)
    val numLongest: BigInt = longestLists.map(_.product).sum

    s"$longest $numLongest"
  }
}
