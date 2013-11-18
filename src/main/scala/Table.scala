class Table(bounds: Rectangle, dirtPiles: Iterable[Point] = List()) {
	def isAccessible(point: Point) = 
	  point.isInside(bounds) && !dirtPiles.exists(_ equals point);
	
	def clean(point: Point) = 
	  new Table(bounds, dirtPiles.filterNot(_ equals point))
	
	def hasDirt = dirtPiles.nonEmpty
}
