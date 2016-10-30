package hackarrank.castleonthegrid

import common.Location


/**
  * Maintains the current position on the immutable grid and calculates possible next moves.
  * Immutable
  */
class Board(val grid: Grid, val currentPosition: Location) {

  def isAtGoal = currentPosition == grid.goal

  /** This could be improved by looking to see if there is an X in the way. If so then min is 4
    * @return a lower bound on the number of steps that it will take to reach the goal */
  def estimatedStepsToGoal: Int = {
    if (currentPosition.row == grid.goal.row) {
      if (currentPosition.col == grid.goal.col) 0 else 1
    }
    else if (currentPosition.col == grid.goal.col) 1 else 2
  }

  /**
    * Consider all moves moving in all of the 4 directions until you hit the edge or an X.
    * @return a list of possible next moves from the current location
    */
  def getNeighborTransitions: List[Transition] = {
    var neighbors: List[Transition] = Nil

    var i: Int = currentPosition.row
    var j: Int = currentPosition.col

    while (i + 1 < grid.size && grid.isOpen(i + 1, j)) {   // march east
      neighbors :+= new Transition(new Location(i + 1, j))
      i += 1
    }

    i = currentPosition.row
    while (i > 0 && grid.isOpen(i - 1, j)) {             // march west
      neighbors :+= new Transition(new Location(i - 1, j))
      i -= 1
    }

    i = currentPosition.row
    while (j + 1 < grid.size && grid.isOpen(i, j + 1) ) { // march south
      neighbors :+= new Transition(new Location(i, j + 1))
      j += 1
    }

    j = currentPosition.col
    while (j > 0 && grid.isOpen(i, j - 1) ) {            // march north
      neighbors :+= new Transition(new Location(i, j - 1))
      j -= 1
    }

    println("from " + currentPosition + " nbrs = "+ neighbors.mkString(", "))
    neighbors
  }

  def applyTransition(trans: Transition): Board = {
    new Board(grid, trans.newPosition)
  }

  override def equals(other: Any): Boolean = {
    other match {
      case other: Board => currentPosition == other.currentPosition && grid == other.grid
      case _ => false
    }
  }

  override def hashCode: Int = {
    currentPosition.hashCode + grid.hashCode()
  }
}
