package hackerrank.largestrectangle

import hackarrank.countingvalleys.Solution
import hackarrank.largestrectangle.Region
import org.scalatest.FunSuite

/**
  * @author Barry Becker
  */
class SolutionTest extends FunSuite {

  test("test largest rectangle for simple ascending") {
    val region = new Region(Array(1, 2, 3, 4, 5))
    assert(region.findMaxArea() == 9)
  }

  test("test largest rectangle for simple descending") {
    val region = new Region(Array(5, 4, 3, 2, 1))
    assert(region.findMaxArea() == 9)
  }

  test("test largest rectangle for simple pyramid") {
    val region = new Region(Array(2, 3, 4, 5, 4, 3, 2, 1))
    assert(region.findMaxArea() == 9)
  }

  test("test largest rectangle for V") {
    val region = new Region(Array(5, 4, 3, 2, 1, 2, 3, 4))
    assert(region.findMaxArea() == 9)
  }

  test("test largest rectangle for W") {
    val region = new Region(Array(5, 3, 1, 2, 6, 5, 3, 4, 7))
    assert(region.findMaxArea() == 9)
  }

  test("test largest rectangle for M") {
    val region = new Region(Array(2, 3, 5, 3, 6, 5, 4, 3))
    assert(region.findMaxArea() == 9)
  }
}