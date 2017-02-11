package usaco.buylower

import org.scalatest.FunSuite

/**
  * @author Barry Becker
  */
class ResultExtractorTest extends FunSuite {
  test("one element") {
    assertResult("1 1") {
      new ResultExtractorOld(List(List(List(1)))).getResult
    }
  }

  test("2 element increasing") {
    assertResult("1 2") {
      new ResultExtractorOld(List(List(List(2, 1)))).getResult
    }
  }

  test("2 element decreasing") {
    assertResult("2 1") {
      new ResultExtractorOld(List(List(List(1), List(2)))).getResult
    }
  }

  test("example from problem") {
    assertResult("2 4") {
      new ResultExtractorOld(List(List(List(4, 3), List(5, 6)))).getResult
    }
  }

  test("my tough case") {
    assertResult("3 39") {
      new ResultExtractorOld(List(
        List(List(1, 2, 3), List(4, 5, 6), List(7, 8, 9)), // 27
        List(List(1), List(2, 3, 4, 5), List(6, 7, 8)))  // 12
      ).getResult
    }
  }

}