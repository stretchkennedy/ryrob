import org.specs2.runner._
import org.specs2.mutable._
import org.specs2.specification._

class RobotCommandTest extends Specification {
  "RobotCommand" should {
    trait c1 extends Scope {
      val table = new Table(new Rectangle(0, 10, 0, 10))
      var robot = Robot(table)
    }
    "place a robot" in new c1 {
      robot = RobotCommand("place 1,2,north")(robot)
      robot.report mustEqual "[1,2], NORTH"
    }
    "move a robot" in new c1 {
      robot = RobotCommand("place 1, 2, north")(robot)
      robot = RobotCommand("move")(robot)
      robot.report mustEqual "[1,1], NORTH"
    }
    "rotate left" in new c1 {
      robot = RobotCommand("place 1, 2, north")(robot)
      robot = RobotCommand("left")(robot)
      robot.report mustEqual "[1,2], WEST"
    }
    "rotate right" in new c1 {
      robot = RobotCommand("place 1, 2, north")(robot)
      robot = RobotCommand("right")(robot)
      robot.report mustEqual "[1,2], EAST"
    }
    trait c2 extends Scope {
      val table = new Table(
        new Rectangle(0, 10, 0, 10),
        List(new Point(0, 0)))
      var robot = Robot(table)
    }
    "clean a spot" in new c2 {
      robot = RobotCommand("place 0, 1, north")(robot)
      robot = RobotCommand("clean")(robot)
      robot = RobotCommand("move")(robot)
      robot.report mustEqual "[0,0], NORTH"
    }
  }
}
