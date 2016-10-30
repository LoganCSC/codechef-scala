package hackerrank.castleonthegrid

import common.geometry.IntLocation
import hackarrank.castleonthegrid.{Board, CastleSolver, Grid}
import hackarrank.largestrectangle.LinearHeightField
import org.scalatest.FunSuite

import scala.io.StdIn

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

    val start = new IntLocation(0, 0)
    val goal = new IntLocation(0, 2)
    val board = new Board(new Grid(start, goal, matrix), start)
    val solver = new CastleSolver(board)

    assertResult(3) { solver.moves }

  }

}