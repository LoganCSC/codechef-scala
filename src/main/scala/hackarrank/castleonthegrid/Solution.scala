package hackarrank.castleonthegrid

import common.Location

import scala.collection.mutable.ListBuffer
import scala.io.StdIn

/**
 * https://www.hackerrank.com/contests/compsci-club5/challenges/castle-on-the-grid
 */
object Solution {

  def main(args: Array[String]) {

    val board = readBoard()
    val solver = new SimpleCastleSolver(board)
    println(solver.moves)
  }

  private def readBoard(): Board = {
    val dim = StdIn.readLine().toInt
    val matrix = new ListBuffer[Array[Char]]()
    (1 to dim).foreach(x =>
      matrix.append(StdIn.readLine().toArray)
    )
    val Array(startRow, startCol, goalRow, goalCol) = StdIn.readLine().split(" ").map(_.toInt)
    val start = new Location(startRow, startCol)
    val goal = new Location(goalRow, goalCol)

    new Board(new Grid(start, goal, matrix.toArray), start)
  }

}