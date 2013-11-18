import org.specs2.runner._
import org.specs2.mutable._
import org.specs2.specification._

object RobotSpec extends Specification {
  "Robot" should {
    trait c1 extends Scope {
      val board = new Table(new Rectangle(0, 10, 0, 10))
      val robot = Robot(board)
    }
    "report correctly after being placed" in new c1 {
      robot
        .place(new Point(1, 2), Direction.NORTH)
        .report mustEqual "[1,2], NORTH"
    }
    "report correctly after being placed and moved" in new c1 {
      robot
        .place(new Point(1, 2), Direction.NORTH)
        .move
        .report mustEqual "[1,1], NORTH"
    }
    "report correctly after being placed and rotated" in new c1 {
      robot
        .place(new Point(1, 3), Direction.NORTH)
        .left
        .report mustEqual "[1,3], WEST"
    }
    "report correctly after being placed, rotated, and moved" in new c1 {
      robot
        .place(new Point(1, 3), Direction.NORTH)
        .move
        .left
        .report mustEqual "[1,2], WEST"
    }
    "report correctly after being placed twice" in new c1 {
      robot
        .place(new Point(1, 2), Direction.NORTH)
        .place(new Point(4, 5), Direction.SOUTH)
        .report mustEqual "[4,5], SOUTH"
    }
    "report correctly after reaching zero" in new c1 {
      robot
        .place(new Point(0, 0), Direction.NORTH)
        .move
        .report mustEqual "[0,0], NORTH"
    }
    "report correctly after coming to the bounds" in new c1 {
      robot
        .place(new Point(10, 10), Direction.SOUTH)
        .move
        .report mustEqual "[10,10], SOUTH"
    }
    "report that it is invalid before being placed" in new c1 {
      robot
        .report mustEqual "INVALID ROBOT PLACEMENT"
    }
    "remain unchanged if it is moved to a location outside its bounds" in new c1 {
      robot
        .place(new Point(0, 0), Direction.NORTH)
        .place(new Point(11, 11), Direction.NORTH)
        .report mustEqual "[0,0], NORTH"
    }
  }
}
