package hackarrank.matchingparens

import scala.collection.mutable.ListBuffer

class ParenFinder {

  var list: ListBuffer[String] = ListBuffer()

  def findParenCombinations(n: Int): Seq[String] = {
    list.clear()
    if (n > 1) findParens(n, n, "")
    list.sorted
  }

  private def findParens(open: Int, close: Int, str: String): Unit = {
    if (open == 0 && close == 0) list.append(str)
    else {
      if (open < close && close > 0) findParens(open, close - 1, str + ")")
      if (open > 0) findParens(open - 1, close, str + "(")
    }
  }
}
