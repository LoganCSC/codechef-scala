package easy.tsort

import org.scalatest.FunSuite

class Main$Test extends FunSuite {
  test("testQuickSort") {
    assert(Main.quickSort(List(4, 3, 5, 1, 8, 7)) == List(1, 3, 4, 5, 7, 8))
  }
}