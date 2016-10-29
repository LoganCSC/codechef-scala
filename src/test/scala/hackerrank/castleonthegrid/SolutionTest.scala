package hackerrank.castleonthegrid

import hackarrank.largestrectangle.LinearHeightField
import org.scalatest.FunSuite

/**
  * @author Barry Becker
  */
class SolutionTest extends FunSuite {

  test("test simple default case") {
    val region = new LinearHeightField(Array(1, 2, 3, 4, 5))
    assert(region.findMaxArea() == 9)
  }

}