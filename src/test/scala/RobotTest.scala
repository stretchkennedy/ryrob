import org.specs2.runner._
import org.specs2.mutable._
import org.specs2.specification._

object RobotSpec extends Specification {
  "Robot" should {
    trait c1 extends Scope {
      val robot: Robot = Robot(new Point(10, 10))
    }
    "report correctly after being placed" in new c1 {
      robot
        .place(new Point(1, 2), Direction.NORTH)
        .report mustEqual "NORTH@1,2"
    }
    "report correctly after being placed and moved" in new c1 {
      robot
        .place(new Point(1, 2), Direction.NORTH)
        .move
        .report mustEqual "NORTH@1,1"
    }
    "report correctly after being placed and rotated" in new c1 {
      robot
        .place(new Point(1, 3), Direction.NORTH)
        .left
        .report mustEqual "WEST@1,3"
    }
    "report correctly after being placed, rotated, and moved" in new c1 {
      robot
        .place(new Point(1, 3), Direction.NORTH)
        .move
        .left
        .report mustEqual "WEST@1,2"
    }
    "report correctly after being placed twice" in new c1 {
      robot
        .place(new Point(1, 2), Direction.NORTH)
        .place(new Point(4, 5), Direction.SOUTH)
        .report mustEqual "SOUTH@4,5"
    }
    "report correctly after reaching zero" in new c1 {
      robot
        .place(new Point(0, 0), Direction.NORTH)
        .move
        .report mustEqual "NORTH@0,0"
    }
    "report correctly after coming to the bounds" in new c1 {
      robot
        .place(new Point(10, 10), Direction.SOUTH)
        .move
        .report mustEqual "SOUTH@10,10"
    }
    "report that it is invalid before being placed" in new c1 {
      robot
        .report mustEqual "INVALID ROBOT PLACEMENT"
    }
    "remain unchanged if it is moved to a location outside its bounds" in new c1 {
      robot
        .place(new Point(0, 0), Direction.NORTH)
        .place(new Point(11, 11), Direction.NORTH)
        .report mustEqual "NORTH@0,0"
    }
  }
}
