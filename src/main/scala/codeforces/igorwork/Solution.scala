import scala.io.StdIn
import scala.collection.mutable


object Solution extends App {

  type Location = Tuple2[Int, Int]

  val ROAD_WORK = '*'
  val HOME = 'S'
  val OFFICE = 'T'

  val UP = "up"
  val DOWN = "down"
  val LEFT = "left"
  val RIGHT = "right"
  val DIRECTIONS = Array(UP, DOWN, LEFT, RIGHT)


  case class Position(location: Location, direction: String, numTurns: Int)

  case class CityMap(lines: Seq[String]) {

    val home: Location = findLocation(HOME)
    val office: Location = findLocation(OFFICE)

    def isOffice(loc: Location): Boolean = loc == office

    /** @return the (up to) 4 neighbors from the specified position */
    def neighborsOf(pos: Position): Seq[Position] = {
      var nbrs =  List[Position]()
      val loc = pos.location
      val turns = pos.numTurns
      // avoid going back to where we just came from
      for (dir <- DIRECTIONS) {
        dir match {
          case UP =>
            if (isOpen(loc, -1, 0) && pos.direction != DOWN)
              nbrs +:= Position((loc._1 - 1, loc._2), UP, if (pos.direction == UP) turns else turns + 1)
          case DOWN =>
            if (isOpen(loc, 1, 0) && pos.direction != UP)
              nbrs +:= Position((loc._1 + 1, loc._2), DOWN, if (pos.direction == DOWN) turns else turns + 1)
          case LEFT =>
            if (isOpen(loc, 0, -1) && pos.direction != RIGHT)
              nbrs +:= Position((loc._1, loc._2 - 1), LEFT, if (pos.direction == LEFT) turns else turns + 1)
          case RIGHT =>
            if (isOpen(loc, 0, 1) && pos.direction != LEFT)
              nbrs +:= Position((loc._1, loc._2 + 1), RIGHT, if (pos.direction == RIGHT) turns else turns + 1)
        }
      }
      nbrs
    }

    def startingPositions(): Seq[Position] = neighborsOf(Position(home, "no direction", -1))

    private def isOpen(loc: Location, rowOffset: Int, columnOffset: Int): Boolean = {
      val newLoc: Location = (loc._1 + rowOffset, loc._2 + columnOffset)
      isInGrid(newLoc) && lines(newLoc._1)(newLoc._2) != ROAD_WORK
    }

    private def isInGrid(loc: Location): Boolean =
      loc._1 >= 0 && loc._1 < lines.length && loc._2 >= 0 && loc._2 < lines.head.length

    private def findLocation(c: Char): Location = {
      for (row <- lines.indices) {
        val col = lines(row).indexOf(c)
        if (col >= 0) return (row, col)
      }
      throw new IllegalArgumentException(s"Could not find $c in ${lines.mkString("\n")}")
    }
  }

  trait Solver {
    def isSolvable(map: CityMap): Boolean
  }

  object BfsSolver extends Solver {

    def isSolvable(map: CityMap): Boolean = {

      def positionOrder(p: Position) = -p.numTurns
      val queue = mutable.PriorityQueue[Position]()(Ordering.by(positionOrder))
      queue.enqueue(map.startingPositions(): _*)

      while (queue.nonEmpty) {
        val currentPos = queue.dequeue()
        if (map.isOffice(currentPos.location)) return true
        val nbrs = map.neighborsOf(currentPos).filter(p => p.numTurns <= 2)
        queue.enqueue(nbrs :_*)
      }
      false
    }
  }

  object DfsSolver extends Solver {

    def isSolvable(map: CityMap): Boolean = {

      var stack = List[Position]()
      map.startingPositions().foreach(p => stack = p :: stack)

      while (stack.nonEmpty) {
        val currentPos = stack.head
        stack = stack.tail
        if (map.isOffice(currentPos.location)) return true
        val nbrs = map.neighborsOf(currentPos).filter(p => p.numTurns <= 2)
        nbrs.foreach(p => stack = p :: stack)
      }
      false
    }
  }

  def readMap(): CityMap = {
    val rows = StdIn.readLine.split(" ").head.toInt
    val lines: Seq[String] = for (i <- 0 until rows) yield StdIn.readLine()
    CityMap(lines)
  }

  val map = readMap()
  println(if (DfsSolver.isSolvable(map)) "YES" else "NO")
}