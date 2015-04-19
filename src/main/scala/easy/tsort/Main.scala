package easy.tsort

import scala.collection.mutable.ListBuffer

/**
 * http://www.codechef.com/problems/TSORT
 */
object Main {
  def main(args: Array[String]) {
    val testCount = readInt()
    val list = new ListBuffer[Int]()
    for (i <- 0 until testCount) {
      list += readInt()
    }

    val sorted = quickSort(list.toList)
    sorted.foreach(x => {
      println(x)
    })
  }

  /**
   * Credit: http://stackoverflow.com/a/2314540/2563009
   */
  def quickSort[T <% Ordered[T]](list: List[T]): List[T] = {
    list match {
      case Nil => Nil
      case h :: t =>
        val (before, after) = t partition (_ < h)
        quickSort(before) ++ (h :: quickSort(after))
    }
  }
}