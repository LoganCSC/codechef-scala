package easy.holes

import org.scalatest.FunSuite

class Main$Test extends FunSuite {
  test("testCountHoles") {
    assert(Main.countHoles("CODECHEF".toCharArray) == 2)
    assert(Main.countHoles("DRINKEATCODE".toCharArray) == 5)
  }
}