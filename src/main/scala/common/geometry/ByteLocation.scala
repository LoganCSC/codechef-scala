package common.geometry

/**
  * Represents a location location of something in byte coordinates.
  * The range of bytes are only -127 to 127.
  *
  * Immutable. Use MutableIntLocation if you really need to modify it (rare).
  * @param row the row coordinate (0 - 255).
  * @param col the column coordinate (0 - 255).
  * @author Barry Becker
  */
@SerialVersionUID(1)
class ByteLocation(val row: Int, val col: Int) extends Location {
  assert(Math.abs(row) < 128 && Math.abs(col) < 128, "row=" + row + " or col=" + col + " was out of range.")

  protected val row_ = row.toByte
  protected val col_ = col.toByte

  def getRow: Int = row_
  def getCol: Int = col_
  def getX: Int = col_
  def getY: Int = row_

  def copy: Location = new ByteLocation(row_, col_)

  def incrementOnCopy(rowChange: Int, colChange: Int): Location =
    new ByteLocation(row_ + rowChange, col_ + colChange)
}

