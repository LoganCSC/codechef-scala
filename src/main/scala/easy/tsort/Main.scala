package easy.tsort

import scala.collection.mutable.ListBuffer
import scala.io.StdIn

/**
 * http://www.codechef.com/problems/TSORT
 */
object Main extends App {

  val testCount = StdIn.readInt()
  val list = new ListBuffer[Int]()
  for (i <- 0 until testCount) {
    list += StdIn.readInt()
  }

  val sorted = quickSort(list.toList)
  sorted.foreach(x => {
    println(x)
  })

  /**
   * Credit: http://stackoverflow.com/a/2314540/2563009
   */
  def quickSort[T](list: List[T])(implicit ev$1: T => Ordered[T]): List[T] = {
  //def quickSort[T <% Ordered[T]](list: List[T]): List[T] = {   <- old way
    list match {
      case Nil => Nil
      case h :: t =>
        val (before, after) = t partition (_ < h)
        quickSort(before) ++ (h :: quickSort(after))
    }
  }
}