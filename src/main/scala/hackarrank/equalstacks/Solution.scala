package hackarrank.equalstacks

import scala.io.StdIn

/**
 * https://www.hackerrank.com/contests/compsci-club4/challenges/equal-stacks
 */
object Solution {

  def main(args: Array[String]) {
    val stackCounts = StdIn.readLine()
    val cylinderHeights: Seq[Array[Int]] = for (n <- stackCounts) yield {
      StdIn.readLine().split(" ").map(_.toInt)
    }
    val stacks = new CylinderStackList(cylinderHeights)


    println(stacks.getMaxHeight)
  }
}