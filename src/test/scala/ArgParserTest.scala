import org.specs2.runner._
import org.specs2.mutable._
import org.specs2.specification._

object ArgParserTest extends Specification {
  "ArgParse" should {
    "reject arguments without required parameters" in {
      ArgParser(List("-a", "meaningless"), Set('a, 'b)) mustEqual None
    }
    "reject arguments with unknown parameters" in {
      val args = List("-a", "meaningless", "-b", "same")
      ArgParser(args, Set('a)) mustEqual None
    }
    "accept blank arguments if there are no required parameters" in {
      ArgParser(List(), Set()).get mustEqual Map()
    }
    "accept arguments if all required parameters are present" in {
      val args = List("-a", "meaningless", "-b", "same")
      ArgParser(args, Set('a), Set('b)).get.contains('a) mustEqual true
      ArgParser(args, Set('a), Set('b)).get.contains('b) mustEqual true
    }
  }
}
