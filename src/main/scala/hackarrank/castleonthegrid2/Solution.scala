package hackarrank.castleonthegrid2

import common.Location

import scala.io.StdIn

/**
 * Simpler solution than AStar solution.
 * https://www.hackerrank.com/contests/compsci-club5/challenges/castle-on-the-grid
 */
object Solution {

  def main(args: Array[String]) {
    val grid = readBoard()
    println(grid.findMinimumMoves)
  }

  private def readBoard(): Grid = {
    val dim = StdIn.readLine().toInt
    val matrix = for (i <- 1 to dim) yield StdIn.readLine().toArray
    val Array(startRow, startCol, goalRow, goalCol) = StdIn.readLine().split(" ").map(_.toInt)
    val start = new Location(startRow, startCol)
    val goal = new Location(goalRow, goalCol)
    new Grid(start, goal, matrix.toArray)
  }
}