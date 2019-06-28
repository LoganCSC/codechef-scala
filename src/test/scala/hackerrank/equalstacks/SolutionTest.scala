package hackerrank.equalstacks

import hackarrank.equalstacks
import hackarrank.equalstacks2
import org.scalatest.FunSuite

import scala.util.Random
import SolutionTest.MAX_CYL_HT

object SolutionTest {
  val MAX_CYL_HT = 100
}

/**
  * @author Barry Becker
  */
class SolutionTest extends FunSuite {

  test("Given test case from contest") {
    runTest(Seq( Array(3, 2, 1, 1, 1), Array(4, 3, 2), Array(1, 1, 4, 1)), 5)
  }

  test("test equal stacks when 2 stacks") {
    runTest(Seq(Array(3, 2), Array(5, 2)), 2)
  }

  test("test equal stacks when 3 stacks (non 0 result)") {
    runTest(Seq(Array(3, 2, 1, 1, 2, 1), Array(5, 1, 2, 1, 1, 2), Array(1, 2, 3, 5, 2)), 7)
  }

  test("test equal stacks when 3 stacks (0 height result)") {
    runTest(Seq(Array(3, 2, 14, 1, 2, 7, 1), Array(5, 1, 3), Array(1, 2, 3, 5, 32)), 0)
  }

  test("test performance for random stacks of size 100") {
    runTest(createRandomArrays(3, 100), 0)
  }

  test("test performance for random stacks of size 100 with small height") {
    runTest(createRandomArrays(3, 100, 5), 178)
  }

  test("test performance for random stacks of size 1000 with small height") {
    runTest(createRandomArrays(3, 1000, 10), 4991)
  }

  test("test performance for 25 random stacks of size 200000 with small height") {
    runTest(createRandomArrays(25, 200000, 3), 0)
  }

  test("test performance for 20 random stacks of size 500000 with small height") {
    runTest(createRandomArrays(20, 500000, 3), 889376)
  }


  test("test performance for random unequal stacks of max size 1000 with small height") {
    runTest(createUnequalLengthRandomArrays(3, 1000, 5), 1647)
  }

  test("test performance for random unequal stacks of max size 10000 with small height") {
    runTest(createUnequalLengthRandomArrays(3, 10000, 10), 20226)
  }

  test("test performance for 25 random unequal stacks of max size 2000000 with small height") {
    runTest(createUnequalLengthRandomArrays(25, 2000000, 3), 0)
  }

  test("test performance for 20 random unequal stacks of max size 4000000 with small height") {
    runTest(createUnequalLengthRandomArrays(10, 4000000, 3), 571658)
  }

  def runTest(data: Seq[Array[Int]], expMaxEqual: Int): Unit = {
    // approach 1
    /*
    val stacks = new equalstacks.CylinderStackList(data)
    stacks.removeBlocksUntilEqual()
    assert(stacks.allHeightsEqual())
    assertResult(expMaxEqual) {
      stacks.getMaxHeight
    }*/

    // approach 2
    val stacks2 = new equalstacks2.CylinderStackList(data)
    assertResult(expMaxEqual) {
      stacks2.heightAfterRemovingTallestUntilEqual()
    }
  }


  /**
    * @param n num arrays
    * @param m num elements in each array
    * @return 2d array of stack data
    */
  def createRandomArrays(n: Int = 3, m: Int = 10, maxCylinderHt: Int = MAX_CYL_HT): Seq[Array[Int]] = {
    val rnd = new Random(1)
    for (i <- 0 until n)
      yield Array.fill[Int](m)(rnd.nextInt(maxCylinderHt) + 1)
  }

  def createUnequalLengthRandomArrays(n: Int = 3, m: Int = 100, maxCylinderHt: Int = MAX_CYL_HT): Seq[Array[Int]] = {
    val rnd = new Random(1)
    for (i <- 0 until n)
      yield Array.fill[Int](rnd.nextInt(m) + 1)(rnd.nextInt(maxCylinderHt) + 1)
  }
}
