package common.geometry


/**
  * Represents a location location of something in byte coordinates.
  * The range of bytes are only -127 to 127.
  * Immutable. Use MutableIntLocation if you really need to modify it (rare).
  *
  * @author Barry Becker
  */
abstract class Location {
  def getRow: Int
  def getCol: Int
  def getX: Int
  def getY: Int
  def copy: Location

  /**
    * Checks to see if the given location has the same coordinates as this one.
    *
    * @param location The location whose coordinates are to be compared.
    * @return true  The location's coordinates exactly equal this location's.
    */
  override def equals(location: Any): Boolean = {
    location match {
      case loc: Location => (loc.getRow == getRow) && (loc.getCol == getCol)
      case _ => false
    }
  }

  /**
    * If override equals, should also override hashCode
    */
  override def hashCode: Int = 255 * getRow + getCol

  /**
    * @param loc another location to measure distance from.
    * @return the euclidean distance from this location to another.
    */
  def getDistanceFrom(loc: Location): Double = {
    val xDif: Float = Math.abs(getCol - loc.getCol)
    val yDif: Float = Math.abs(getRow - loc.getRow)
    Math.sqrt(xDif * xDif + yDif * yDif)
  }

  /**
    * @return an immutable copy of the original incremented by the amount specified.
    */
  def incrementOnCopy(rowChange: Int, colChange: Int): Location

  /**
    * @return an immutable copy of the original incremented by the amount specified.
    */
  def incrementOnCopy(loc: Location): Location = incrementOnCopy(loc.getRow, loc.getCol)

  /**
    * @return an immutable copy of the original incremented by the amount specified.
    */
  def decrementOnCopy(loc: Location): Location = incrementOnCopy(-loc.getRow, -loc.getCol)

  /**
    * @return the string form
    */
  override def toString: String = "(row=" + getRow + ", column=" + getCol + ")" //NON-NLS
}

