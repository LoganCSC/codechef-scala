package hackarrank.matchingparens

import scala.collection.mutable
import scala.io.StdIn

/**
 * Find all possible valid configurations of N matched parenthesis.
 * For example if N = 3, then the program should output:
 * ()()()
 * ()(())
 * (()())
 * (())()
 * ((()))
 */
object Solution extends App {

    val pf = new ParenFinder()
    println(pf.findParenCombinations(3).mkString("\n"))
}