package codequest2018.p18dominatingdisney

import scala.io.StdIn


/**
  * @author Barry Becker
  */
object Solution extends App {

  val num = StdIn.readInt()
  for (i <- 0 until num) {
    val weights = StdIn.readLine().split(' ').map(_.toInt)
    println(new DisneyPathFinder(weights).findShortestPath())
  }

}
