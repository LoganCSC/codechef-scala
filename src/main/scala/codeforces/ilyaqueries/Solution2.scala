package codeforces.ilyaqueries

import scala.io.StdIn

/**
  * http://codeforces.com/group/Jr4sdXmPmx/contest/218941/problem/H
  */
object Solution2 extends App {

  /** @return array with int values giving runs of same characters */
  def processPattern(str: String): Array[Int] = {

    val a = Array.ofDim[Int](str.length)
    var total = 0

    for (i <- 1 until str.length) {
      if (str(i) == str(i - 1))
        total += 1
      a(i) = total
    }
    a
  }

  def executeQueries(num: Int, arr: Array[Int]): Unit = {
    for (i <- 0 until num) {
      val Array(left, right) = StdIn.readLine.split(" ").map(_.toInt - 1)
      //println(s"a($right) = ${arr(right)} - a($left) = ${arr(left)}")
      println(arr(right) - arr(left))
    }
  }

  val pattern = StdIn.readLine()
  val numQueries = StdIn.readInt()
  val arr = processPattern(pattern)
  //println(arr.mkString(", "))
  executeQueries(numQueries, arr)
}