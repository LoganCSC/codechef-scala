package hackarrank.birthdaycakecandles

/**
 * http://www.codechef.com/problems/TLG
 */
object Solution extends App {

  val sc = new java.util.Scanner (System.in)
  val n = sc.nextInt()
  val height = new Array[Int](n)
  for (height_i <- 0 until n) {
     height(height_i) = sc.nextInt()
  }

  val max = height.max
  println(height.count(_ == max))
}