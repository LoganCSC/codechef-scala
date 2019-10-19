package hackerrank.triplesum

import hackarrank.triplesum.TripleSum
import org.scalatest.FunSuite

import scala.util.Random

/**
  * @author Barry Becker
  */
class SolutionTest extends FunSuite {

  test("simple") {
    assert(TripleSum(Array(4, 2, 3, 4, 1, 2)).exists)
  }

  test("simple case where no solution") {
    assert(!TripleSum(Array(4, 4, 3, 4, 5, 2)).exists)
  }

  test("typical case") {
    assert(TripleSum(Array(5, 7, 1, 5, 4, 2, 3, 4, 1, 2)).exists)
  }

  test("typical case where no solution") {
    assert(!TripleSum(Array(9, 7, 9, 5, 7, 8, 3, 4, 10, 4)).exists)
  }

  test("case where solution but 2 values duplicated") {
    assert(TripleSum(Array(4, 4, 3, 4, 8, 1, 9, 9, 9)).exists)
  }

  test("case where solution but 3 values duplicated") {
    assert(TripleSum(Array(4, 10, 11, 4, 3, 4, 8, 1, 9, 9, 7, 9)).exists)
  }

  test("case where many duplicates and no solution") {
    assert(!TripleSum(Array(5, 5, 11, 5, 3, 5, 8, 5, 9, 9, 7, 9)).exists)
  }

  test("counter example for my incorrect nlogn implementation") {
    assert(TripleSum(Array(1, 1, 1, 1, 1, 1, 3, 3, 3, 7, 8, 8, 9, 10, 14)).exists)
  }


  test("case for random list of 10 values") {
    val a = makeRandomArray(10, new Random(1))
    assert(!TripleSum(a).exists)
  }

  test("case for random list of 100 values") {
    val a = makeRandomArray(100, new Random(1))
    assert(TripleSum(a).exists)
  }

  test("case for random list of 1000 values") {
    val a = makeRandomArray(1000, new Random(1))
    assert(TripleSum(a).exists)
  }

  test("case for random list of 10000 values") {
    val a = makeRandomArray(10000, new Random(1))
    assert(TripleSum(a).exists)
  }

  test("case for random list of 100000 values") {
    val a = makeRandomArray(100000, new Random(1))
    assert(TripleSum(a).exists)
  }

  test("case for random list of 1000000 values") {
    val a = makeRandomArray(1000000, new Random(1))
    assert(TripleSum(a).exists)
  }

  def makeRandomArray(n: Int, rnd: Random): Array[Int] = Array.fill(n)(rnd.nextInt(n) + 1)
}
