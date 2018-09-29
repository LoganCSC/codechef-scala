package codeforces.ilyaqueries

object FastSolution extends App {

  def solve(line: String): Array[Int] = {
    val intArray: Array[Int] = new Array[Int](line.length())
    intArray(0) = 0
    for (i <- 1 until line.length()) {
      if (line.charAt(i - 1) == line.charAt(i)) {
        intArray(i) = intArray(i - 1) + 1
      } else {
        intArray(i) = intArray(i - 1)
      }
    }
    intArray
  }

  val lines = scala.io.Source.stdin.getLines()
  val line = lines.next()
  val arr: Array[Int] = solve(line)
  val n = lines.next().toInt
  val builder: StringBuilder = StringBuilder.newBuilder
  for (i <- 0 until n) {
    val Array(p, q) = lines.next().split(" ").map(_.toInt - 1)
    builder.append(arr(q) - arr(p)).append("\n")
  }
  print(builder.toString())
}