import Direction._

trait Robot {
  def place(p: Point, dir: Direction): Robot
  def move: Robot
  def left: Robot
  def right: Robot
  def clean: Robot
  def hasWon: Boolean
  def report: String
}

object Robot {

  def apply(table: Table): Robot = {
    new RobotInvalid(table)
  }

  private abstract class AbstractRobot(table: Table)
      extends Robot {
    override def place(p: Point, dir: Direction) =
      table.isAccessible(p) match {
        case true  => new RobotImpl(p, dir, table)
        case false => this
      }
    
    override def hasWon = !table.hasDirt
  }

  private class RobotImpl(point: Point, direction: Direction, table: Table)
      extends AbstractRobot(table) {

    override def move = {
      val newPoint = point.move(direction)
      table.isAccessible(newPoint) match {
        case true  => new RobotImpl(newPoint, direction, table)
        case false => this
      }
    }

    override def left =
      new RobotImpl(point, direction.left, table)

    override def right =
      new RobotImpl(point, direction.right, table)

    override def clean = {
      val cleanPoint = point.move(direction)
      new RobotImpl(point, direction, table.clean(cleanPoint))
    }

    override def report =
      point + ", " + direction
  }

  private class RobotInvalid(table: Table)
      extends AbstractRobot(table) {
    override def move = this
    override def left = this
    override def right = this
    override def clean = this
    override def report = "INVALID ROBOT PLACEMENT"
  }
}
