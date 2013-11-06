import Direction._

trait Robot {
  def place(p: Point, dir: Direction): Robot
  def move: Robot
  def left: Robot
  def right: Robot
  def report: String
}

object Robot {

  def apply(bounds: Point): Robot = {
    new RobotInvalid(bounds)
  }

  private class RobotImpl(point: Point, direction: Direction, bounds: Point)
      extends Robot {
    override def place(p: Point, dir: Direction): Robot =
      p.inBounds(bounds) match {
        case true  => new RobotImpl(p, dir, bounds)
        case false => this
      }

    override def move: Robot = {
      val newPoint = point.move(direction)
      newPoint.inBounds(bounds) match {
        case true  => new RobotImpl(newPoint, direction, bounds)
        case false => this
      }
    }

    override def left: Robot =
      new RobotImpl(point, direction.left, bounds)

    override def right: Robot =
      new RobotImpl(point, direction.right, bounds)

    override def report: String = {
      List(direction, "@", point.x, ",", point.y).mkString
    }
  }

  private class RobotInvalid(bounds: Point)
      extends Robot {
    override def place(p: Point, dir: Direction) = new RobotImpl(p, dir, bounds)
    override def move: Robot = this
    def left: Robot = this
    def right: Robot = this
    def report: String = "INVALID ROBOT PLACEMENT"
  }
}
