package hackerrank.countingvalleys

import easy.tsort.Main
import hackarrank.countingvalleys.Solution
import org.scalatest.FunSuite

/**
  * @author Barry Becker
  */
class SolutionTest extends FunSuite {
  test("test count valleys") {
    assert(Solution.countValleys("UDDDDUUUU") == 1)
    assert(Solution.countValleys("DDDDUUUUUUDDDDDDDUUUUUUUD") == 2)
  }
}