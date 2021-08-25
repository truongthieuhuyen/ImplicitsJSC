package controllers

import models.{UserInMemory}
import javax.inject.{Inject, Singleton}
import play.api.mvc.{BaseController, ControllerComponents}
import play.api.data.Forms._
import play.api.data._

case class LoginData(username: String, password: String)

@Singleton
class UserController @Inject()(val controllerComponents: ControllerComponents) extends BaseController{
  val loginForm = Form(mapping(
    "Username" -> text(3,16),
    "Password" -> text(8)
  )(LoginData.apply)(LoginData.unapply))

  def login = Action{ implicit request =>
    Ok(views.html.login(loginForm))
  }

  def validateLoginForm = Action{ implicit request =>
    loginForm.bindFromRequest().fold(
      formWithErrors => BadRequest(views.html.login(formWithErrors)),
      ld =>
        if (UserInMemory.validateUser(ld.username,ld.password)){
          Redirect(routes.HomeController.index()).withSession("username" -> ld.username)
        }
        else{
          Redirect(routes.UserController.login).flashing("error" -> "incorrect username/password")
        }
    )
  }

  def register = Action { implicit request =>
    Ok(views.html.register())
  }

//  def createUser = Action { implicit request =>
//    val postVals = request.body.asFormUrlEncoded
//    postVals.map { args =>
//      val username = args("Username").head
//      val password = args("Password").head
//      if (UserInMemory.createUser(username, password)) {
//        Redirect(routes.UserController.taskList).withSession("username" -> username)
//      }
//      else {
//        Redirect(routes.UserController.register)
//      }
//    }.getOrElse(Redirect(routes.UserController.register))
//  }
}
