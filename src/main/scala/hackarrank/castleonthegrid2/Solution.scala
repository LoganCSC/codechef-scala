package hackarrank.castleonthegrid2

import common.Location

import scala.io.StdIn

/**
 * Simpler solution than AStar solution.
 * https://www.hackerrank.com/contests/compsci-club5/challenges/castle-on-the-grid
 */
object Solution extends App {

  val grid = readBoard()
  println(grid.findMinimumMoves)

  private def readBoard(): Grid = {
    val dim = StdIn.readLine().toInt
    val matrix = for (i <- 1 to dim) yield StdIn.readLine().toArray
    val Array(startRow, startCol, goalRow, goalCol) = StdIn.readLine().split(" ").map(_.toInt)
    val start = Location(startRow, startCol)
    val goal = Location(goalRow, goalCol)
    new Grid(start, goal, matrix.toArray)
  }
}