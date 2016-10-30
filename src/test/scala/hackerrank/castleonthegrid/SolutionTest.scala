package hackerrank.castleonthegrid

import common.Location
import hackarrank.castleonthegrid.astarsolution.AStarCastleSolver
import hackarrank.castleonthegrid.{Board, Grid, SimpleCastleSolver}
import org.scalatest.FunSuite


/**
  * @author Barry Becker
  */
class SolutionTest extends FunSuite {


  test("test simple default case") {
    val matrix = Array(
      ".X.".toArray,
      ".X.".toArray,
      "...".toArray
    )

    val start = new Location(0, 0)
    val goal = new Location(0, 2)
    verifySolution(start, goal, matrix, 3)
  }

  test("test case with lots of blocks") {

    val matrix = Array(
      "....X...XX".toArray,
      "..X...X...".toArray,
      ".X..X.XX..".toArray,
      "X...X...X.".toArray,
      "...X...X..".toArray,
      "X..X....XX".toArray,
      "..X....X..".toArray,
      "....X...F.".toArray,
      "..X...X...".toArray,
      "XX....X.X.".toArray
    )

    val start = new Location(1, 1)
    val goal = new Location(7, 8)
    verifySolution(start, goal, matrix, 6)
  }

  test("test case for wall with opening") {

    val matrix = Array(
      "....X...XX".toArray,
      "..XX..X...".toArray,
      ".X.XX.XX..".toArray,
      ".X..X...X.".toArray,
      ".......X..".toArray,
      ".X.XXXX.XX".toArray,
      ".X......X.".toArray,
      ".XX.XXX...".toArray,
      ".XX..XX...".toArray,
      ".X......X.".toArray
    )

    val start = new Location(1, 1)
    val goal = new Location(7, 8)
    verifySolution(start, goal, matrix, 7)
  }

  test("test case when no solution") {

    val matrix = Array(
      "...X....XX".toArray,
      "..XX...X..".toArray,
      ".X.XX.XX..".toArray,
      ".X..X...X.".toArray,
      ".......X..".toArray,
      ".X.XXXX.XX".toArray,
      ".XX.....X.".toArray,
      ".X..XXX...".toArray,
      ".XX..XX...".toArray,
      ".X......X.".toArray
    )

    val start = new Location(1, 1)
    val goal = new Location(7, 8)
    verifySolution(start, goal, matrix, -1)
  }

  def verifySolution(start: Location, goal: Location, matrix: Array[Array[Char]], expectedNumSteps: Int) = {
    val board = new Board(new Grid(start, goal, matrix), start)
    //val solver = new AStarCastleSolver(board)
    val solver = new SimpleCastleSolver(board)

    if (expectedNumSteps == -1)
      assert(solver.moves == -1)
    else {
      //println("solution = " + solver.solution.map(_.currentPosition).mkString(", "))
      println("solution = " + solver.transitions.mkString(", "))
      assertResult(expectedNumSteps) { solver.moves }
    }
  }
}