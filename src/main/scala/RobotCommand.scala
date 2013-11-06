import scala.util.Try
import scala.collection.immutable.StringOps

object RobotCommand {
  def apply(com: String): (Robot) => Robot = com.split(" ", 2).toList.map(_.toUpperCase) match {
    case List("PLACE", args) => (r: Robot) => {
      args.mkString.split(",").map(_.trim) match {
        case Array(x, y, dir) => {
          Try(r.place(new Point(x.toInt, y.toInt), Direction.withName(dir)))
            .getOrElse(r)
        }
        case _ => {
          println("Invalid arguments: " + com)
          r
        }
      }
    }
    case List("PLACE", _) => (r: Robot) => {
      println("PLACE requires arguments")
      r
    }

    case List("REPORT") => (r: Robot) => {
      println(r.report)
      r
    }

    case List("MOVE") => (r: Robot) => r.move
    case List("LEFT") => (r: Robot) => r.left
    case List("RIGHT") => (r: Robot) => r.right

    case List("EXIT") => sys.exit()

    case s => (r: Robot) => {
      println("Unrecognised command: " + s.head)
      r
    }
  }
}

