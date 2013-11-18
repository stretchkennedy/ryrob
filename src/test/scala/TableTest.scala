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
    "be accessible when point inside and no dirt on the table" in new t1 {
      t.isAccessible(new Point(25, 5)) mustEqual true
      t.isAccessible(new Point(20, 0)) mustEqual true
      t.isAccessible(new Point(29, 9)) mustEqual true
    }
    "return false for hasDirt even if no list is given" in new t1 {
      t.hasDirt mustEqual false
    }

    trait t2 extends Scope {
      val t = new Table(new Rectangle(0, 10, 0, 10), List(new Point(2, 2)))
    }
    "be inaccessed at points of dirt" in new t2 {
      t.isAccessible(new Point(2, 2)) mustEqual false
    }
    "clean dirt at specified locations" in new t2 {
      t
        .clean(new Point(2, 2))
        .isAccessible(new Point(2, 2)) mustEqual true
    }
    "return true for hasDirt if it has dirt" in new t2 {
      t.hasDirt mustEqual true
    }
    "return false for hasDirt if it doesn't have dirt" in new t2 {
      t
        .clean(new Point(2, 2))
        .hasDirt mustEqual false
    }
  }
}
