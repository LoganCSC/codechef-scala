package common.search.slidingpuzzle

import java.util.Arrays
import java.util.{Collections, Comparator}

import common.geometry.{ByteLocation, Location}

import scala.collection.mutable.ListBuffer

/**
  * Immutable.
  * For a long time I could not solve puzzles beyond 31. Eventually I got to about 44, but no higher.
  *
  * Here is a list of performance enhancements that I made and how important they are:
  * - calculated the changed the manhattan distance in the constructor, and cache it in a private property.
  * - use lazy calculation of the hamming distance. IOW, calculate the first time requested and cache it.
  * - made blocks use byte instead of int. short is probably best.
  * - use a 1D array instead of a 2D array for the blocks.
  * - Calculate the stringForm (from toString) in the constructor and cache it.
  * This should not be needed, and is bad because it adds memory. But I needed it in order to use the stringForm
  * as a hashcode for use in a HashMap (that also should not be needed, but I could not get reasonable times without)
  * - Use hamming distance as a sort of hashcode in the equals method to speed it up, since the normal way of computing
  * equals is slow. IOW first check the hamming value, if that does not match, it cannot be equal. If it does match
  * resort to slower comparison.
  * - When creating neighbors I use the fact that there is going to be an incremental change to the manhattan distance
  * and do not recompute it from scratch. Hint: use a private constructor, that takes the manhattan distance as a param.
  * - Sorted the neighbors so that the most promising is delivered first. Gave a modest performance boost because
  * fewer nodes were then added to the queue in the long run.
  * - Used System.arraycopy(src, 0, target, 0,length); to copy the internal blocks array.
  *
  * @author Barry Becker
  */
object Board {
  private def makeBlocks(src: Array[Array[Int]]): Array[Byte] = {
    val length: Int = src.length
    val target: Array[Byte] = new Array[Byte](length * length)
    for (i <- 0 to length) {
      for (j <- 0 to length) {
        target(i * length + j) = src(i)(j).toByte
      }
    }
    target
  }

  private def copyBlocks(src: Array[Byte]): Array[Byte] = {
    val length: Int = src.length
    val target: Array[Byte] = new Array[Byte](length)
    System.arraycopy(src, 0, target, 0, length)
    target
  }
}

class Board {
  private var blocks: Array[Byte] = _
  private var side: Byte = 0
  private var hamming1: Byte = 0
  var manhattan: Int = 0
  private var hashCode1: Int = 0

  def this(blocks: Array[Byte]) {
    this()
    this.blocks = blocks
    val size: Byte = blocks.length.toByte
    this.side = Math.sqrt(size).toByte
    this.hamming1 = -1
    this.manhattan = calculateManhattan
    this.hashCode1 = -1
  }

  /**
    * Construct a board from an N-by-N array of blocks
    * @param blocks 0 - N*N blocks. blocks[i][j] = block in row i, column j
    */
  def this(blocks: Array[Array[Int]]) {
    this(Board.makeBlocks(blocks))
  }

  /** use this version of the constructor if you already know the manhattan distance */
  def this(blocks: Array[Byte], side: Byte, manhattan: Int) {
    this()
    this.blocks = blocks
    this.side = side
    this.hamming1 = -1
    this.hashCode1 = -1
    this.manhattan = manhattan
  }

  /** @return board dimension N */
  def dimension: Int = side

  /** @return number of blocks out of place */
  def hamming: Int = {
    if (hamming1 < 0) hamming1 = calculateHamming
    hamming1
  }

  private def calculateHamming: Byte = {
    var expected: Byte = 0
    var hamCount: Byte = 0
    for (i <- 0 to side) {
      for (j <- 0 to side) {
            val value: Byte = blocks(i * side + j)
            expected = (expected + 1).toByte
            if (value != 0 && value != expected) hamCount = (hamCount + 1).toByte
      }
    }
    hamCount
  }

  /** @return sum of Manhattan distances between blocks and goal */
  //def manhattan: Int = manhattan

  private def calculateManhattan: Int = {
    var totalDistance: Int = 0
    for (i <- 0 to side) {
      for (j <- 0 to side) {
        val value: Int = blocks(i * side + j)
        if (value != 0) {
          val expCol: Int = (value - 1) % side
          val expRow: Int = (value - 1) / side
          val deltaRow: Int = Math.abs(expRow - i)
          val deltaCol: Int = Math.abs(expCol - j)
          totalDistance += deltaRow + deltaCol
        }
      }
    }
    totalDistance
  }

