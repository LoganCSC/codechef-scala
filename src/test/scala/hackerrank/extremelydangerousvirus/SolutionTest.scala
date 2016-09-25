package hackerrank.extremelydangerousvirus

import hackarrank.extremelydangerousvirus.Solution
import org.scalatest.FunSuite

/**
  * @author Barry Becker
  */
class SolutionTest extends FunSuite {
  test("test find exp num cells - small") {
    assert(Solution.expNumCells(2, 4, 1) == 3)
    assert(Solution.expNumCells(2, 4, 2) == 9)
    assert(Solution.expNumCells(4, 9, 6) == 75418)
  }

  test("mypow") {
    assert(Solution.myPow(10, 2) == 100)
    assert(Solution.myPow(10, 4) == 10000)
    assert(Solution.myPow(100, 8) == 930000007)
    assert(Solution.myPow(2, 2000) == 749218516)
    assert(Solution.myPow(3, 2000000) == 961835147)
    assert(Solution.myPow(2, 200000000) == 860291332L)
  }


  test("test find exp num cells - large") {
    assert(Solution.expNumCells(5, 9, 6000000000000000L) == 996028980L)
  }


  test("test find exp num cells - contest input") {
    assert(Solution.expNumCells(100, 100, 1000000000000000000L) == 5764801L)
    assert(Solution.expNumCells(75, 17, 711968069842552322L) == 412438533L) //291172003L)    //412438533
  }
}