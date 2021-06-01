package controllers


import javax.inject._
import play.api.libs.ws.WSClient
import play.api.mvc._

import scala.concurrent.ExecutionContext.Implicits.global


/**
 * This controller creates an `Action` to handle HTTP requests to the
 * application's home page.
 */
@Singleton
class HomeController @Inject()(ws: WSClient, val controllerComponents: ControllerComponents) extends BaseController {

  /**
   * Create an Action to render an HTML page.
   *
   * The configuration in the `routes` file means that this method
   * will be called when the application receives a `GET` request with
   * a path of `/`.
   */
  case class Book (
                  bookId : Int,
                  author : String,
                  pages : Int,
                  category : String
                  )


  def index(country: String) = Action.async{
    ws.url("https://corona.lmao.ninja/v2/countries/"+ country + "?yesterday=true&strict=true&query").get().map(resp =>
      Ok(resp.json)
    )
  }
}
