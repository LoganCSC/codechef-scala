package hackerrank.largestrectangle

import hackarrank.countingvalleys.Solution
import hackarrank.largestrectangle.LinearHeightField
import org.scalatest.FunSuite

/**
  * @author Barry Becker
  */
class SolutionTest extends FunSuite {

  test("test largest rectangle for simple ascending") {
    val region = new LinearHeightField(Array(1, 2, 3, 4, 5))
    assert(region.findMaxArea() == 9)
  }

  test("test largest rectangle for simple descending") {
    val region = new LinearHeightField(Array(5, 4, 3, 2, 1))
    assert(region.findMaxArea() == 9)
  }

  test("test largest rectangle for simple pyramid") {
    val region = new LinearHeightField(Array(2, 3, 4, 5, 4, 3, 2, 1))
    assert(region.findMaxArea() == 14)
  }

  test("test largest rectangle for V") {
    val region = new LinearHeightField(Array(5, 4, 3, 2, 1, 2, 3, 4))
    assert(region.findMaxArea() == 9)
  }

  test("test largest rectangle for W") {
    val region = new LinearHeightField(Array(5, 3, 1, 2, 6, 5, 3, 4, 7))
    assert(region.findMaxArea() == 15)
  }

  test("test largest rectangle for M") {
    val region = new LinearHeightField(Array(2, 3, 5, 3, 6, 5, 4, 3))
    assert(region.findMaxArea() == 21)
  }

  test("test largest rectangle for spike") {
    val region = new LinearHeightField(Array(2, 3, 5, 3, 600, 5, 4, 3))
    assert(region.findMaxArea() == 21)
  }

  test("test largest rectangle for trough") {
    val region = new LinearHeightField(
      Array(20, 30, 25, 15, 3, 60, 5, 4, 30, 30, 20, 20, 9, 1, 1, 1, 1, 8, 7, 10, 20, 30, 30, 28, 25))
    assert(region.findMaxArea() == 100)
  }
}