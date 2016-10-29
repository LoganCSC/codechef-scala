package easy.chefdetective

import org.scalatest.FunSuite

class Main$Test extends FunSuite {

  test("testCount4") {
    assert(Main.findLeaves(Array(0, 1, 1, 2, 2, 3)) == "4 5 6")
  }
}