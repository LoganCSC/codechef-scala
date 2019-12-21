package hackerrank.matchingparens

import hackarrank.matchingparens.{ParenFinder1, ParenFinder2, Solution}
import org.scalatest.FunSuite
import testsupport.TestSupport.strip


class SolutionTest extends FunSuite {

  val pf = new ParenFinder2()

  test("test n = 2") {
    verifyCombos(2, Seq("(())", "()()") )
  }

  test("test n = 3") { // 5 combos
    verifyCombos(3, Seq(
      "((()))",
      "(()())",
      "(())()",
      "()(())",
      "()()()"
    ))
  }

  test("test n = 4") {  // 14 combos
    verifyCombos(4, Seq(
      "(((())))",
      "((()()))",
      "((())())",
      "((()))()",
      "(()(()))",
      "(()()())",
      "(()())()",
      "(())(())",
      "(())()()",
      "()((()))",
      "()(()())",
      "()(())()",
      "()()(())",
      "()()()()"
    ))
  }



  test("test n = 5") { // 42 combos  (initially got 37)
    verifyCombos(5, Seq(
      "((((()))))",
      "(((()())))",
      "(((())()))",
      "(((()))())",
      "(((())))()",
      "((()(())))",
      "((()()()))",
      "((()())())",
      "((()()))()",
      "((())(()))",
      "((())()())",
      "((())())()",
      "((()))(())",
      "((()))()()",
      "(()((())))",
      "(()(()()))",
      "(()(())())",
      "(()(()))()",
      "(()()(()))",
      "(()()()())",
      "(()()())()",
      "(()())(())",
      "(()())()()",
      "(())((()))",
      "(())(()())",
      "(())(())()",
      "(())()(())",
      "(())()()()",
      "()(((())))",
      "()((()()))",
      "()((())())",
      "()((()))()",
      "()(()(()))",
      "()(()()())",
      "()(()())()",
      "()(())(())",
      "()(())()()",
      "()()((()))",
      "()()(()())",
      "()()(())()",
      "()()()(())",
      "()()()()()"
    ))
  }

  test("test n = 6") {
    verifyNum(6, 132)
  }
  test("test n = 7") {
    verifyNum(7, 429)
  }
  test("test n = 8") {
    verifyNum(8, 1430)
  }
  test("test n = 9") {
    verifyNum(9, 4862)
  }
  test("test n = 10") {
    verifyNum(10, 16796)
  }
  test("test n = 11") {
    verifyNum(11, 58786)
  }
  test("test n = 12") {
    verifyNum(12, 208012)
  }
  test("test n = 14") {
    val start = System.currentTimeMillis()
    verifyNum(14, 2674440)  // > 2 million combinations
    println("took " + (System.currentTimeMillis() - start) + " milliseconds")
  }
//  test("test n = 15") {
//    val start = System.currentTimeMillis()
//    verifyNum(15, 9694845)  // almost 10 million combinations
//    println("took " + (System.currentTimeMillis() - start) + " milliseconds")
//  }

  def verifyCombos(n: Int, expectedResult: Seq[String]): Unit = {
    val result1 = pf.findParenCombinations(n)
    //val result2 = new ParenFinder2().findParenCombinations(n)
    verifyResult(n, expectedResult, result1, "finder1")
    //verifyResult(n, expectedResult, result2, "finder2")
  }

  def verifyResult(n: Int, expectedResult: Seq[String], actResult: Seq[String], msg: String): Unit = {
    if (expectedResult.length != actResult.length) {
      println(s"Unexpected result for $msg:\n" + actResult.map('"' + _ + '"').mkString(",\n"))
    }
    assertResult(expectedResult.length) { actResult.length }
    assertResult(expectedResult) { actResult }
  }

  def verifyNum(n: Int, expectedNumCombos: Int): Unit = {
    val result2 = pf.findParenCombinations(n)
    assertResult(expectedNumCombos) { result2.length }
  }
}
