package usaco.subseqsummingtosevens

import usaco.subseqsummingtosevens.Solution
import org.scalatest.FunSuite
import SolutionTest._
import scala.io.{Source, StdIn}
import scala.util.Random

object SolutionTest {
  val FILE_PREFIX = "/usaco/subseqsummingtosevens/"
  val RADIX = 7
}

/**
  * @author Barry Becker
  */
class SolutionTest extends FunSuite {

  val solver = new SubSequenceFinder(RADIX)

  test("Very short list") {
    assertResult(2) { solver.findLongestSubSequence(Array(1, 1, 6)) }
  }

  test("No sum 7 subsequence") {
    assertResult(0) { solver.findLongestSubSequence(Array(1, 3, 2, 6)) }
  }

  test("My 12 element case") {
    val a = Array(3, 2, 1, 5, 1, 6, 2, 14, 10, 21, 20, 4)
    assertResult(10) { solver.findLongestSubSequence(a.map(_ % RADIX)) }
  }

  test("My 100 element case") {
    val a = createRandomSeq(100)
    assertResult(96) { solver.findLongestSubSequence(a) }
  }

  test("My 1000 element case") {
    val a = createRandomSeq(1000)
    assertResult(998) { solver.findLongestSubSequence(a) }
  }

  test("My 10000 element case") {
    val a = createRandomSeq(10000)
    assertResult(9997) { solver.findLongestSubSequence(a) }
  }

  test("My 50000 element case") {
    val a = createRandomSeq(50000)
    assertResult(49998) { solver.findLongestSubSequence(a) }
  }

  test("My 100000 element case") {
    val a = createRandomSeq(100000)
    assertResult(99999) { solver.findLongestSubSequence(a) }
  }

  test("My 1000000 element case") {
    val a = createRandomSeq(1000000)
    assertResult(999999) { solver.findLongestSubSequence(a) }
  }

  test("Test case1 from contest") {
    val a = Array(3, 5, 1, 6, 2, 14, 10)
    assertResult(5) { solver.findLongestSubSequence(a.map(_ % RADIX)) }
  }

  test("Test case2 from contest") { runContestTest(2, 18)}
  test("Test case3 from contest") { runContestTest(3, 41)}
  test("Test case4 from contest") { runContestTest(4, 175)}
  test("Test case5 from contest") { runContestTest(5, 675) }
  test("Test case6 from contest") { runContestTest(6, 2341) }
  test("Test case7 from contest") { runContestTest(7, 5008) }
  test("Test case8 from contest") { runContestTest(8, 8341) }
  test("Test case9 from contest") { runContestTest(9, 13341) }
  test("Test case10 from contest") { runContestTest(10, 16675) }

  private def runContestTest(num: Int, expResult: Int): Unit = {
    val a = readData(FILE_PREFIX + num +".in")
    assertResult(expResult) { solver.findLongestSubSequence(a) }
  }

  private def createRandomSeq(len: Int): Seq[Int] = {
    val rnd = new Random(0)
    for (i <- 0 until len) yield rnd.nextInt(RADIX)
  }

  private def readData(fname: String): Seq[Int] = {
    val source = Source.fromURL(getClass.getResource(fname))
    source.getLines.drop(1).map(_.toInt % RADIX).toArray
  }
}