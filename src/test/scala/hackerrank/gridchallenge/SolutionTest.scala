package hackerrank.gridchallenge

import hackarrank.gridchallenge.Grid
import org.scalatest.FunSuite


/**
  * @author Barry Becker
  */
class SolutionTest extends FunSuite {


  test("test case when has solution") {

    val grid = new Grid(Array(
      "bac".toArray,
      "foo".toArray,
      "zyx".toArray
    ))


    assertResult(true, "Should have been orderable") {
      grid.isSortSolvable
    }
  }


  test("test case when has solution, but lot of equal values") {

    val grid = new Grid(Array(
      "bac".toArray,
      "foo".toArray,
      "ofo".toArray
    ))


    assertResult(true, "Should have been orderable") {
      grid.isSortSolvable
    }
  }

  test("test case when no solution") {

    val grid = new Grid(Array(
      "bzz".toArray,
      "foo".toArray,
      "zyx".toArray
    ))


    assertResult(false, "Should not have been orderable") {
      grid.isSortSolvable
    }
  }
}