package beginner.plindtst

import scala.io.StdIn

/**
  * See https://www.codechef.com/problems/LISTNUM
  */
object Solution extends App {

  def isPalindrome(str: String): Boolean = {
    str == str.reverse
  }

  val num = StdIn.readInt()
  Range(0, num).foreach(str => println(isPalindrome(StdIn.readLine())))
}