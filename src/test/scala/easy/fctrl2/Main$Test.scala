package easy.fctrl2

import org.scalatest.FunSuite

class Main$Test extends FunSuite {
  test("testFactorial") {
    assert(Main.factorial(1) == "1")
    assert(Main.factorial(3) == "6")
    assert(Main.factorial(5) == "120")
    assert(Main.factorial(10) == "3628800")
    assert(Main.factorial(100) == "93326215443944152681699238856266700490715968264381621468592963895217599993229915608941463976156518286253697920827223758251185210916864000000000000000000000000")
  }
}