package hackerrank.antiprimenumbers

import hackarrank.antiprimenumbers.Solution
import org.scalatest.FunSuite

/**
  * @author Barry Becker
  */
class SolutionTest extends FunSuite {
  test("test num factors (basic cases)") {
    assert(Solution.getNumFactors(1) == 1)
    assert(Solution.getNumFactors(2) == 2)
    assert(Solution.getNumFactors(3) == 2)
    assert(Solution.getNumFactors(6) == 4)
    assert(Solution.getNumFactors(8) == 4)    // 6?
    assert(Solution.getNumFactors(10) == 4)
    assert(Solution.getNumFactors(12) == 6)
  }


  test("test num factors (simple cases)") {
    assert(Solution.getNumFactors(18) == 6)
    assert(Solution.getNumFactors(21) == 4)
    assert(Solution.getNumFactors(24) == 8)
    assert(Solution.getNumFactors(36) == 10)
    assert(Solution.getNumFactors(48) == 10)
  }

}