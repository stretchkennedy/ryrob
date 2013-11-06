import org.specs2.runner._
import org.specs2.mutable._
import org.specs2.specification._

object RobotSpec extends Specification {
  "Robot" should {
    trait c1 extends Scope  {
      val robot: Robot = Robot(0, 0, Direction.North).get
    }
    "report correctly after being placed" in new c1 {
      robot
      .place(1, 2, Direction.North)
      .report mustEqual "North@1,2"
    }
    "report correctly after being placed and moved" in new c1 {
      robot
      .place(1, 2, Direction.North)
      .move
      .report mustEqual "North@1,1"
    }
    "report correctly after being placed and rotated" in new c1 {
      robot
      .place(1, 3, Direction.North)
      .left
      .report mustEqual "West@1,3"
    }
    "report correctly after being placed, rotated, and moved" in new c1 {
      robot
      .place(1, 3, Direction.East)
      .move
      .left
      .report mustEqual "North@2,3"
    }
    "report correctly after being placed twice" in new c1 {
      robot
      .place(1, 2, Direction.North)
      .place(4, 5, Direction.South)
      .report mustEqual "South@4,5"
    }
  }
}
