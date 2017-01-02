package hackerrank.castleonthegrid

import common.Location
import hackarrank.castleonthegrid.astarsolution.AStarCastleSolver
import hackarrank.castleonthegrid2.Grid
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
    verifySolution(start = Location(0, 0), goal = Location(0, 2), matrix, 3)
    verifySolution(start = Location(0, 2), goal = Location(0, 0), matrix, 3)
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

  test("test goal same as start") {
    val matrix = Array(
      ".X.".toArray,
      ".X.".toArray,
      "...".toArray
    )
    verifySolution(start = Location(1, 0), goal = Location(1, 0), matrix, 0)
  }

  test("test reach goal in one step") {
    val matrix = Array(
      ".X.".toArray,
      ".X.".toArray,
      "...".toArray
    )
    verifySolution(start = Location(2, 0), goal = Location(2, 2), matrix, 1)
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

  test("test case for big grid") {

    val matrix = Array(
      "..XXX...................XXXXX.".toArray,
      "...XXXXX....................X.".toArray,
      "X.X.X.XXX.X...................".toArray,
      "X.........................X.X.".toArray,
      "X.XX..................XX.X.X..".toArray,
      "X..X..............X..X......X.".toArray,
      "...X.....X......X......X......".toArray,
      "X....................XXXXXX.X.".toArray,
      ".........X.......X............".toArray,
      "XXXXXXXXXX....................".toArray,
      "..XXX...................XXXXX.".toArray,
      "...XXXXX....................X.".toArray,
      "X.X.X.XXX.X...................".toArray,
      "X.........................X.X.".toArray,
      "X.XX..................XX.X.X..".toArray,
      "X..X..............X..X......X.".toArray,
      "...X.....X......X......X......".toArray,
      "X....................XXXXXX.X.".toArray,
      ".........X.......X............".toArray,
      "XXXXXXXXXX....................".toArray,
      "..XXX...................XXXXX.".toArray,
      "...XXXXX.....................X".toArray,
      "X.X...XXX.X...................".toArray,
      "X.........................X.X.".toArray,
      "X.XX..................XX.X.X..".toArray,
      "X..X..............X..X......X.".toArray,
      "...X.....X......X......X......".toArray,
      "X....................XXXXXX.X.".toArray,
      ".........X.......X............".toArray,
      ".........X.......X............".toArray
    )
    verifySolution(start = Location(0, 0), goal = Location(29, 29), matrix, 5)
    verifySolution(start = Location(29, 29), goal = Location(0, 0), matrix, 5)
  }

  test("test case for mostly blank") {

    val matrix = Array(
      "..............................".toArray,
      "..............................".toArray,
      "..............................".toArray,
      "..............................".toArray,
      "..............................".toArray,
      "..............................".toArray,
      "..............................".toArray,
      "..............................".toArray,
      "..............................".toArray,
      "..............................".toArray,
      "..............................".toArray,
      "..............................".toArray,
      "..............................".toArray,
      "..............................".toArray,
      "..............................".toArray,
      "..............................".toArray,
      "..............................".toArray,
      "..............................".toArray,
      "..............................".toArray,
      "..............................".toArray,
      "..............................".toArray,
      "..............................".toArray,
      "..............................".toArray,
      "..............................".toArray,
      "..............................".toArray,
      "..............................".toArray,
      "..............................".toArray,
      "..............................".toArray,
      "..............................".toArray,
      ".........X.......X............".toArray
    )
    verifySolution(start = Location(0, 0), goal = Location(29, 29), matrix, 2)
    verifySolution(start = Location(29, 29), goal = Location(0, 0), matrix, 2)
  }

  test("test case for on C") {

    val matrix = Array(
      "..............................".toArray,
      "..............................".toArray,
      "..........XXXXXXXXX...........".toArray,
      "........XX..........XX........".toArray,
      ".......X..............X.......".toArray,
      "......X................X......".toArray,
      "......X.......................".toArray,
      "......X.......................".toArray,
      ".......X..............X.......".toArray,
      "........X............X........".toArray,
      ".........XX........XX.........".toArray,
      "...........XXXXXXXX...........".toArray,
      "..............................".toArray,
      "..............................".toArray,
      "..............................".toArray,
      "..............................".toArray,
      "..............................".toArray,
      "..............................".toArray,
      "..............................".toArray,
      "..............................".toArray,
      "..............................".toArray,
      "..............................".toArray,
      "..............................".toArray,
      "..............................".toArray,
      "..............................".toArray,
      "..............................".toArray,
      "..............................".toArray,
      "..............................".toArray,
      "..............................".toArray,
      ".........X.......X............".toArray
    )
    verifySolution(start = Location(5, 8), goal = Location(6, 2), matrix, 4)
    verifySolution(start = Location(6, 2), goal = Location(5, 8), matrix, 4)
  }


  // verify that both aproaches to solving work.
  def verifySolution(start: Location,
                     goal: Location, matrix: Array[Array[Char]], expectedNumSteps: Int): Unit = {
    verifySolutionUsingAStar(start, goal, matrix, expectedNumSteps)
    verifySolutionUsingHandTuned(start, goal, matrix, expectedNumSteps)
  }

  def verifySolutionUsingAStar(start: Location,
                     goal: Location, matrix: Array[Array[Char]], expectedNumSteps: Int): Unit = {

    val grid = new hackarrank.castleonthegrid.Grid(start, goal, matrix)
    val board = new Board(grid, start)
    val solver = new AStarCastleSolver(board)
    val moves: Int = solver.moves

    if (expectedNumSteps == -1)
      assert(moves == -1)
    else {
      assertResult(expectedNumSteps, "Failed to find min. Grid = \n" + grid) { moves }
    }
  }

  def verifySolutionUsingHandTuned(start: Location,
                     goal: Location, matrix: Array[Array[Char]], expectedNumSteps: Int): Unit = {

    val grid = new hackarrank.castleonthegrid2.Grid(start, goal, matrix)
    val moves = grid.findMinimumMoves

    if (expectedNumSteps == -1)
      assert(moves == -1)
    else {
      //println("solution = " + solver.solution.map(_.currentPosition).mkString(", "))
      //println("solution = " + solver.solutionTransitions.get.mkString(", "))
      assertResult(expectedNumSteps, "Failed to find min. Grid = \n" + grid) { moves }
    }
  }
}