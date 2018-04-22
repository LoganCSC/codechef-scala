package codequest2018.p18dominatingdisney

import org.scalatest.FunSuite


/**
  * @author Barry Becker
  */
class SolutionSuite extends FunSuite {
  test("test find shortest path") {
    assertResult("1 2 4 6 6") { new DisneyPathFinder(Seq(5, 10, 7, 6, 2, 4, 12)).findShortestPath() }
  }

}