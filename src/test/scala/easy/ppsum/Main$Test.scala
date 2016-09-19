package easy.ppsum

import org.scalatest.FunSuite

class Main$Test extends FunSuite {
  test("testPPsum small") {
    assert(Main.ppSum(1, 4) == 10)
    assert(Main.ppSum(2, 3) == 21)
  }

    test("testPPsum big") {
    assert(Main.ppSum(2, 4) == 55)
    assert(Main.ppSum(3, 5) == 7260)
      assert(Main.ppSum(8, 11) == 2350835989367920964L)
  }
}