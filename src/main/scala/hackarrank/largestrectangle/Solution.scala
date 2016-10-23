package hackarrank.largestrectangle

import scala.io.StdIn

/**
 * https://www.hackerrank.com/contests/compsci-club4/challenges/largest-rectangle
 */
object Solution {

  def main(args: Array[String]) {
    StdIn.readLine() // don't need first line
    val region = new Region(StdIn.readLine().split(" ").map(_.toInt))
    println(region.findMaxArea())
  }


}