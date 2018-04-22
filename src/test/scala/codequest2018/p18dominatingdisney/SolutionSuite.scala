package codequest2018.p18dominatingdisney

import org.scalatest.FunSuite


/**
  * @author Barry Becker
  */
class SolutionSuite extends FunSuite {
  test("test find shortest path case 1") {
    assertResult("1 2 4 6") { new DisneyPathFinder(Seq(5, 10, 7, 6, 2, 4, 12)).findShortestPath() }
  }

  test("test find shortest path case 2") {
    assertResult("7 6 4 2") { new DisneyPathFinder(Seq(15, 8, 20, 7, 20, 5, 10)).findShortestPath() }
  }

  test("test find shortest path case 3") {
    assertResult("1 2 4 6") { new DisneyPathFinder(Seq(4, 8, 10, 6, 6, 8, 10)).findShortestPath() }
  }

}