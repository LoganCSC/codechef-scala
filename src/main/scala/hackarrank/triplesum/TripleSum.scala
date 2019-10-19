package hackarrank.triplesum


case class TripleSum(array: Array[Int]) {

  private val a = array.sorted
  private val N: Int = array.length
  private val solution: Option[List[Int]] = findTripleSum(a)
  println("sol = " + solution)

  private def findTripleSum(a: Array[Int]): Option[List[Int]] = {
    val countMap = createCountMap(a)

    var lowIdx = 0
    var highIdx = a.length - 1
    var low = a(lowIdx)
    var high = a(highIdx)
    println(a.mkString(", "))

    while (lowIdx < highIdx) {

      val lookingFor = N - low - high
      if (countMap.contains(lookingFor) && verifiedFound(countMap(lookingFor), low, high)) {
        return Some(List(low, lookingFor, high))
      }
      var highChanged = false
      while (low + high >= N) {
        highIdx -= 1
        high = a(highIdx)
        highChanged = true
        lowIdx = 1
      }
      if (!highChanged) {
        lowIdx += 1
        low = a(lowIdx)
      }
    }
    None
  }

  private def verifiedFound(count: Int, low: Int, high: Int): Boolean = {
    val lookingFor = N - low - high
    if (low == high && low == lookingFor) count == 3
    else if (lookingFor == low || lookingFor == high) count == 2
    else true
  }

  private def createCountMap(a: Array[Int]): Map[Int, Int] = {
    var map: Map[Int, Int] = Map()
    a.foreach(i => {
      if (map.contains(i)) map += i -> (map(i) + 1)
      else map += i -> 1
    })
    map
  }

  def exists: Boolean = solution.isDefined
}
