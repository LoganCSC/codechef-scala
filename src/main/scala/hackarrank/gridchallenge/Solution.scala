package hackarrank.gridchallenge

import scala.io.StdIn

/**
 * https://www.hackerrank.com/contests/compsci-club7/challenges/grid-challenge
 */
object Solution {

  def main(args: Array[String]) {
    val numTests = StdIn.readLine().toInt
    for (i <- 1 to numTests) {
      val grid = readBoard()
      //println(grid)
      println(if (grid.isSortSolvable) "YES" else "NO")
    }
  }

  private def readBoard(): Grid = {
    val dim = StdIn.readLine().toInt
    val matrix = for (i <- 1 to dim) yield StdIn.readLine().toArray
    new Grid(matrix.toArray)
  }
}