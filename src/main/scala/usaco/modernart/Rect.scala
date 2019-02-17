package usaco.modernart

case class Rect(x1: Int, y1: Int, x2: Int, y2: Int) {
  def contains(x: Int, y: Int): Boolean = x >= x1 && y >= y1 && x <= x2 && y <= y2
  def extendBy(x: Int, y: Int): Rect = Rect(Math.min(x1, x), Math.min(y1, y), Math.max(x2, x), Math.max(y2, y))
}
