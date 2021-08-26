package models

import scala.collection.mutable

object UserInMemory {
  private val users = mutable.Map[String, String]("admin" -> "password")

  def validateUser(username: String, password: String): Boolean ={
    users.get(username).map(_ == password).getOrElse(false)
  }

  def createUser(username: String, password: String):Boolean ={
    if(users.contains(username)) false
    else{
      users(username) = password
      true
    }
  }
  def validateNewUser(username: String, password: String): Boolean ={
    if (users.contains(username) || username.length().<(3) || username.length.>(16) || password.isEmpty) false
    else {
      users(username) = password
      true
    }
  }
}
