package easy.tlg

import scala.io.StdIn

/**
 * http://www.codechef.com/problems/TLG
 */
object Main extends App {

  val testCount = StdIn.readInt()
  var lead1 = 0
  var lead2 = 0
  var prevScore1 = 0
  var prevScore2 = 0
  for (i <- 0 until testCount) {
    val scores = StdIn.readLine().split(" ")
    val score1 = scores(0).toInt + prevScore1
    prevScore1 = score1

    val score2 = scores(1).toInt + prevScore2
    prevScore2 = score2

    val diff = score1 - score2
    if (diff > 0 && diff > lead1)
      lead1 = diff
    else if (diff < 0 && -diff > lead2)
      lead2 = -diff
  }

  if (lead1 > lead2) println("1 %d".format(lead1))
  else println("2 %d".format(lead2))
}