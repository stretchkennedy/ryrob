import scala.io.Source
import scala.io.BufferedSource
import System._
import scala.collection.mutable.LinkedList
import scala.collection.mutable.MutableList

object Console {
  def main(args: Array[String]) {
    val initial = Robot(new Point(10, 10));
    val handleLine = (r: Robot, l: String) => {
      val ret = RobotCommand(l)(r)
      print("# ")
      ret
    }
    print("# ")
    Source.fromInputStream(in).getLines.foldLeft(initial)(handleLine)
  }
}
