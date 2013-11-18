import org.specs2.runner._
import org.specs2.mutable._
import org.specs2.specification._

object BoardTest extends Specification {
  "Board" should {
    trait b1 extends Scope {
      val b = new Board(new Rectangle(0, 10, 20, 30))
    }
    "check the top bounds" in new b1 {
      b.isAccessible(new Point(-1, 25)) mustEqual false
    }
    "check the bottom bounds" in new b1 {
      b.isAccessible(new Point(10, 25)) mustEqual false
    }
    "check the left bounds" in new b1 {
      b.isAccessible(new Point(5, 19)) mustEqual false
    }
    "check the right bounds" in new b1 {
      b.isAccessible(new Point(5, 30)) mustEqual false
    }
    "be true when the given point is inside" in new b1 {
      b.isAccessible(new Point(25, 5)) mustEqual true
      b.isAccessible(new Point(20, 0)) mustEqual true
      b.isAccessible(new Point(29, 9)) mustEqual true
    }
  }
}
