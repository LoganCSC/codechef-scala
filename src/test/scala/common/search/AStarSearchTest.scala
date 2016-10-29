package common.search

import org.scalatest.{BeforeAndAfter, FunSuite}

/**
  * @author Barry Becker
  */
class AStarSearchTest extends FunSuite with BeforeAndAfter {

  private var space: StubSearchSpace = _

  before {
    space = new StubSearchSpace
  }

  /** test the protected search method to see if it finds the goal state */
  test("solve simple case") {
    val searcher: AStarSearch[StubState, StubTransition] = new AStarSearch[StubState, StubTransition](space)

    // a list of transitions to the goal state
    val path: Seq[StubTransition] = searcher.solve

    assertResult("[[id=D distanceFromGoal=5 cost=4], " + "[id=E distanceFromGoal=4 cost=6], " + "[id=goal distanceFromGoal=0 cost=4]]") {
      path.toString
    }
  }
}
