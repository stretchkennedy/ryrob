import org.specs2.runner._
import org.specs2.mutable._
import org.specs2.specification._

object RobotSpec extends Specification {
  "Robot" should {
    trait c1 extends Scope {
      val table = new Table(new Rectangle(0, 10, 0, 10))
      val robot = Robot(table)
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
        .place(new Point(9, 9), Direction.SOUTH)
        .move
        .report mustEqual "[9,9], SOUTH"
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
    
    trait r2 extends Scope {
      val table = new Table(
        new Rectangle(0, 10, 0, 10),
        List(new Point(0, 0)))
      val robot = Robot(table).place(new Point(0, 1), Direction.NORTH)
    }
    "not move if it runs into a dirt pile" in new r2 {
      robot
        .move
        .report mustEqual "[0,1], NORTH"
    }
    "be able to clean a dirt pile and then move into that space" in new r2 {
      robot
        .clean
        .move
        .report mustEqual "[0,0], NORTH"
    }
    "be in a winning state after clearing all dirt from the table" in new r2 {
      robot
      .clean
      .hasWon mustEqual true
    }
    
    trait r3 extends Scope {
      val table = new Table(
        new Rectangle(0, 10, 0, 10),
        List(new Point(0, 0)))
      val robot = Robot(table)
    }
    "must be invalid if placed on a dirt pile" in new r3 {
      robot
        .place(new Point(0, 0), Direction.NORTH)
        .report mustEqual "INVALID ROBOT PLACEMENT"
    }
  }
}
