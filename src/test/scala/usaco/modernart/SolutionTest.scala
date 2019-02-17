package usaco.modernart

import SolutionTest._
import org.scalatest.FunSuite

import scala.io.Source


object SolutionTest {
  private val PATH_PREFIX = "/usaco/modernart/"

  def readJudgeProblem(fname: String): Array[Array[Int]] =
    Source.fromURL(getClass.getResource(PATH_PREFIX + fname)).getLines().drop(1).map(_.toCharArray.map(_.toString.toInt)).toArray
}

class SolutionTest extends FunSuite {

  test("Judge 1") {
    assertResult(1) {
      new ModernArtSolver(readJudgeProblem("judge1.data")).solve()
    }
  }

  test("Judge 2") {
    assertResult(2) {
      new ModernArtSolver(readJudgeProblem("judge2.data")).solve()
    }
  }
}
