package hackerrank.chocolatebar

import hackarrank.chocolatebar.ChocolateBar
import org.scalatest.FunSuite

import scala.util.Random

/**
  * @author Barry Becker
  */
class SolutionTest extends FunSuite {

  test("simple 2*3 bar with upper left cell having raisins") {
    val bar = ChocolateBar(Array(Array(1, 0, 0), Array(0, 0, 0)))
    assertResult(2) { bar.findMinBreaksToIsolateRaisins() }
  }

  test("Bar with no raisins") {
    val bar = ChocolateBar(Array(Array(0, 0, 0), Array(0, 0, 0)))
    assertResult(0) { bar.findMinBreaksToIsolateRaisins() }
  }

  test("3x3 Bar with 1 raisin cell in the middle") {
    val bar = ChocolateBar(Array(Array(0, 0, 0), Array(0, 1, 0), Array(0, 0, 0)))
    assertResult(4) { bar.findMinBreaksToIsolateRaisins() }
  }

  test("10x20 with two adjacent raisin cells") {
    val bar = createBar(10, 20, Seq((3,4), (4, 4)))
    assertResult(4) { bar.findMinBreaksToIsolateRaisins() }
  }

  /**
    * xx.x
    * ...x
    * ...x
    * ...x
    * ..xx
    * ..xx
    */
  test("6x4 homework example case") {
    val bar = createBar(6, 4,
      Seq((0, 0), (0, 1), (0, 3), (1, 3), (2, 3), (3, 3), (4, 2), (4, 3), (5, 2), (5, 3))
    )
    assertResult(4) { bar.findMinBreaksToIsolateRaisins() }
  }

  /*
   *   ........
   *   ........
   *   ........
   *   ....xx..
   *   ........
   *   ........
   *   ........
   *   ... ..x.
   */
  test("8x8 with 3 raisin cells") {
    val bar = createBar(8, 8, Seq((3,4), (3, 5), (7, 6)))
    assertResult(6) { bar.findMinBreaksToIsolateRaisins() }
  }

  /*
   *   ........
   *   ........
   *   ........
   *   ....xx..
   *   ........
   *   ........
   *   ........
   *   ......x.
   *   ........
   */
  test("9x8 with 3 raisin cells") {
    val bar = createBar(9, 8, Seq((3,4), (3, 5), (7, 6)))
    assertResult(7) { bar.findMinBreaksToIsolateRaisins() }
  }

  test("Large 300, 400 with 10 raisin cells") {
    val bar = createBar(300, 400, Seq((3,4), (3, 5), (7, 6), (8, 6), (9, 6),
      (100, 101), (101, 101), (100, 102), (101, 102), (234, 345)))
    assertResult(15) { bar.findMinBreaksToIsolateRaisins() }
  }

  test("Performance test (50x50, 1% raisins)") {
    val bar = createRandomBar(50, 50, 0.01)
    assertResult(80) { bar.findMinBreaksToIsolateRaisins() }
  }

  test("Performance test (50x50, 2% raisins)") {
    val bar = createRandomBar(50, 50, 0.02)
    assertResult(128) { bar.findMinBreaksToIsolateRaisins() }
  }

  test("Performance test (50x50, 4% raisins)") {
    val bar = createRandomBar(50, 50, 0.04)
    assertResult(226) { bar.findMinBreaksToIsolateRaisins() }
  }

  // With 1, 2, 4, 50, and 99% raisin density
  // the respective performance numbers are 2s, 9s, 28s, 44s, 1s.
  test("Bill's performance test (50x50, 50% raisins)") {
    val bar = createRandomBar(50, 50)
    assertResult(1098) { bar.findMinBreaksToIsolateRaisins() }
  }

  test("Performance test (50x50,99% raisins)") {
    val bar = createRandomBar(50, 50, 0.99)
    assertResult(57) { bar.findMinBreaksToIsolateRaisins() }
  }

  def createBar(numRows: Int, numCols: Int, raisinCells: Seq[(Int, Int)]): ChocolateBar = {
    val cells = Array.fill[Byte](numRows, numCols)(0)
    for (rcell <- raisinCells)
      cells(rcell._1)(rcell._2) = 1
    ChocolateBar(cells)
  }

  def createRandomBar(numRows: Int, numCols: Int, raisinFraction: Double = 0.5): ChocolateBar = {
    val rnd = new Random(1)
    val cells = Array.fill[Byte](numRows, numCols)(if (rnd.nextDouble() < raisinFraction)  1 else 0)
    //println(cells.map(r => r.mkString(", ")).mkString("\n"))
    ChocolateBar(cells)
  }
}
