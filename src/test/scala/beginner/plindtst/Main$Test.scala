package beginner.plindtst

import org.scalatest.FunSuite

class Main$Test extends FunSuite {

  test("test palindrome") {
    assert(!Main.isPalindrome("foo"))
    assert(Main.isPalindrome("poop"))
  }
}