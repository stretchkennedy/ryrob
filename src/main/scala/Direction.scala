object Direction extends Enumeration() {
  type Direction = Value
  val NORTH, SOUTH, EAST, WEST = Value

  class DirectionValue(dir: Value) {
    def left: Direction = {
      dir match {
        case NORTH => WEST
        case WEST  => SOUTH
        case SOUTH => EAST
        case EAST  => NORTH
      }
    }

    def right: Direction = {
      dir match {
        case NORTH => EAST
        case EAST  => SOUTH
        case SOUTH => WEST
        case WEST  => NORTH
      }
    }
  }

  implicit def value2DirectionValue(dir: Value) = new DirectionValue(dir)
}
