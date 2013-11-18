import org.specs2.runner._
import org.specs2.mutable._
import org.specs2.specification._

object PointTest extends Specification {
  "Point" should {
    trait r1 extends Scope {
      val p = new Point(3, 2)
    }
    "check the top bounds" in new r1 {
      p.isInside(new Rectangle(4, 10, 0, 10)) mustEqual false
    }
    "check the bottom bounds" in new r1 {
      p.isInside(new Rectangle(0, 1, 0, 10)) mustEqual false
    }
    "check the left bounds" in new r1 {
      p.isInside(new Rectangle(0, 10, 4, 10)) mustEqual false
    }
    "check the right bounds" in new r1 {
      p.isInside(new Rectangle(0, 10, 0, 3)) mustEqual false
    }
    "be true when inside the given Rectangle" in new r1 {
      p.isInside(new Rectangle(2, 10, 3, 10)) mustEqual true
      p.isInside(new Rectangle(0, 3, 0, 4)) mustEqual true
    }
  }
}
