package hackarrank.castleonthegrid

import scala.collection.mutable.ListBuffer
import scala.io.StdIn

/**
 * https://www.hackerrank.com/contests/compsci-club5/challenges/castle-on-the-grid
 */
object Solution {

  def main(args: Array[String]) {
    val dim = StdIn.readLine().toInt
    val matrix = new ListBuffer[Array[Char]]()
    (1 to dim).foreach(x =>
      matrix.append(StdIn.readLine().toArray)
    )
    val Array(startRow, startCol, goalRow, goalCol) = StdIn.readLine().split(" ").map(_.toInt)

    val grid = new Grid(matrix.toArray)
    //println(matrix.map(_.mkString("")).mkString("\n"))


    println(grid.findNumStepsToGoal(startRow, startCol, goalRow, goalCol))
  }

}