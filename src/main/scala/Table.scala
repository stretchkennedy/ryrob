class Table(bounds: Rectangle) {
	def isAccessible(point: Point): Boolean = 
	  point.isInside(bounds);
}
