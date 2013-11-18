class Rectangle(top: Int, bottom: Int, left: Int, right: Int) {
	def doesContain(p: Point): Boolean = 
	  p.y >= top && p.y < bottom &&
	  p.x >= left && p.x < right
}
