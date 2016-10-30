package common.geometry

/**
  * Represents a location location of something in integer coordinates.
  * Immutable. Use MutableIntLocation if you really need to modify it (rare).
  *
  * @author Barry Becker
  */
class IntLocation(val row: Int, val col: Int) extends Location {

  def this(loc: Location) {
    this(loc.getRow, loc.getCol)
  }

  def getRow: Int = row
  def getCol: Int = col
  def getX: Int = col
  def getY: Int = row

  def copy: Location = new IntLocation(row, col)

  def incrementOnCopy(rowChange: Int, colChange: Int): Location = new IntLocation(row + rowChange, col + colChange)
}

