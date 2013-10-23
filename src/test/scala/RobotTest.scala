import org.specs2.runner._
import org.specs2.mutable._
import org.specs2.specification._

object RobotSpec extends Specification {
  "Robot" should {
    trait c1 extends Scope  {
      val robot = new Robot
    }
    "report correctly after being placed" in new c1 {
      robot.place(1, 2, Direction.North)
      robot.report() mustEqual "North@1,2"
    }
    "report correctly after being placed and moved" in new c1 {
      robot.place(1, 2, Direction.North)
      robot.move()
      robot.report() mustEqual "North@1,1"
    }
    "report correctly after being placed and rotated" in new c1 {
      robot.place(1, 3, Direction.North)
      robot.left()
      robot.report() mustEqual "West@1,3"
    }
    "report correctly after being placed, rotated, and moved" in new c1 {
      robot.place(1, 3, Direction.East)
      robot.move()
      robot.left()
      robot.report() mustEqual "North@2,3"
    }
    "report correctly after being placed twice" in new c1 {
      robot.place(1, 2, Direction.North)
      robot.place(4, 5, Direction.South)
      robot.report() mustEqual "South@4,5"
    }
  }
}
