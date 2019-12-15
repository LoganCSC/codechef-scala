package hackarrank.matchingparens

/** Note: This approach does not work because it will miss (())(()), for example, in the n= 4 case. */
class ParenFinder1 extends ParenFinder {

  def findParenCombinations(n: Int): Seq[String] = {
    if (n <= 0) Seq()
    else findParens(n).toSeq.sorted
  }

  private def findParens(n: Int): Set[String] = {
    if (n == 1) Set("()")
    else {
      val parens = findParens(n - 1)
      parens.flatMap(s => Set("()" + s, "(" + s + ")", s + "()"))
    }
  }
}
