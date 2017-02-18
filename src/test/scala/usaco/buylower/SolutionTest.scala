package usaco.buylower

import org.scalatest.FunSuite

import scala.io.Source
import scala.util.Random
import SolutionTest.readJudgeProblem


object SolutionTest {
  private val PATH_PREFIX = "/usaco/buylower/"

  def readJudgeProblem(fname: String): Array[Int] =
    Source.fromURL(getClass.getResource(PATH_PREFIX + fname)).getLines().next().split(", ").map(_.toInt)
}

/**
  * @author Barry Becker
  */
class SolutionTest extends FunSuite {

  test("one element") {
    assertResult("1 1") {
      new BuyLowerSolver(Array(1)).solve()
    }
  }

  test("2 element increasing") {
    assertResult("1 2") {
      new BuyLowerSolver(Array(1, 2)).solve()
    }
  }

  test("up down") {
    assertResult("4 1") {
      new BuyLowerSolver(Array(3, 4, 5, 2, 1, 4, 3, 6, 2)).solve()
    }
  }

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

  test("5 element decreasing (with dupe)") {
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

  test("Bill's example") {
    assertResult("4 1") {
      new BuyLowerSolver(Array(44, 32, 3, 29, 28)).solve()
    }
  }

  test("Bill's example 2") {
    assertResult("3 6") {
      new BuyLowerSolver(Array(44, 29, 31, 32, 3, 28)).solve()
    }
  }

  test("Bill's example (with dupes)") {
    assertResult("5 1") {
      new BuyLowerSolver(Array(44, 32, 3, 44, 31, 3, 44, 29, 28)).solve()
    }
  }


  test("example from problem") {
    assertResult("4 2") {
      new BuyLowerSolver(Array(68, 69, 54, 64, 68, 64, 70, 67, 78, 62, 98, 87)).solve()
    }
  }

  test("my tough case") {
    assertResult("5 2") {
      new BuyLowerSolver(Array(10, 20, 30, 25, 35, 15, 55, 45, 27, 40, 60, 35, 7, 8, 70)).solve()
    }
  }

  test("500 element case (random with descending trend)") {
    val array = bigArray(500, ascending = false)
    assertResult("57 422400") {
      new BuyLowerSolver(array).solve()
    }
  }

  test("500 element case (random with ascending trend)") {
    val array = bigArray(500, ascending = true)
    assertResult("22 72") {
      new BuyLowerSolver(array).solve()
    }
  }

  test("1000 element case (random with descending trend)") {
    val array = bigArray(1000, ascending = false)
    assertResult("99 453869568") {
      new BuyLowerSolver(array).solve()
    }
  }

  test("1000 element case (random with ascending trend)") {
    val array = bigArray(1000, ascending = true)
    assertResult("36 541008") {
      new BuyLowerSolver(array).solve()
    }
  }

  test("5000 element case (random with descending trend)") {
    val array = bigArray(5000, ascending = false)
    assertResult("226 692587030588183019520") {
      new BuyLowerSolver(array).solve()
    }
  }

  test("5000 element case (random with ascending trend)") {
    val array = bigArray(5000, ascending = true)
    assertResult("83 145152") {
      new BuyLowerSolver(array).solve()
    }
  }

  def bigArray(size: Int, ascending: Boolean): Array[Int] = {
    val rand = new Random(1)
    val minNum = 10
    var maxNum: Int = if (ascending) minNum else size + minNum
    val array: Array[Int] = Array.fill(size) {
      maxNum += (if (ascending) 1 else -1)
      rand.nextInt(maxNum)
    }
    array
  }

  test("judge 1") {
    assertResult("1 10") {
      new BuyLowerSolver(Array(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)).solve()
    }
  }
  test("judge 2") {
    assertResult("4 2") {
      new BuyLowerSolver(Array(68, 69, 54, 64, 68, 64, 70, 67, 78, 62, 98, 87)).solve()
    }
  }
  test("judge 3") {
    assertResult("5 20") {  // really 5 20
      new BuyLowerSolver(Array(1, 16, 17, 18, 20, 10, 22, 22, 8, 17, 26, 14, 3, 24, 8, 1, 2, 21, 2, 17)).solve()
    }
  }
  test("judge 4") {
    assertResult("36 16") { // 36 16
      new BuyLowerSolver(readJudgeProblem("judge4.data")).solve()
    }
  }
  test("judge 5") {
    assertResult("20 1") {
      new BuyLowerSolver(readJudgeProblem("judge5.data")).solve()
    }
  }
  test("judge 6") {
    assertResult("59 532224") {  // 59 532224
      new BuyLowerSolver(readJudgeProblem("judge6.data")).solve()
    }
  }
  test("judge 7") {
    assertResult("200 1606938044258990275541962092341162602522202993782792835301376") {
      new BuyLowerSolver(readJudgeProblem("judge7.data")).solve()
    }
  }
  test("judge 8") {
    assertResult("83 5253120000") {// 83 5253120000
      new BuyLowerSolver(readJudgeProblem("judge8.data")).solve()
    }
  }
  test("judge 9") {
    assertResult("142 336363353948160") { // 142 336363353948160
      new BuyLowerSolver(readJudgeProblem("judge9.data")).solve()
    }
  }
}