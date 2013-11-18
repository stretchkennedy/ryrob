import org.specs2.runner._
import org.specs2.mutable._
import org.specs2.specification._

object TableTest extends Specification {
  "Table" should {
    trait t1 extends Scope {
      val t = new Table(new Rectangle(0, 10, 20, 30))
    }
    "check the top bounds" in new t1 {
      t.isAccessible(new Point(-1, 25)) mustEqual false
    }
    "check the bottom bounds" in new t1 {
      t.isAccessible(new Point(10, 25)) mustEqual false
    }
    "check the left bounds" in new t1 {
      t.isAccessible(new Point(5, 19)) mustEqual false
    }
    "check the right bounds" in new t1 {
      t.isAccessible(new Point(5, 30)) mustEqual false
    }
    "be true when the given point is inside" in new t1 {
      t.isAccessible(new Point(25, 5)) mustEqual true
      t.isAccessible(new Point(20, 0)) mustEqual true
      t.isAccessible(new Point(29, 9)) mustEqual true
    }
  }
}
