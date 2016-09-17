package easy.holes

import scala.io.StdIn

/**
 * http://www.codechef.com/problems/HOLES
 */
object Main {
  def main(args: Array[String]) {
    val testCount = StdIn.readInt()
    for (i <- 0 until testCount) {
      println(countHoles(StdIn.readLine().toCharArray))
    }
  }

  def countHoles(chars: Array[Char]): Int = chars match {
    case Array() => 0
    case _ => countHoles(chars.tail) + (chars.head match {
      case 'Q' | 'R' | 'O' | 'P' | 'A' | 'D' => 1
      case 'B' => 2
      case _ => 0
    })
  }
}