package common.search.slidingpuzzle

import java.io.InputStream
import java.util.Scanner


/**
  * Read a puzzle board of any size
  *
  * @author Barry Becker
  */
class BoardReader {
  def read(filename: String): Board = {
    val str: InputStream = getClass.getResourceAsStream("resources/" + filename)
    assert(str != null)
    val in: Scanner = new Scanner(str)
    val sidLen: Int = in.nextInt
    in.nextLine()
    val lines = for (i <- 0 until sidLen) yield in.nextLine()
    //println("BLOCKS = \n" + lines.mkString("\n"))
    val blocks: Array[Array[Int]] = lines.map(_.trim().split("\\s+").map(_.toInt)).toArray
      //= new Array[Array[Int]](sidLen)
    println("BLOCKS = " + blocks.map(_.mkString(", ")).mkString("\n"))

    new Board(blocks)
  }
}
