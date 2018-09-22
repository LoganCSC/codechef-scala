package codeforces.ilyaqueries

import scala.io.StdIn

/**
  * http://codeforces.com/group/Jr4sdXmPmx/contest/218941/problem/H
  * This version hits time limit exceeded on test case 10
  */
object Solution extends App {

  def processPattern(str: String): Array[Int] = {
    val len = str.length - 1
    val a = for (i <- 0 until len) yield if (str(i) == str(i + 1)) 1 else 0
    val aa = Array.ofDim[Int](len)
    aa(0) = a(0)

    for (i <- 1 until len) {
      aa(i) = aa(i - 1) + a(i)
    }
    aa
  }

  def executeQueries(num: Int, arr: Array[Int]): Unit = {
    for (i <- 0 until num) {
      val values = StdIn.readLine().split(" ")
      val (left, right) = (values(0).toInt, values(1).toInt)
      if (left == 1)
        println(arr(right - 2))
      else
        println(arr(right - 2) - arr(left - 2))
    }
  }

  val pattern = StdIn.readLine()
  val numQueries = StdIn.readInt()
  val arr = processPattern(pattern)
  executeQueries(numQueries, arr)
}