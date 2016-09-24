package beginner.plindtst

import scala.io.StdIn

/**
  * See https://www.codechef.com/problems/LISTNUM
  */
object Main {

  def main(args: Array[String]) {
    val num = StdIn.readInt()
    Range(0, num).foreach(str => println(isPalindrome(StdIn.readLine())))
  }

  def isPalindrome(str: String): Boolean = {
    str == str.reverse
  }
}