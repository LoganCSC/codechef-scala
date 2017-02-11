package usaco.buylower

import org.scalatest.FunSuite

/**
  * @author Barry Becker
  */
class SolutionTest extends FunSuite {
  test("test buy lower") {
    assertResult("4 2") {
      new BuyLowerSolver(Seq(68, 69, 54, 64, 68, 64, 70, 67, 78, 62, 98, 87)).solve()
    }

  }
}