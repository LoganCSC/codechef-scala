package hackerrank.superreducestring.countingvalleys

import hackarrank.superreducedstring.Solution
import org.scalatest.FunSuite

/**
  * @author Barry Becker
  */
class SolutionTest extends FunSuite {
  test("test reduce strings") {
    assert(Solution.reduce("UDDDDUUUU") == "U")
    assert(Solution.reduce("UDDDDUUUUU") == "")
    assert(Solution.reduce("abc") == "abc")
    assert(Solution.reduce("aabc") == "bc")
    assert(Solution.reduce("abbc") == "ac")
    assert(Solution.reduce("abcc") == "ab")
    assert(Solution.reduce("abccb") == "a")
  }
}