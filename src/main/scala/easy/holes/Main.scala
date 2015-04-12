package easy.holes

/**
 * http://www.codechef.com/problems/HOLES
 */
object Main {
  def main(args: Array[String]) {
    val testCount = readInt()
    for (i <- 0 until testCount) {
      println(countHoles(readLine().toCharArray))
    }
  }

  def countHoles(chars: Array[Char]): Int = {
    if (chars.isEmpty) 0
    else {
      countHoles(chars.tail) + (chars.head match {
        case 'Q' | 'R' | 'O' | 'P' | 'A' | 'D' => 1
        case 'B' => 2
        case _ => 0
      })
    }
  }
}