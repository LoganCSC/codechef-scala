package hackarrank.castleonthegrid

import common.geometry.{IntLocation, Location}

/**
  * Maintains the current position on the grid
  */
class Board(grid: Grid, val currentPosition: Location) {

  def isAtGoal = currentPosition == grid.goal

  /** This could be improved by looking to see if there is an X in the way. If so then min is 4
    * @return a lower bound on the number of steps that it will take to reach the goal */
  def estimatedStepsToGoal: Int = {
    if (currentPosition.getRow == grid.goal.row) {
      if (currentPosition.getCol == grid.goal.col) 0 else 1
    }
    else if (currentPosition.getCol == grid.goal.col) 1 else 2
  }

  /**
    * Consider all moves moving in all of the 4 directions until you hit the edge or an X.
    * @return a list of possible next moves from the current location
    */
  def getNeighborTransitions: List[Transition] = {
    var neighbors: List[Transition] = Nil

    val i: Int = currentPosition.getRow
    val j: Int = currentPosition.getCol
    // march east
    while (i + 1 < grid.size && grid.isOpen(i + 1, j) )
      neighbors :+= new Transition(currentPosition, new IntLocation(i + 1, j))

    // march west
    while (i > 0 && grid.isOpen(i - 1, j) )
      neighbors :+= new Transition(currentPosition, new IntLocation(i - 1, j))

    // march north
    while (j + 1 < grid.size && grid.isOpen(i, j + 1) )
      neighbors :+= new Transition(currentPosition, new IntLocation(i, j + 1))

    // march south
    while (j > 0 && grid.isOpen(i, j - 1) )
      neighbors :+= new Transition(currentPosition, new IntLocation(i, j - 1))

    neighbors
  }

  def applyTransition(trans: Transition): Board = {
    new Board(grid, trans.newPosition)
  }

}
