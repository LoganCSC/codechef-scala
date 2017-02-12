package usaco.buylower

import org.scalatest.FunSuite

/**
  * @author Barry Becker
  */
class SolutionTest extends FunSuite {

/*
  test("one element") {
    assertResult("1 1") {
      new BuyLowerSolver(Array(1)).solve()
    }
  }

  test("2 element increasing") {
    assertResult("1 2") {
      new BuyLowerSolver(Array(1, 2)).solve()
    }
  }*/

  test("up down") {
    assertResult("4 1") {
      new BuyLowerSolver(Array(3, 4, 5, 2, 1, 4, 3, 6, 2)).solve()
    }
  }
/*
  test("2 element decreasing") {
    assertResult("2 1") {
      new BuyLowerSolver(Array(2, 1)).solve()
    }
  }

  test("5 element increasing (with dupe)") {
    assertResult("1 4") {
      new BuyLowerSolver(Array(1, 2, 3, 3, 4)).solve()
    }
  }

  test("5 element decreasing (with dupe") {
    assertResult("4 1") {
      new BuyLowerSolver(Array(4, 3, 3, 2, 1)).solve()
    }
  }

  test("4 element increasing") {
    assertResult("1 4") {
      new BuyLowerSolver(Array(1, 2, 3, 4)).solve()
    }
  }

  test("5 element increasing, then down") {
    assertResult("2 2") {
      new BuyLowerSolver(Array(1, 2, 3, 4, 2)).solve()
    }
  }

  test("4 element decreasing") {
    assertResult("4 1") {
      new BuyLowerSolver(Array(4, 3, 2,  1)).solve()
    }
  }

  test("example from problem") {
    assertResult("4 2") {
      new BuyLowerSolver(Array(68, 69, 54, 64, 68, 64, 70, 67, 78, 62, 98, 87)).solve()
    }
  }


  test("my tough case") {
    assertResult("4 6") {
      new BuyLowerSolver(Array(10, 20, 30, 25, 35, 15, 55, 45, 27, 40, 60, 35, 7, 8, 70)).solve()
    }
  }
*/
}