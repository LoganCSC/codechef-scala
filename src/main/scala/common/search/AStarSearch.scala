/** Copyright by Barry G. Becker, 2016. Licensed under MIT License: http://www.opensource.org/licenses/MIT  */
package common.search

import scala.collection.mutable

/**
  * Sequential search strategy that uses the A* search algorithm.
  * See http://en.wikipedia.org/wiki/A*_search_algorithm
  * S represents a state in the global search space.
  * T represents a transition from one state to the next.
  *
  * The performance of this search is very dependent on the design of the search space.
  * Here are some possible optimizations to consider when designing the SearchSpace and its components.
  * - The visited list may grow huge if the space is very large causing out of memory issues.
  * - Calculate distance metrics in the constructor (or using lazy initialization) of S. S and T should be immutable.
  * - Try to make the equals method in S as efficient as possible as it will be called a lot.
  * - When creating neighbors, use the fact that there is going to be an incremental change to the distance
  * and do not recompute it from scratch. Hint: use a private constructor, that takes the distance as a param.
  * - Sort the neighbors so that the most promising is delivered first.
  *
  * @author Barry Becker
  */
class AStarSearch[S, T] protected() extends ISearcher[S, T] {

  protected var searchSpace: SearchSpace[S, T] = _

  /** States that have been visited, but they may be replaced if we can reach them by a better path */
  private[search] var visited: mutable.Map[S, Node[S, T]] = _

  /** Candidate nodes to search on the frontier. */
  protected var openQueue: UpdatablePriorityQueue[S, T] = _

  /** Provides the value for the lowest cost path from the start state to the specified goal state (g score) */
  protected var pathCost: mutable.Map[S, Integer] = _

  protected var solution: Option[Node[S, T]] = None

  /** number of steps that it took to find solution */
  protected var numTries: Long = 0L

  /** enables stopping the search via method call */
  private var stopped: Boolean = false

  /**
    * @param searchSpace the global search space containing initial and goal states.
    * @param queue       the specific updatable priority queue to use.
    */
  def this(searchSpace: SearchSpace[S, T], queue: UpdatablePriorityQueue[S, T]) {
    this()
    this.searchSpace = searchSpace
    visited = new mutable.HashMap[S, Node[S, T]]
    openQueue = queue
    pathCost = new mutable.HashMap[S, Integer]
  }

  /** @param searchSpace the global search space containing initial and goal states. */
  def this(searchSpace: SearchSpace[S, T]) {
    this(searchSpace, new HeapPriorityQueue[S, T])
  }

  /**
    * @return a sequence of transitions leading from the initial state to the goal state.
    */
  def solve: Option[Seq[T]] = {
    val startTime: Long = System.currentTimeMillis
    initialize()
    val solutionState: Option[Node[S, T]] = search
    val pathToSolution: Option[Seq[T]] = if (solutionState.isDefined) Some(solutionState.get.asTransitionList) else None
    val solution: Option[S] = if (solutionState.isDefined) Some(solutionState.get.getState) else None

    val elapsedTime: Long = System.currentTimeMillis - startTime
    searchSpace.finalRefresh(pathToSolution, solution, numTries, elapsedTime)
    pathToSolution
  }

  private[search] def initialize() {
    stopped = false
    val startingState: S = searchSpace.initialState
    val startNode: Node[S, T] = new Node[S, T](startingState, searchSpace.distanceFromGoal(startingState))
    openQueue.add(startNode)
    pathCost.put(startingState, 0)
  }

  /** @return the solution - null until it is found */
  def getSolution: Option[Seq[T]] = if (solution.isDefined) Some(solution.get.asTransitionList) else None

  /** Tell the search to stop */
  def stop() {
    stopped = true
  }

  /**
    * Best first search for a solution.
    *
    * @return the solution state node, if found, which has the path leading to a solution. Null if no solution.
    */
  protected def search: Option[Node[S, T]] = {
    while (continueSearching) {
      val currentNode: Option[Node[S, T]] = processNext(openQueue.pop)
      if (currentNode.isDefined) return currentNode
    }
    None // failure
  }

  private[search] def continueSearching: Boolean = nodesAvailable && !stopped

  /** process the next node on the priority queue */
  private[search] def processNext(currentNode: Node[S, T]): Option[Node[S, T]] = {
    val currentState: S = currentNode.getState
    searchSpace.refresh(currentState, numTries)
    if (searchSpace.isGoal(currentState)) {
      // the extra check for a better path is needed when running concurrently
      if (solution.isEmpty || currentNode.getPathCost < solution.get.getPathCost)
        solution = Some(currentNode)
      return Some(currentNode) // success
    }
    visited.put(currentState, currentNode)
    val transitions: Seq[T] = searchSpace.legalTransitions(currentState)
    assert(transitions != null, "Could not find any transitions from " + currentState)
    import scala.collection.JavaConversions._
    for (transition <- transitions) {
      val nbr: S = searchSpace.transition(currentState, transition)
      if (!visited.containsKey(nbr)) {
        val estPathCost: Int = pathCost(currentState) + searchSpace.getCost(transition)
        if (!pathCost.containsKey(nbr) || estPathCost < pathCost(nbr)) {
          val estFutureCost: Int = estPathCost + searchSpace.distanceFromGoal(nbr)
          val child: Node[S, T] = new Node[S, T](nbr, Some(transition), Some(currentNode), estPathCost, estFutureCost)
          pathCost.put(nbr, estPathCost)
          openQueue.addOrUpdate(child)
          numTries += 1
        }
      }
    }
    None
  }

  protected def nodesAvailable: Boolean = !openQueue.isEmpty
}




