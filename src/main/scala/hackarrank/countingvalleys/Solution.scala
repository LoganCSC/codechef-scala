package hackarrank.countingvalleys

import scala.io.StdIn

/**
 * http://www.codechef.com/problems/TLG
 */
object Solution {

  def main(args: Array[String]) {
    //println(io.Source.stdin.getLines().take(2).map(_.toInt).sum)
    val num = StdIn.readInt()
    val steps = StdIn.readLine()
    println(countValleys(steps))
  }

  def countValleys(stepsStr: String): Int = {
    val steps = stepsStr.toList
     var numValleys = 0
    //var aboveSeaLevel = true
    var lastHeight = 0
    var height = 0

    steps.foreach(step => {
      step match {
         case 'U' => height += 1
         case 'D' => height -= 1
      }
      if (height == 0 && lastHeight == -1) numValleys += 1
      lastHeight = height
    })
    numValleys
  }
}