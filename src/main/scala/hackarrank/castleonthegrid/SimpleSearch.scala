package hackarrank.castleonthegrid

import common.geometry.Location

import scala.collection.mutable

/**
  * @author Barry Becker
  */
class SimpleSearch(val startingState: Board) {

  /** States that have been visited, but they may be replaced if we can reach them by a better path */
  private val bestCost = new mutable.HashMap[Location, Int]

  /** estCost is the cost to get here plus estimated future cost */
  private class Node(val b: Board, val costToHere: Int, val estCost: Int, val prev: Node = null) {
    override def toString: String = {
        "costToHere=" + costToHere +" estCost=" + estCost
    }
  }

  private val openQueue = new mutable.PriorityQueue[Node]()(Ordering.by(-_.estCost))


  /** @return a sequence of transitions leading from the initial state to the goal state.*/
  def solve: Option[Seq[Location]] = {
    initialize()
    asTransitionList(search)
  }

  private def asTransitionList(node: Option[Node]): Option[Seq[Location]] = {

    if (node.isDefined) {
      var currentNode = node.get
      var solution: Seq[Location] = List()
      while (currentNode.prev != null) {
        solution = currentNode.b.currentPosition +: solution
        currentNode = currentNode.prev
      }
      Some(solution)
    } else None
  }

  private def initialize() {
    val startNode = new Node(startingState, 0, startingState.estimatedStepsToGoal)
    openQueue.enqueue(startNode)
    bestCost.put(startingState.currentPosition, 0)
  }

  /**
    * Best first search for a solution.
    * @return the solution state node, if found, which has the path leading to a solution. Null if no solution.
    */
  private def search: Option[Node] = {
    while (openQueue.nonEmpty) {
      println("q = " + openQueue.mkString(", "))
      val currentNode: Option[Node] = processNext(openQueue.dequeue())
      if (currentNode.isDefined)
        return currentNode
    }
    None // failure
  }


  /** process the next node on the priority queue */
  private def processNext(currentNode: Node): Option[Node] = {
    val currentState: Board = currentNode.b
    if (currentState.isAtGoal) {
      Some(currentNode) // success
    }
    else {
      val transitions = currentState.getNeighborTransitions
      assert(transitions != null, "Could not find any transitions from " + currentState)

      for (transition <- transitions) {
        val nbr = currentState.applyTransition(transition)
        val pathCost: Int = currentNode.costToHere + 1
        if (!bestCost.contains(nbr.currentPosition) || pathCost < bestCost(nbr.currentPosition)) {
          bestCost.put(nbr.currentPosition, pathCost)
          val estFutureCost: Int = pathCost + nbr.estimatedStepsToGoal
          val child: Node = new Node(nbr, pathCost, estFutureCost, currentNode)
          openQueue.enqueue(child)
        }
      }
      None
    }
  }

}




