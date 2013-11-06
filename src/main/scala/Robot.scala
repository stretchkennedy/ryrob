import Direction._

trait Robot {
  def place(x: Int, y: Int, dir: Direction): Robot
  def move: Robot
  def left: Robot
  def right: Robot
  def report: String
}

object Robot {

  def apply(x: Int, y: Int, dir: Direction): Option[Robot] = {
    return Some(new RobotImpl(x, y, dir))
  }

  private class RobotImpl(x: Int, y: Int, dir: Direction)
      extends Robot {
    override def place(x: Int, y: Int, dir: Direction): Robot = {
      Robot(x, y, dir).getOrElse(this)
    }

    override def move: Robot = {
      val ret = dir match {
        case North => Robot(x, y - 1, dir)
        case South => Robot(x, y + 1, dir)
        case East  => Robot(x + 1, y, dir)
        case West  => Robot(x - 1, y, dir)
      }
      
      ret.getOrElse(this)
    }

    override def left: Robot = {
      Robot(x, y, dir.left).getOrElse(this)
    }

    override def right: Robot = {
      Robot(x, y, dir.right).getOrElse(this)
    }

    override def report: String = {
      List(dir, "@", x, ",", y).mkString
    }
  }
}
