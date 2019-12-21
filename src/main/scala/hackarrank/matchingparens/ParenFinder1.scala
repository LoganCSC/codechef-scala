package hackarrank.matchingparens

/** this approach does not work unfortunately */
class ParenFinder1 extends ParenFinder {

  def findParenCombinations(n: Int): Seq[String] = {
    if (n <= 0) Seq()
    else findParens(n).toSeq.sorted
  }

  private def findParens(n: Int): Set[String] = {
    if (n == 1) Set("()")
    else {
      var combos: Set[String] = Set()

      if (n % 2 == 0) {
        val parens = findParens(n / 2)
        combos ++= parens.flatMap(s => Set(s + s))
      }
      else if (n > 2) {
        val parens = findParens((n - 1) / 2)
        combos ++= parens.flatMap(s => Set(s + "()" + s))
      }

      val parens = findParens(n - 1)
      combos ++= parens.flatMap(s => Set("()" + s, "(" + s + ")", s + "()"))
      combos
    }
  }
}
