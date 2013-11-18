import org.specs2.runner._
import org.specs2.mutable._
import org.specs2.specification._

class RobotCommandTest extends Specification {
  "RobotCommand" should {
    trait c1 extends Scope {
      val board = new Table(new Rectangle(0, 10, 0, 10))
      var robot = Robot(board)
    }
    "correctly place a robot" in new c1 {
      robot = RobotCommand("place 1,2,north")(robot)
      robot.report mustEqual "[1,2], NORTH"
    }
    "correctly move a robot" in new c1 {
      robot = RobotCommand("place 1, 2, north")(robot)
      robot = RobotCommand("move")(robot)
      robot.report mustEqual "[1,1], NORTH"
    }
    "correctly rotate left" in new c1 {
      robot = RobotCommand("place 1, 2, north")(robot)
      robot = RobotCommand("left")(robot)
      robot.report mustEqual "[1,2], WEST"
    }
    "correctly rotate right" in new c1 {
      robot = RobotCommand("place 1, 2, north")(robot)
      robot = RobotCommand("right")(robot)
      robot.report mustEqual "[1,2], EAST"
    }
  }
}
