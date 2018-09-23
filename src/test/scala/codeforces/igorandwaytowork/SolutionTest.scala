package codeforces.igorandwaytowork

import org.scalatest.FunSuite


class SolutionTest extends FunSuite {

  val bfsSolver = new BfsSolver
  val dfsSolver = new DfsSolver

  test("Simple 3x3") {
    verify(Seq(
      ".S..",
      "***.",
      ".T.."), true)
  }

  test("Simple 3x3 (unsolvable)") {
    verify(Seq(
      ".S..",
      "****",
      ".T.."), false)
  }

  test("Simple 5x5") {
    verify(Seq(
      "..S..",
      "****.",
      "T....",
      "****.",
      "....."), true)
  }

  test("Simple 5x5 (unsolvable)") {
    verify(Seq(
      "..S..",
      "****.",
      ".....",
      ".****",
      "..T.."), false)
  }

  def verify(data: Seq[String], expectedResult: Boolean): Unit = {
    val map = new CityMap(data)
    assertResult(expectedResult) {bfsSolver.isSolvable(map)}
    assertResult(expectedResult) {dfsSolver.isSolvable(map)}
  }
}