package codeforces.igorandwaytowork

import scala.io.StdIn

/**
  * http://codeforces.com/group/Jr4sdXmPmx/contest/218941/problem/I
  */
object Solution extends App {

  def readMap(): CityMap = {
    val rows = StdIn.readLine.split(" ").head.toInt
    val lines: Seq[String] = for (i <- 0 until rows) yield StdIn.readLine()
    CityMap(lines)
  }

  val map = readMap()
  println(if (new DfsSolver().isSolvable(map)) "YES" else "NO")
}