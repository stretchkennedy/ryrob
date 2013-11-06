import Direction._

class Point(val x: Int, val y: Int) {
  def move(dir: Direction) = dir match {
    case North => new Point(x, y - 1)
    case South => new Point(x, y + 1)
    case East  => new Point(x + 1, y)
    case West  => new Point(x - 1, y)
  }
  
  def inBounds(b: Point): Boolean = 
    this.x < b.x && 
    this.y < b.y &&
    this.x > 0 &&
    this.y > 0
}
