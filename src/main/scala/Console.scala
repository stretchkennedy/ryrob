import scala.io.Source
import scala.io.BufferedSource
import System._
import scala.collection.immutable.Map

object Console {
  val csvPairRegex = "^([0-9]+),([0-9]+)$".r
  def getCsvPair(s: String): Tuple2[Int, Int] =
    "\\s".r.replaceAllIn(s, "") match {
      case csvPairRegex(p1, p2) => Tuple2(p1.toInt, p2.toInt)
      case ss                   => throw new Exception("Failed to find csv pair in " + s)
    }
  def main(args: Array[String]) {
    try {
      val options = ArgParser(args, Set('t, 'd)).getOrElse {
        sys.exit(0)
      }

      val dirtLocations =
        Source
          .fromFile(options('d))
          .getLines
          .foldLeft(List[Point]())((l, s) => (new Point(getCsvPair(s)) +: l))

      println(dirtLocations.mkString)

      val boardDimensions =
        getCsvPair(Source.fromFile(options('t)).getLines.next)

      val table = new Table(
        new Rectangle(0, boardDimensions._1, 0, boardDimensions._2),
        dirtLocations)

      val initial = Robot(table);
      val handleLine = (r: Robot, l: String) => {
        val ret = RobotCommand(l)(r)
        if (r.hasWon) {
          println("Game Over")
          sys.exit(0)
        }
        print("# ")
        ret
      }
      print("# ")
      Source.fromInputStream(in).getLines.foldLeft(initial)(handleLine)
    } catch {
      case e: Exception => {
        e.printStackTrace()
        sys.exit(1)
      }
    }
  }
}
