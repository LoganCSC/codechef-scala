package codequest2018.p18dominatingdisney


class DisneyPathFinder(val weights: Seq[Int]) {

  case class Path(from: String, to: String, num: Int) {
    def weight = weights(num)
  }

  val destinations = Set("pirates", "splash", "dwarfs", "space")
  val graph = Map(
    "start" -> Seq(Path("start", "pirates", 0), Path("start", "splash", 2),
               Path("start", "dwarfs", 4), Path("start", "space", 6)),
    "pirates" -> Seq(Path("pirates", "splash", 1), Path("pirates", "start", 0)),
    "splash" -> Seq(Path("splash", "pirates", 1), Path("splash", "start", 2), Path("splash", "dwarfs", 3)),
    "dwarfs" -> Seq(Path("dwarfs", "splash", 3), Path("dwarfs", "start", 4), Path("dwarfs", "space", 5)),
    "space" -> Seq(Path("space", "dwarfs", 5), Path("space", "start", 6))
  )

  // Terminate the search of the path len exceeds the sum of all path weights
  private val maxPathLen = weights.sum


  def findShortestPath(): String = {
    var shortestDistance: Int = Int.MaxValue
    var shortestPath: Seq[Path] = Seq()

    // never visit a destination more than twice since that would indicate a cycle.
    val visitCount: Map[String, Int] = Map()
    val allPaths = findAllPaths(Seq(), visitCount)

    for (path <- allPaths) {
      val dist = path.map(_.weight).sum
      if (dist < shortestDistance || dist == shortestDistance && path.head.weight < shortestPath.head.weight) {
        shortestDistance = dist
        shortestPath = path
      }
    }
    shortestPath.map(_.num + 1).mkString(" ")
  }

  private def findAllPaths(pathPrefix: Seq[Path], visitCount: Map[String, Int]): Seq[Seq[Path]] = {

    val currentPath = pathPrefix.lastOption
    val to = if (currentPath.isDefined) currentPath.get.to else "start"

    if (visitCount.keys.size == 4) {
      println("Visited everything! returning:  " + (pathPrefix :+ currentPath.get))
      Seq(pathPrefix)
    }
    else {
      var pathSeqs: Seq[Seq[Path]] = Seq()

      val current = if (currentPath.isDefined) currentPath.get.to else "start"
      val childPaths = graph(current)
      //println("childPaths = " + childPaths.mkString(", "))
      //println("pathPrefix = " + pathPrefix.mkString(", "))
      //println("current = " + currentPath)
      //println("unvisited = " + unvisited.mkString(", "))

      for (childPath <- childPaths.sortBy(_.weight)) {
        val newPathPrefix = pathPrefix :+ childPath
        val next = childPath.to
        val newVisitCount =
          if (next != "start") {
            if (visitCount.contains(next))
              visitCount + (next -> (visitCount(next) + 1))
            else visitCount + (next -> 1)
          } else visitCount
        val visitedThrice = next != "start" && visitCount.contains(next) && visitCount(next) > 2
        if (newPathPrefix.map(_.weight).sum <= maxPathLen && !visitedThrice)
          pathSeqs ++= findAllPaths(newPathPrefix, newVisitCount)
      }
      pathSeqs
    }
  }
}