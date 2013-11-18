import Direction._

trait Robot {
  def place(p: Point, dir: Direction): Robot
  def move: Robot
  def left: Robot
  def right: Robot
  def report: String
}

object Robot {

  def apply(board: Board): Robot = {
    new RobotInvalid(board)
  }

  private class RobotImpl(point: Point, direction: Direction, board: Board)
      extends Robot {
    override def place(p: Point, dir: Direction): Robot =
      board.isAccessible(p) match {
        case true  => new RobotImpl(p, dir, board)
        case false => this
      }

    override def move: Robot = {
      val newPoint = point.move(direction)
      board.isAccessible(newPoint) match {
        case true  => new RobotImpl(newPoint, direction, board)
        case false => this
      }
    }

    override def left: Robot =
      new RobotImpl(point, direction.left, board)

    override def right: Robot =
      new RobotImpl(point, direction.right, board)

    override def report: String = {
      List("[", point.x, ",", point.y, "], ", direction).mkString
    }
  }

  private class RobotInvalid(board: Board)
      extends Robot {
    override def place(p: Point, dir: Direction) = new RobotImpl(p, dir, board)
    override def move: Robot = this
    override def left: Robot = this
    override def right: Robot = this
    override def report: String = "INVALID ROBOT PLACEMENT"
  }
}
