package hackerrank.maximumsubarray

import hackarrank.maximumsubarray.ArrayData
import org.scalatest.FunSuite

/**
  * @author Barry Becker
  */
class SolutionTest extends FunSuite {

  test("test when all positive1") {
    val region = new ArrayData(Array(1, 2, 3, 4, 5))
    assert(region.largestContiguousSubArray() == 15)
    assert(region.largestSubArray() == 15)
  }

  test("test when all positive2") {
    val region = new ArrayData(Array(1, 2, 3, 4))
    assert(region.largestContiguousSubArray() == 10)
    assert(region.largestSubArray() == 10)
  }

  test("test when positive and negative") {
    val region = new ArrayData(Array(2, -1, 2, 3, 4, -5))
    assert(region.largestContiguousSubArray() == 10)
    assert(region.largestSubArray() == 11)
  }

  test("test when all negative") {
    val region = new ArrayData(Array(-2, -1, -2, -3, -4, -5))
    assert(region.largestContiguousSubArray() == -1)
    assert(region.largestSubArray() == -1)
  }

  test("test for long list starting with negative") {
    val region = new ArrayData(Array(-3, 1, 2, 3, -1, 4, 5, -2, -1, 12, -3, -4, 5, -6, 1, 2, 8))
    assert(region.largestContiguousSubArray() == 26)
    assert(region.largestSubArray() == 43)
  }


}