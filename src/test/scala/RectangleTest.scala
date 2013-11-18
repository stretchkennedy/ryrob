import org.specs2.runner._
import org.specs2.mutable._
import org.specs2.specification._

object RectangleTest extends Specification {
  "Rectangle" should {
    trait r1 extends Scope {
      val r = new Rectangle(0, 10, 20, 30)
    }
    "check the top bounds" in new r1 {
      r.doesContain(new Point(25, -1)) mustEqual false
    }
    "check the bottom bounds" in new r1 {
      r.doesContain(new Point(25, 10)) mustEqual false
    }
    "check the left bounds" in new r1 {
      r.doesContain(new Point(19, 5)) mustEqual false
    }
    "check the right bounds" in new r1 {
      r.doesContain(new Point(30, 5)) mustEqual false
    }
    "be true when the given point is inside" in new r1 {
      r.doesContain(new Point(25, 5)) mustEqual true
      r.doesContain(new Point(20, 0)) mustEqual true
      r.doesContain(new Point(29, 9)) mustEqual true
    }
  }
}
