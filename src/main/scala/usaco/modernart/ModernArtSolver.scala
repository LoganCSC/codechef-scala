package usaco.modernart

class ModernArtSolver(canvas: Array[Array[Int]]) {

  def solve(): Int =
    determinePossibleInitialColors(determineRects()).size

  /** In first pass determine rects for each color.
    * @return map from color to Rect
    */
  private def determineRects(): Map[Int, Rect] = {
    var colorToRect: Map[Int, Rect] = Map()
    val n = canvas.length

    for (y <- 0 until n)
      for (x <- 0 until n) {
        val c = canvas(y)(x)
        if (c != 0) {
          if (!colorToRect.contains(c))
            colorToRect += c -> Rect(x, y, x, y)
          else
            colorToRect += c -> colorToRect(c).extendBy(x, y)
        }
      }
    colorToRect
  }

  /** @return set of possible initial colors based on reducing the set of visible colors
    */
  private def determinePossibleInitialColors(colorToRect: Map[Int, Rect]): Set[Int] = {
    var visibleColors = colorToRect.keySet
    for ((color, rect) <- colorToRect)
      for (y <- rect.y1 to rect.y2)
        for (x <- rect.x1 to rect.x2) {
          val c = canvas(y)(x)
          if (color != c) visibleColors -= c
        }
    visibleColors
  }
}