  override def equals(other: Any): Boolean = {
    other match {
      case other: Board => hamming == other.hamming && Arrays.equals(blocks, other.blocks)
      case _ => false
    }
  }

  override def hashCode: Int = {
    if (hashCode1 < 0) hashCode1 = Arrays.hashCode(blocks)
    hashCode1
  }

  /** @return true if this board the goal board */
  def isGoal: Boolean = hamming == 0

  /** @return a board that is obtained by exchanging two adjacent blocks in the same row */
  def twin: Board = {
    val newBlocks: Array[Byte] = Board.copyBlocks(this.blocks)
    if (newBlocks(0) != 0 && newBlocks(1) != 0) swap(0, 0, 0, 1, newBlocks)
    else swap(1, 0, 1, 1, newBlocks)
    new Board(newBlocks)
  }

  def getNeighborTransitions: List[Transition] = {
    var neighbors: List[Transition] = Nil
    val spacePos: Location = getSpacePosition
    val i: Int = spacePos.getRow
    val j: Int = spacePos.getCol
    if (i > 0) neighbors :+= new Transition(spacePos, new ByteLocation(i - 1, j))
    if (i < side - 1) neighbors :+= new Transition(spacePos, new ByteLocation(i + 1, j))
    if (j > 0) neighbors :+= new Transition(spacePos, new ByteLocation(i, j - 1))
    if (j < side - 1) neighbors :+= new Transition(spacePos, new ByteLocation(i, j + 1))
    neighbors
  }

  def applyTransition(trans: Transition): Board = {
    val space: Location = trans.getSpacePosition
    val tile: Location = trans.getTilePosition
    move(space.getRow, space.getCol, tile.getRow, tile.getCol)
  }

  /**
    * @return all neighboring boards. There are at most 4.
    */
  def neighbors: Iterable[Board] = {
    var neighbors: List[Board] = Nil
    val spacePos: Location = getSpacePosition
    val i: Int = spacePos.getRow
    val j: Int = spacePos.getCol
    if (i > 0) neighbors  :+= move(i, j, i - 1, j)
    if (i < side - 1) neighbors :+= move(i, j, i + 1, j)
    if (j > 0) neighbors :+= move(i, j, i, j - 1)
    if (j < side - 1) neighbors :+= move(i, j, i, j + 1)

    neighbors = neighbors.sortBy(_.manhattan)
    //Collections.sort(neighbors, new Comparator[Board]() {
    //  def compare(o1: Board, o2: Board): Int = o1.manhattan - o2.manhattan
    //})
    neighbors
  }

  private def move(oldSpaceRow: Int, oldSpaceCol: Int, newSpaceRow: Int, newSpaceCol: Int): Board = {
    val newBlocks: Array[Byte] = Board.copyBlocks(blocks)
    val movingVal: Int = blocks(newSpaceRow * side + newSpaceCol)
    val goalCol: Int = (movingVal - 1) % side
    val goalRow: Int = (movingVal - 1) / side
    val oldDist: Int = Math.abs(newSpaceRow - goalRow) + Math.abs(newSpaceCol - goalCol)
    val newDist: Int = Math.abs(oldSpaceRow - goalRow) + Math.abs(oldSpaceCol - goalCol)
    val distImprovement: Int = oldDist - newDist
    swap(oldSpaceRow, oldSpaceCol, newSpaceRow, newSpaceCol, newBlocks)
    new Board(newBlocks, side, manhattan - distImprovement)
  }

  /**
    * @return row column coordinates of the space position
    */
  private def getSpacePosition: Location = {
    var i: Byte = 0
    for (i <- 0 to side) {
      for (j <- 0 to side) {
        if (blocks(i * side + j) == 0) return new ByteLocation(i, j)
      }
    }
    throw new IllegalStateException("No space position!")
  }

  private def swap(row1: Int, col1: Int, row2: Int, col2: Int, b: Array[Byte]) {
    val p1: Byte = (row1 * side + col1).toByte
    val p2: Byte = (row2 * side + col2).toByte
    val temp: Byte = b(p1)
    b(p1) = b(p2)
    b(p2) = temp
  }

  /** @return string representation of this board  */
  override def toString: String = getStringForm

  private def getStringForm: String = {
    val s: StringBuilder = new StringBuilder
    s.append(side).append("\n")
    var i: Byte = 0
    for (i <- 0 to side) {
      for (j <- 0 to side) {
        val value = blocks(i * side + j)
        s"$value%2d"
        //s.append(String.format("%2d ", new Integer(blocks(i * side + j))))
      }
    }
    s.toString
  }
}