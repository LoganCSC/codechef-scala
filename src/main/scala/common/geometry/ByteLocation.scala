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
class ByteLocation(val row: Int, val col: Int) extends Location {
  assert(Math.abs(row) < 128 && Math.abs(col) < 128, "row=" + row + " or col=" + col + " was out of range.")

  def getRow: Int = row
  def getCol: Int = col
  def getX: Int = col
  def getY: Int = row

  def copy: Location = new ByteLocation(row, col)

  def incrementOnCopy(rowChange: Int, colChange: Int): Location =
    new ByteLocation(row + rowChange, col + colChange)
}

