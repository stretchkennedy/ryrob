import scala.util.Try
import scala.collection.immutable.StringOps

object RobotCommand {
  def apply(com: String): (Robot) => Robot = com.toUpperCase().split(" ", 2).toList match {
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

    case List("MOVE") => _.move
    case List("LEFT") => _.left
    case List("RIGHT") => _.right
    case List("CLEAN") => _.clean

    case List("EXIT") => sys.exit()

    case List("") => (r: Robot) => r
    
    case s => (r: Robot) => {
      println("Unrecognised command: " + s.head)
      r
    }
  }
}

