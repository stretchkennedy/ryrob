import Direction._

trait Robot {
  def place(p: Point, dir: Direction): Robot
  def move: Robot
  def left: Robot
  def right: Robot
  def report: String
}

object Robot {

  def apply(p: Point, dir: Direction): Option[Robot] = {
    Some(new RobotImpl(p, dir))
  }

  private class RobotImpl(point: Point, direction: Direction)
      extends Robot {
    override def place(p: Point, dir: Direction): Robot =
      Robot(p, dir).getOrElse(this)

    override def move: Robot = 
      Robot(point.move(direction), direction).getOrElse(this)

    override def left: Robot =
      Robot(point, direction.left).getOrElse(this)

    override def right: Robot =
      Robot(point, direction.right).getOrElse(this)

    override def report: String = {
      List(direction, "@", point.x, ",", point.y).mkString
    }
  }
}
