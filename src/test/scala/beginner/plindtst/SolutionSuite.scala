package beginner.plindtst

import org.scalatest.FunSuite

class SolutionSuite extends FunSuite {

  test("test palindrome") {
    assert(!Solution.isPalindrome("foo"))
    assert(Solution.isPalindrome("poop"))
  }
}