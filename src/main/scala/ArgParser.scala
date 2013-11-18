object ArgParser {
  type ArgMap = Map[Symbol, String]

  def apply(args: Iterable[String],
            required: Set[Symbol],
            optional: Set[Symbol] = Set()): Option[ArgMap] = {
    val usage = "Usage: -t tablefile -d dirtfile"

    def isSwitch(s: String) =
      if (s.head == '-')
        required.contains(Symbol(s.tail)) || optional.contains(Symbol(s.tail))
      else
        false

    def validate(map: Option[ArgMap]) =
      if (required.foldLeft(true)(
        (b: Boolean, flag: Symbol) =>
          b && map != None && map.get.contains(flag)))
        map
      else {
        println(usage)
        None
      }

    def nextOption(map: ArgMap, list: List[String]): Option[ArgMap] = {
      list match {
        case Nil =>
          Some(map)
        case option :: string :: tail if (isSwitch(option)) =>
          nextOption(map ++ Map(Symbol(option.tail) -> string), tail)
        case option :: tail => {
          println("Unknown option: " + option)
          None
        }
      }
    }
    validate(if (required.isEmpty && optional.isEmpty)
      Some(Map())
    else
      nextOption(Map(), args.toList))
  }
}
