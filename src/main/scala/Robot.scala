import Direction._

class Robot {
  private var x: Int = _
  private var y: Int = _
  private var dir: Direction = _

  def place(x: Int, y: Int, dir: Direction) {
    this.x = x
    this.y = y
    this.dir = dir
  }
  
  def move() {
    dir match {
      case North => y -= 1
      case South => y += 1
      case East => x += 1
      case West => x -= 1
    }
  }
  
  def left() {
    dir = dir.left
  }
  
  def right() {
    dir = dir.right
  }
  
  def report(): String = {
    List(dir, "@", x, ",", y).mkString
  }
}
