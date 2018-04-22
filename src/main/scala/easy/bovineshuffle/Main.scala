package easy.bovineshuffle

import scala.io.StdIn

/**
  * http://usaco.org/index.php?page=viewproblem2&cpid=760
  */
object Main extends App {

  StdIn.readInt() // don't need number on first line
  val shuffle = StdIn.readLine().split(" ").map(_.toInt)
  val cowIds = StdIn.readLine().split(" ")

  val inverseShuffle = Array.ofDim[Int](shuffle.length)
  val ids = Array.ofDim[String](2, shuffle.length)

  for (i <- shuffle.indices) {
    inverseShuffle(shuffle(i) - 1) = i
    ids(0)(i) = cowIds(i)
  }

  var idxs = (0, 1)
  for (s <- 1 to 3) {
    for (i <- inverseShuffle.indices)
      ids(idxs._2)(inverseShuffle(i)) = ids(idxs._1)(i)
    idxs = (idxs._2, idxs._1) // swap arrays each shuffle
  }

  println(ids(idxs._1).mkString("\n"))
}