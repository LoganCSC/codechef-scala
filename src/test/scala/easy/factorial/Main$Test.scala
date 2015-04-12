package easy.factorial

import org.scalatest.{FlatSpec, Matchers}

class Main$Test extends FlatSpec with Matchers {
  it should "return correct countTrailingZeros(n)" in {
    assert(Main.countTrailingZeros(3) == 0)
    assert(Main.countTrailingZeros(60) == 14)
    assert(Main.countTrailingZeros(100) == 24)
    assert(Main.countTrailingZeros(1024) == 253)
    assert(Main.countTrailingZeros(23456) == 5861)
    assert(Main.countTrailingZeros(8735373) == 2183837)
  }
}