package hackerrank.equalstacks

import hackarrank.equalstacks.CylinderStackList
import org.scalatest.FunSuite

/**
  * @author Barry Becker
  */
class SolutionTest extends FunSuite {

  test("test equal stacks when 2 stacks") {

    val stacks = new CylinderStackList(Seq(Array(3, 2), Array(5, 2)))

    assert(!stacks.allHeightsEqual())
    assert(stacks.getMaxHeight == 7)

    stacks.removeBlocksFromTallestStacks()

    assert(!stacks.allHeightsEqual())
    assert(stacks.getMaxHeight == 5)

    stacks.removeBlocksFromTallestStacks()

    assert(stacks.allHeightsEqual())
    assert(stacks.getMaxHeight == 2)
  }

  test("test equal stacks when 3 stacks (non 0 result)") {

    val stacks = new CylinderStackList(Seq(Array(3, 2, 1, 1, 2, 1), Array(5, 1, 2, 1, 1, 2), Array(1, 2, 3, 5, 2)))

    assert(!stacks.allHeightsEqual())
    assert(stacks.getMaxHeight == 13)

    stacks.removeBlocksUntilEqual()

    assert(stacks.allHeightsEqual())
    assert(stacks.getMaxHeight == 7)
  }

  test("test equal stacks when 3 stacks (0 height result)") {

    val stacks = new CylinderStackList(Seq(Array(3, 2, 14, 1, 2, 7, 1), Array(5, 1, 3), Array(1, 2, 3, 5, 32)))

    assert(!stacks.allHeightsEqual())
    assert(stacks.getMaxHeight == 43)

    stacks.removeBlocksUntilEqual()

    assert(stacks.allHeightsEqual())
    assert(stacks.getMaxHeight == 0)
  }
}