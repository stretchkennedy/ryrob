import Direction._

class Point(val x: Int, val y: Int) {
  def move(dir: Direction) = dir match {
    case NORTH => new Point(x, y - 1)
    case SOUTH => new Point(x, y + 1)
    case EAST  => new Point(x + 1, y)
    case WEST  => new Point(x - 1, y)
  }
  
  def inBounds(b: Point): Boolean = 
    this.x < b.x && 
    this.y < b.y &&
    this.x >= 0 &&
    this.y >= 0
}
