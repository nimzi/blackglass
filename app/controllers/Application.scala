package controllers

import play.api._
import play.api.mvc._

import play.api.Play.current

import play.api.db._
import navigation._

import javax.inject.Inject
import play.api.Logger

import play.api.data._
import play.api.i18n._

import scala.util.{Try, Success, Failure}

object Application extends Controller {

  def asInt(s: String): Option[Int] = Try(s.trim.toInt) match {
    case Success(x) => Some(x)
    case _ => None
  }

  def index = Action { implicit request =>
    Ok(views.html.index(Home()))
  }

  def news = Action { implicit request =>
    Ok(views.html.index(News()))
  }

  def contact = Action { implicit request =>
    Ok(views.html.index(Contact()))
  }

  def about = Action { implicit request =>
    Ok(views.html.index(Privacy()))
  }

  
  def createWidget = Action { implicit request =>

    val x = for  { 
              rr <- request.body.asFormUrlEncoded 
              human <- rr.lift("human")
              n <- asInt(human.last)
            } 
            yield n


    val revr = routes.Application.contact
    x match {
      case Some(n) if n == 5 => Redirect(revr).flashing("success" -> "Success, message sent!")
      case _ => Redirect(revr).flashing("success" -> "Failure, please look over the form.")
    }

    // val r = request.body.asFormUrlEncoded
    // if (!r.isEmpty) {
    //   var rr:Map[String,Seq[String]] = r.get
    //   for ( (a,b) <- rr) {
    //     Logger.info(a + ":" +  b.last )
    //   }

    //   rr.lift("human")
    // }

    // Redirect(routes.Application.contact).flashing("success" -> "Success")
  }

  // def db = Action {
  //   var out = ""
  //   val conn = DB.getConnection()
  //   try {
  //     val stmt = conn.createStatement

  //     stmt.executeUpdate("CREATE TABLE IF NOT EXISTS ticks (tick timestamp)")
  //     stmt.executeUpdate("INSERT INTO ticks VALUES (now())")

  //     val rs = stmt.executeQuery("SELECT tick FROM ticks")

  //     while (rs.next) {
  //       out += "Read from DB: " + rs.getTimestamp("tick") + "\n"
  //     }
  //   } finally {
  //     conn.close()
  //   }
  //   Ok(out)
  // }
}
