package easy.chefluck

import org.scalatest.FunSuite

class Main$Test extends FunSuite {
  test("testCount4") {
    assert(Main.count4(7) == 7)
    assert(Main.count4(4) == 0)
    assert(Main.count4(11) == 7)
    assert(Main.count4(1) == -1)
    assert(Main.count4(15) == 7)
  }
}