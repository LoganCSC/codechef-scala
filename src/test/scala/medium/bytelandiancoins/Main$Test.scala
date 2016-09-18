package medium.bytelandiancoins

import medium.bytelandiancoins.Main
import org.scalatest.FunSuite

class Main$Test extends FunSuite {

  test("testCount4") {
    assert(Main.getCoinValue(2) == 2)
    assert(Main.getCoinValue(11) == 11)
    assert(Main.getCoinValue(12) == 13)
    assert(Main.getCoinValue(211) == 253)
    assert(Main.getCoinValue(1211) == 1755)
  }

}