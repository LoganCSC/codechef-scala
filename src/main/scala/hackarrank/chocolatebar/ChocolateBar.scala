package hackarrank.chocolatebar

import scala.collection.Map

case class ChocolateBar(cells: Array[Array[Byte]]) {

  /** coordinates (Int, Int) are row, column, not x, y */
  private case class Grid(upperLeft: (Int, Int), lowerRight: (Int, Int))

  private var cache: Map[Grid, Int] = Map()

  /** @return minimum breaks needed to isolate raisin cells */
  def findMinBreaksToIsolateRaisins(): Int = minBreaks(Grid((0, 0), (cells.length, cells(0).length)))

  /** @param grid a rectangular sub-region of the chocolate bar
    * @return minimum breaks needed to isolate raisin cells
    */
  private def minBreaks(grid: Grid): Int = {
    if (cache.contains(grid)) cache(grid)
    else {
      val result = if (isPure(grid)) 0
        else {
          var minSplits = Integer.MAX_VALUE
          for (split <- possibleSplits(grid)) {
            val numSplits = minBreaks(split._1) + minBreaks(split._2) + 1
            if (numSplits < minSplits)
              minSplits = numSplits
            minSplits = Math.min(numSplits, minSplits)
          }
          minSplits
        }
      cache += grid -> result
      result
    }
  }

  /** @return true if all cells are homogeneous - i.e. all raisin or all pure chocolate */
  private def isPure(grid: Grid): Boolean = {
    val startRow = grid.upperLeft._1
    val startCol = grid.upperLeft._2
    val firstCell = cells(startRow)(startCol)
    for (row <- startRow until grid.lowerRight._1)
      for (col <- startCol until grid.lowerRight._2)
        if (cells(row)(col) != firstCell) return false
    true
  }

  /** @return all valid splits.
    *   A split is valid if there is at least one pair of adjacent cells across the
    *   split where one has raisins and the other does not.
    */
  private def possibleSplits(grid: Grid): List[(Grid, Grid)] = {
    var splits = List[(Grid, Grid)]()

    for (colCandidate <- grid.upperLeft._2 + 1 until grid.lowerRight._2) {   // vertical splits
      var row = grid.upperLeft._1
      while (row < grid.lowerRight._1 && cells(row)(colCandidate - 1) == cells(row)(colCandidate))
        row += 1
      if (row < grid.lowerRight._1)
        splits :+= (Grid(grid.upperLeft, (grid.lowerRight._1, colCandidate)),
                    Grid((grid.upperLeft._1, colCandidate), grid.lowerRight))
    }

    for (rowCandidate <- grid.upperLeft._1 + 1 until grid.lowerRight._1) {  // horizontal splits
      var col = grid.upperLeft._2
      while (col < grid.lowerRight._2 && cells(rowCandidate - 1)(col) == cells(rowCandidate)(col))
        col += 1
      if (col < grid.lowerRight._2)
        splits :+= (Grid(grid.upperLeft, (rowCandidate, grid.lowerRight._2)),
                    Grid((rowCandidate, grid.upperLeft._2), grid.lowerRight))
    }
    splits
  }
}
