package models

import play.api.libs.json.Json


case class Thing(myThing: String, count: Int, active: Boolean)

object Thing {
  implicit val thingReads  = Json.reads[Thing]
  implicit val thingWrites = Json.writes[Thing]

  var list: List[Thing] = {
    // default data to start
    List(
      Thing("strange thing", 1, false),
      Thing("odd thing", 26, true)
    )
  }

  def save(thing: Thing): Boolean = {
    val listCount = list.size
    list = list ::: List(thing)
    list.size - listCount == 1
  }
}