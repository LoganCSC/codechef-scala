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
    assert(region.findMaxArea() == 15)
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

  test("test largest rectangle when long run after early spike") {
    val region = new LinearHeightField(
      Array(5, 100, 2, 2, 2, 3, 4, 6, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 8, 7, 7, 7, 7, 10, 7, 6, 5, 4, 3, 2, 1, 1, 1, 1, 2, 3, 4, 10, 11, 12))
    assert(region.findMaxArea() == 140)
  }

  test("test largest rectangle when spike after long run") {
    val region = new LinearHeightField(
      Array(3, 4, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 4, 1, 2, 3, 4, 80, 80, 10, 11))
    assert(region.findMaxArea() == 160)
  }

  test("test largest rectangle when bigger run after long run") {
    val region = new LinearHeightField(
      Array(3, 4, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 4, 1, 2, 3, 4, 20, 20, 20, 9, 11))
    assert(region.findMaxArea() == 60)
  }


  test("test largest rectangle for long field") {
    val region = new LinearHeightField(
      Array(5, 2, 2, 2, 3, 4, 6, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 8, 7, 7, 7, 7, 10, 7, 6, 5, 4, 3, 2, 5, 20, 30, 40, 20,
        1, 1, 1, 1, 2, 3, 4, 40, 40, 10, 11, 12, 2, 3, 4, 6, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 8, 7, 7, 7, 7, 10, 7, 6, 5, 4, 3, 2,
        1, 1, 1, 1, 2, 3, 4, 50, 60, 2, 3, 4, 6, 8, 8, 8, 9, 9, 9, 9, 9, 11, 11, 11, 11, 11, 10, 10, 10, 5, 5, 7, 7, 7, 8, 7,
        7, 7, 10, 7, 6, 5, 4, 3, 2, 20, 3, 4, 5, 6, 7, 8, 9, 10, 10, 11, 11, 12, 13, 14, 15, 10, 5, 1, 1,
        1, 1, 1, 1, 2, 3, 4, 20, 2, 3, 4, 6, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 8, 7, 7, 7, 7, 10, 7, 6, 5, 4, 3, 2, 1, 1, 1, 1, 2, 3, 4, 12))
    assert(region.findMaxArea() == 151)
  }

  test("test largest rectangle for long random  field") {
    //for (i <- Range(0, 100)) { print((Math.random() * 100).toInt + ", ")}
    val region = new LinearHeightField(
      Array(53, 49, 69, 35, 34, 11, 58, 25, 25, 77, 80, 60, 62, 0, 23, 77, 9, 6, 48, 28, 61, 13, 40, 73,
        53, 53, 13, 21, 29, 79, 98, 17, 47, 18, 96, 62, 75, 29, 20, 74, 86, 38, 62, 17, 41, 6, 31, 22, 17,
        7, 43, 79, 1, 15, 65, 69, 80, 67, 59, 58, 79, 52, 62, 85, 80, 32, 52, 35, 12, 65, 82, 97, 45, 16,
        38, 65, 48, 49, 59, 75, 54, 69, 15, 30, 61, 75, 34, 90, 49, 42, 5, 80, 19, 4, 65, 20, 72, 29, 3, 15
      ))
    assert(region.findMaxArea() == 572) // 396)
  }
}