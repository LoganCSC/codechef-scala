package hackarrank.superreducedstring

import scala.collection.mutable.ListBuffer
import scala.io.StdIn

/**
 * https://www.hackerrank.com/contests/compsci-club2/challenges/reduced-string
 */
object Solution {

  def main(args: Array[String]) {
    val str = StdIn.readLine()
    val result = reduce(str)
    println(if (result == "") "Empty String" else result)
  }

  /** wald the string, making reductions when possible */
  def reduce(str: String): String = {
    val buf = new ListBuffer[Char]()
    buf.append(str.toList:_*)
    var idx = 0
    while (idx < buf.length - 1 && idx >= 0) {
      if (buf(idx) == buf(idx + 1)) {
        buf.remove(idx, 2)
        if (idx > 0) idx -= 1
      } else {
        idx += 1
      }
    }
    if (idx < 0) "" else buf.mkString("")
  }
}