package easy.chefdetective

import scala.io.StdIn

/**
  * See https://www.codechef.com/problems/CHEFDETE
  */
object Main {

  def main(args: Array[String]) {
    val numMembers = StdIn.readInt() // not used, but must be read
    val reportsList = StdIn.readLine().split(" ").map(_.toInt)
    println(findLeaves(reportsList))
  }

  def findLeaves(reportsList: Array[Int]): String =  {
    val memberSubordinates = Array.fill(reportsList.length) {0}
    reportsList.filter(_ > 0).foreach{
      report => memberSubordinates(report - 1) += 1
    }
    memberSubordinates.zipWithIndex.filter(p => p._1 == 0).map(_._2 + 1).mkString(" ")
  }
}