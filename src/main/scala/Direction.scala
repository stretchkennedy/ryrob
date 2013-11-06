object Direction extends Enumeration() {
  type Direction = Value
  val North, South, East, West = Value

  class DirectionValue(dir: Value) {
    def left: Direction = {
      dir match {
        case North => West
        case West => South
        case South => East
        case East => North
      }
    }
    
    def right: Direction = {
      dir match {
        case North => East
        case East => South
        case South => West
        case West => North
      }
    }
  }
  
  implicit def value2DirectionValue(dir: Value) = new DirectionValue(dir)
}
