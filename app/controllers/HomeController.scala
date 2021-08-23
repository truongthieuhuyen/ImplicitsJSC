package controllers

import javax.inject._
import play.api._
import play.api.mvc._
import play.twirl.api.Html

/**
 * This controller creates an `Action` to handle HTTP requests to the
 * application's home page.
 */
@Singleton
class HomeController @Inject()(val controllerComponents: ControllerComponents) extends BaseController {

  /**
   * Create an Action to render an HTML page.
   *
   * The configuration in the `routes` file means that this method
   * will be called when the application receives a `GET` request with
   * a path of `/`.
   */
  def index() = Action { implicit request: Request[AnyContent] =>
    Ok(views.html.index())
  }

  def toWebPage() = Action{ implicit request: Request[AnyContent] =>
    Ok(views.html.web())
  }

  def toSAAS() = Action{ implicit request: Request[AnyContent] =>
    Ok(views.html.SAAS())
  }

  def toMobilePage() =Action{ implicit request: Request[AnyContent] =>
    Ok(views.html.mobile())
  }

  def toContactPage() = Action { implicit request: Request[AnyContent] =>
    Ok(views.html.contact())
  }

  def sendMessage() = TODO
}
