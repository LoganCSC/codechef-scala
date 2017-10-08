package hackarrank.equalstacks

import scala.io.StdIn

/**
 * https://www.hackerrank.com/contests/compsci-club4/challenges/equal-stacks
 */
object Solution extends App {

  val stacks = new CylinderStackList(readHeights)
  stacks.removeBlocksUntilEqual()
  println(stacks.getMaxHeight)


  private def readHeights = {
    val stackCounts = StdIn.readLine().split(" ")
    for (n <- stackCounts) yield StdIn.readLine().split(" ").map(_.toInt)
  }
}