package usaco.buylower

import org.scalatest.FunSuite

/**
  * @author Barry Becker
  */
class SolutionTest extends FunSuite {
/*
  test("one element") {
    assertResult("1 1") {
      new BuyLowerSolver(Seq(1)).solve()
    }
  }*/

  test("2 element increasing") {
    assertResult("1 2") {
      new BuyLowerSolver(Seq(1, 2)).solve()
    }
  }
/*
  test("2 element decreasing") {
    assertResult("2 1") {
      new BuyLowerSolver(Seq(2, 1)).solve()
    }
  }

  test("example from problem") {
    assertResult("4 2") {
      new BuyLowerSolver(Seq(68, 69, 54, 64, 68, 64, 70, 67, 78, 62, 98, 87)).solve()
    }
  }

  test("my tough case") {
    assertResult("4 6") {
      new BuyLowerSolver(Seq(10, 20, 30, 25, 35, 15, 55, 45, 27, 40, 60, 35, 7, 8, 70)).solve()
    }
  }
*/
}