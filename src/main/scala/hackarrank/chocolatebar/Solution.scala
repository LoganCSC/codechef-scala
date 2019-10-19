package hackarrank.chocolatebar

import scala.io.StdIn

/**
  * Given a chocolate bar with M*N cells, some of which contain raisins, determine
  * the minimum number of breaks to isolate the raisin cells from the pure chocolate cells.
  */
object Solution extends App {

  val numTestCases = StdIn.readLine().toInt
  for (i <- 0 until numTestCases) {
    val dims: Array[Int] = StdIn.readLine().split(" ").map(_.toInt)
    val cells: Array[Array[Byte]] = Array.ofDim[Array[Byte]](dims(0))
    for (i <- 0 until dims(0)) {
      cells(i) = StdIn.readLine().split(" ").map(_.toByte)
    }

    val bar = ChocolateBar(cells)

    println(ChocolateBar(cells).findMinBreaksToIsolateRaisins())
  }
}
