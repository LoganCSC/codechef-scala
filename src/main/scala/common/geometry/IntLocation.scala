package common.geometry

/**
  * Represents a location location of something in integer coordinates.
  * Immutable. Use MutableIntLocation if you really need to modify it (rare).
  *
  * @author Barry Becker
  */
class IntLocation extends Location {
  private var row_ = 0
  private var col_ = 0

  def this(loc: Location) {
    this()
    row_ = loc.getRow
    col_ = loc.getCol
  }

  /**
    * Constructs a new Location at the given coordinates.
    *
    * @param row the row coordinate.
    * @param col the column coordinate.
    */
  def this(row: Int, col: Int) {
    this()
    row_ = row
    col_ = col
  }

  override def getRow: Int = row_

  def getCol: Int = col_

  def getX: Int = col_

  def getY: Int = row_

  def copy: Location = new IntLocation(row_, col_)

  def incrementOnCopy(rowChange: Int, colChange: Int): Location = new IntLocation(row_ + rowChange, col_ + colChange)
}

