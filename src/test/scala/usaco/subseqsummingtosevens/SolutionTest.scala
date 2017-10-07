package usaco.subseqsummingtosevens

import usaco.subseqsummingtosevens.Solution
import org.scalatest.FunSuite

import scala.util.Random

/**
  * @author Barry Becker
  */
class SolutionTest extends FunSuite {

  val solver = new SubSequenceFinder(7)

  test("Very short list") {
    assertResult(2) { solver.findLongestSubSequence(Array(1, 1, 6)) }
  }

  test("No sum 7 subsequence") {
    assertResult(0) { solver.findLongestSubSequence(Array(1, 3, 2, 6)) }
  }

  test("Test case from contest") {
    val a = Array(3, 5, 1, 6, 2, 14, 10)
    assertResult(5) { solver.findLongestSubSequence(a) }
  }

  test("My 12 element case") {
    val a = Array(3, 2, 1, 5, 1, 6, 2, 14, 10, 21, 20, 4)
    assertResult(10) { solver.findLongestSubSequence(a) }
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

  private def createRandomSeq(len: Int): Seq[Int] = {
    val rnd = new Random(0)
    for (i <- 0 until len) yield rnd.nextInt(7)
  }
}