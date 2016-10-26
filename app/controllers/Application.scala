package controllers

import play.api._
import play.api.mvc._
import models.Thing
import play.api.libs.json.Json

class Application extends Controller {

  def index = Action {
    Ok(views.html.index("Your new application is ready."))
  }

  def json(parm: String) = Action {
    val thing1 = Thing(s"One ${parm} thing", 1, false)
    val thing2 = Thing(s"Leads to another: ${parm}", 2, true)
    val allThings = Array(thing1, thing2)
    Ok(Json toJson allThings)
  }

  def saveThing = Action(BodyParsers.parse.json) { request =>
    val res = request.body.validate[Thing].map {
      case thing => {
        if (Thing.save(thing)) {
          Ok(Json.obj("status" -> "OK", "message" -> s"Your Thing '${thing.myThing}' successfully sent."))
        }else{
          UnprocessableEntity(Json.obj("status" -> "Unprocessable", "message" -> s"Failed to save: ${thing.myThing}"))
        }
      }
      case _ => BadRequest(Json.obj("status" -> "KO"))
    }
    res.get
  }

}
