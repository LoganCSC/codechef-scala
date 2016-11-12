package hackerrank.maximumsubarray

import hackarrank.maximumsubarray.ArrayData
import org.scalatest.FunSuite

/**
  * @author Barry Becker
  */
class SolutionTest extends FunSuite {

  test("test when all positive1") {
    val region = new ArrayData(Array(1, 2, 3, 4, 5))
    assert(region.largestContiguousSubArraySum() == 15)
    assert(region.largestSubArraySum() == 15)
  }

  test("test when all positive2") {
    val region = new ArrayData(Array(1, 2, 3, 4))
    assert(region.largestContiguousSubArraySum() == 10)
    assert(region.largestSubArraySum() == 10)
  }

  test("test when positive and negative") {
    val region = new ArrayData(Array(2, -1, 2, 3, 4, -5))
    assert(region.largestContiguousSubArraySum() == 10)
    assert(region.largestSubArraySum() == 11)
  }

  test("test when all negative") {
    val region = new ArrayData(Array(-2, -1, -2, -3, -4, -5))
    assert(region.largestContiguousSubArraySum() == -1)
    assert(region.largestSubArraySum() == -1)
  }

  test("test for long list starting with negative") {
    val region = new ArrayData(Array(-3, 1, 2, 3, -1, 4, 5, -2, -1, 12, -3, -4, 5, -6, 1, 2, 8))
    assert(region.largestContiguousSubArraySum() == 26)
    assert(region.largestSubArraySum() == 43)
  }

  test("test for leading and trailing negatives") {
    val region = new ArrayData(Array(-3, -2, 1, 2, 3, -1, 4, 5, -2, -1, 12, -3, -4, 5, -6, 1, 2, 8, -3))
    assert(region.largestContiguousSubArraySum() == 26)
    assert(region.largestSubArraySum() == 43)
  }

  test("test for leading and trailing positives") {
    val region = new ArrayData(Array(3, 2, 1, 2, 3, -1, 4, 5, -2, -1, 12, -3, -4, 5, -6, 1, 2, 8, 3))
    assert(region.largestContiguousSubArraySum() == 34)
    assert(region.largestSubArraySum() == 51)
  }

  test("test for single negative") {
    val region = new ArrayData(Array(-3))
    assert(region.largestContiguousSubArraySum() == -3)
    assert(region.largestSubArraySum() == -3)
  }

  test("test for single posative") {
    val region = new ArrayData(Array(3))
    assert(region.largestContiguousSubArraySum() == 3)
    assert(region.largestSubArraySum() == 3)
  }

  test("test for triple out positive") {
    val region = new ArrayData(Array(3, -1, 2))
    assert(region.largestContiguousSubArraySum() == 4)
    assert(region.largestSubArraySum() == 5)
  }

  test("test for triple out positive big negative causes break") {
    val region = new ArrayData(Array(3, -10, 2))
    assert(region.largestContiguousSubArraySum() == 3)
    assert(region.largestSubArraySum() == 5)
  }

  test("test for triple out negative") {
    val region = new ArrayData(Array(-3, 1, -2))
    assert(region.largestContiguousSubArraySum() == 1)
    assert(region.largestSubArraySum() == 1)
  }

  test("test for small but long run in middle") {
    val region = new ArrayData(Array(7, 6, 4, 3, -1, -1, -2, -1, 3, 4, 5, 6, 7, -3, 1, -2))
    assert(region.largestContiguousSubArraySum() == 40)
    assert(region.largestSubArraySum() == 46)
  }

  test("test sequence with -100, 100") {
    val region = new ArrayData(Array(3, 5, 1, 10, -100, 100, 1, 1, -5, 5, 5, -2))
    assert(region.largestContiguousSubArraySum() == 107)
    assert(region.largestSubArraySum() == 131)
  }

  test("test sequence with cancelation pairs") {
    val region = new ArrayData(Array(
      7, -1, 6, 4, 3, -1, -1, -2, -1, 3, 5, 1, 10, -100, 100, 1, 1, -5, 1, 1, 4, 5, 6, 7, -3, 1, -2
    ))
    assert(region.largestContiguousSubArraySum() == 121)
    assert(region.largestSubArraySum() == 166)
  }
}