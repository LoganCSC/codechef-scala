package usaco.subseqsummingtosevens

import usaco.subseqsummingtosevens.Solution
import org.scalatest.FunSuite

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
}