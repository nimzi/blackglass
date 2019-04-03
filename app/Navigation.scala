package navigation

sealed trait NavPath {
	def title:String
}

case class Home()    extends NavPath {
	def title:String = "Home"
}

case class News()    extends NavPath {
	def title:String = "News"
}

case class Privacy()   extends NavPath {
	def title:String = "Privacy"
}

case class Contact() extends NavPath {
	def title:String = "Contact"
}

