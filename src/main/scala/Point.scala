import Direction._

class Point(val x: Int, val y: Int) {
  def move(dir: Direction) = dir match {
    case NORTH => new Point(x, y - 1)
    case SOUTH => new Point(x, y + 1)
    case EAST  => new Point(x + 1, y)
    case WEST  => new Point(x - 1, y)
  }
  
  def isInside(r: Rectangle): Boolean = 
    r.doesContain(this)
}
