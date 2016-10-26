package controllers

import javax.inject.Inject

import play.api.libs.ws._
import play.api.mvc._
import play.api.Play.current

import scala.concurrent.{Await, TimeoutException}
import scala.concurrent.duration._

import scala.concurrent.ExecutionContext.Implicits.global

class ApiConsumer @Inject() (ws: WSClient) extends Controller {
}
