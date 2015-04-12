package easy.fctrl2

object Main {
  def main(args: Array[String]) {
    val testCount = readInt()
    for (i <- 0 until testCount) {
      println(factorial(readInt()))
    }
  }

  def factorial(i: Int): String = {
    var r = BigInt(1)
    for (x <- 1 to i) {
      r *= x
    }

    r.toString()
  }
}