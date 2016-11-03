package hackerrank.castleonthegrid

import common.Location
//import hackarrank.castleonthegrid.astarsolution.AStarCastleSolver
import hackarrank.castleonthegrid2.Grid
//import hackarrank.castleonthegrid.{Board, Grid, SimpleCastleSolver}
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

    verifySolution(start = Location(0, 0), goal = Location(0, 2), matrix, 3)
    verifySolution(start = Location(0, 2), goal = Location(0, 0), matrix, 3)
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
      "....X.....".toArray,
      "..X...X...".toArray,
      "XX....X.X.".toArray
    )
    verifySolution(start = Location(1, 1), goal = Location(7, 8), matrix, 6)
    verifySolution(start = Location(7, 8), goal = Location(1, 1), matrix, 6)
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
    verifySolution(start = Location(1, 1), goal = Location(7, 8), matrix, 7)
    verifySolution(start = Location(7, 8), goal = Location(1, 1), matrix, 7)
  }

  test("test case for spiral") {

    val matrix = Array(
      "..XXXXXXXX".toArray,
      ".........X".toArray,
      "XXXXXXXX.X".toArray,
      "X......X.X".toArray,
      "X.XXXX.X.X".toArray,
      "X.X..X.X.X".toArray,
      "X.X....X.X".toArray,
      "X.XXXXXX.X".toArray,
      "X........X".toArray,
      "XXXXXXXXXX".toArray
    )
    verifySolution(start = Location(0, 0), goal = Location(5, 3), matrix, 9)
    verifySolution(start = Location(5, 3), goal = Location(0, 0), matrix, 9)
  }

  test("test case for spiral with opening") {

    val matrix = Array(
      "..XXXXXXXX".toArray,
      ".........X".toArray,
      "XXX.XXXX.X".toArray,
      "X......X.X".toArray,
      "X.XXXX.X.X".toArray,
      "X.X..X.X.X".toArray,
      "X.X....X.X".toArray,
      "X.XXXXXX.X".toArray,
      "X........X".toArray,
      "XXXXXXXXXX".toArray
    )
    verifySolution(start = Location(0, 0), goal = Location(5, 3), matrix, 7)
    verifySolution(start = Location(5, 3), goal = Location(0, 0), matrix, 7)
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
    //val board = new Board(new Grid(start, goal, matrix), start)
    //val solver = new AStarCastleSolver(board)
    //val solver = new SimpleCastleSolver(board)
    val grid = new Grid(start, goal, matrix)
    val moves = grid.findMinimumMoves

    if (expectedNumSteps == -1)
      assert(moves == -1)
    else {
      //println("solution = " + solver.solution.map(_.currentPosition).mkString(", "))
      //println("solution = " + solver.solutionTransitions.get.mkString(", "))
      assertResult(expectedNumSteps) { moves }
    }
  }
}