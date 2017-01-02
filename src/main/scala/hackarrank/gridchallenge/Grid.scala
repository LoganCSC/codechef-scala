package hackarrank.gridchallenge


class Grid(elements: Array[Array[Char]]) {

  /** @return the minimum number of moves to get from start to finish. return -1 if no solution */
  def isSortSolvable: Boolean = {
    // first sort each row, then check if columns are sorted
    val m = elements.map(_.sorted)

    //println(m.map(_.mkString(", ")).mkString("\n"))

    // now check that columns are sorted
    for (i: Int <- m.indices) {
      for (j: Int <- 0 to m.length - 2) {
        if (m(j)(i) > m(j + 1)(i)) {
          println(m(j)(i) + " WAS GREATER THAN " + m(j + 1)(i))
          return false
        }
      }
    }
    true
  }
}
