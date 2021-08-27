package controllers

import models.UserInMemory

import javax.inject.{Inject, Singleton}
import play.api.mvc.{BaseController, ControllerComponents, MessagesAbstractController, MessagesControllerComponents}
import play.api.data.Forms._
import play.api.data._

case class LoginData(username: String, password: String)

case class RegisterData(username: String, password: String)

@Singleton
class UserController @Inject()(val cc: MessagesControllerComponents) extends MessagesAbstractController(cc) {
  val loginForm = Form(mapping(
    "Username" -> text(5, 16),
    "Password" -> text(8)
  )(LoginData.apply)(LoginData.unapply))

  val registerForm = Form(mapping(
    "Username" -> text(5, 16),
    "Password" -> text(8)
  )(RegisterData.apply)(RegisterData.unapply))

  def login = Action { implicit request =>
    Ok(views.html.login(loginForm))
  }

  def validateLoginForm = Action { implicit request =>
    loginForm.bindFromRequest().fold(
      formWithErrors => BadRequest(views.html.login(formWithErrors)),
      ld =>
        if (UserInMemory.validateUser(ld.username, ld.password)) {
          Redirect(routes.HomeController.index()).withSession("username" -> ld.username)
        }
        else {
          Redirect(routes.UserController.login).flashing("error" -> "incorrect username/password")
        }
    )
  }
  def logout = Action {
    Redirect(routes.UserController.login).withNewSession
  }

  def register = Action { implicit request =>
    Ok(views.html.register(registerForm))
  }

  def createUser = Action { implicit request =>
    val postVals = request.body.asFormUrlEncoded
    postVals.map { args =>
      val username = args("Username").head
      val password = args("Password").head
      if (UserInMemory.createUser(username, password)) {
        Redirect(routes.HomeController.index).withSession("username" -> username)
      }
      else {
        Redirect(routes.UserController.register)
      }
    }.getOrElse(Redirect(routes.UserController.register))
  }

  def validateRegisterForm = Action { implicit request =>
    registerForm.bindFromRequest().fold(
      formRWithErrors => BadRequest(views.html.register(formRWithErrors)),
      rd =>
        if (UserInMemory.validateNewUser(rd.username, rd.password)) {
          Redirect(routes.UserController.login).withSession("username" -> rd.username)
        }
        else {
          Redirect(routes.UserController.register).flashing("error" -> "invalid username/password\n please input again")
        }
    )
  }
}
