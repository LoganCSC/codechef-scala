package beginner.listnumb

import scala.io.StdIn

/**
  * See https://www.codechef.com/problems/LISTNUM
  */
object Main extends App {

  val num = StdIn.readInt()
  val nums = for (i <- Range(1, num + 1)) yield i
  println(nums.mkString(" "))
}