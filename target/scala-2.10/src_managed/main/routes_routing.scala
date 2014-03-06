// @SOURCE:C:/Users/Franklin Wesley/Downloads/projeto-si1-master/conf/routes
// @HASH:812d818b2b7430d14ea7d2814ae256e5d37a966d
// @DATE:Wed Mar 05 19:28:44 GMT-03:00 2014


import play.core._
import play.core.Router._
import play.core.j._

import play.api.mvc._
import play.libs.F

import Router.queryString

object Routes extends Router.Routes {

private var _prefix = "/"

def setPrefix(prefix: String) {
  _prefix = prefix
  List[(String,Routes)]().foreach {
    case (p, router) => router.setPrefix(prefix + (if(prefix.endsWith("/")) "" else "/") + p)
  }
}

def prefix = _prefix

lazy val defaultPrefix = { if(Routes.prefix.endsWith("/")) "" else "/" }


// @LINE:6
private[this] lazy val controllers_Application_index0 = Route("GET", PathPattern(List(StaticPart(Routes.prefix))))
        

// @LINE:9
private[this] lazy val controllers_Assets_at1 = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("assets/"),DynamicPart("file", """.+""",false))))
        

// @LINE:11
private[this] lazy val controllers_Application_addCadeira2 = Route("POST", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("addCadeira/"),DynamicPart("disciplina", """[^/]+""",true),StaticPart("/"),DynamicPart("periodo", """[^/]+""",true))))
        

// @LINE:12
private[this] lazy val controllers_Application_removerDisciplina3 = Route("POST", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("removerDisciplina/"),DynamicPart("disciplina", """[^/]+""",true))))
        

// @LINE:13
private[this] lazy val controllers_Application_removerDisciplina4 = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("removerDisciplina/"),DynamicPart("disciplina", """[^/]+""",true))))
        
def documentation = List(("""GET""", prefix,"""controllers.Application.index()"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """assets/$file<.+>""","""controllers.Assets.at(path:String = "/public", file:String)"""),("""POST""", prefix + (if(prefix.endsWith("/")) "" else "/") + """addCadeira/$disciplina<[^/]+>/$periodo<[^/]+>""","""controllers.Application.addCadeira(disciplina:String, periodo:Int)"""),("""POST""", prefix + (if(prefix.endsWith("/")) "" else "/") + """removerDisciplina/$disciplina<[^/]+>""","""controllers.Application.removerDisciplina(disciplina:String)"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """removerDisciplina/$disciplina<[^/]+>""","""controllers.Application.removerDisciplina(disciplina:String)""")).foldLeft(List.empty[(String,String,String)]) { (s,e) => e.asInstanceOf[Any] match {
  case r @ (_,_,_) => s :+ r.asInstanceOf[(String,String,String)]
  case l => s ++ l.asInstanceOf[List[(String,String,String)]] 
}}
      

def routes:PartialFunction[RequestHeader,Handler] = {

// @LINE:6
case controllers_Application_index0(params) => {
   call { 
        invokeHandler(controllers.Application.index(), HandlerDef(this, "controllers.Application", "index", Nil,"GET", """ Home page""", Routes.prefix + """"""))
   }
}
        

// @LINE:9
case controllers_Assets_at1(params) => {
   call(Param[String]("path", Right("/public")), params.fromPath[String]("file", None)) { (path, file) =>
        invokeHandler(controllers.Assets.at(path, file), HandlerDef(this, "controllers.Assets", "at", Seq(classOf[String], classOf[String]),"GET", """ Map static resources from the /public folder to the /assets URL path""", Routes.prefix + """assets/$file<.+>"""))
   }
}
        

// @LINE:11
case controllers_Application_addCadeira2(params) => {
   call(params.fromPath[String]("disciplina", None), params.fromPath[Int]("periodo", None)) { (disciplina, periodo) =>
        invokeHandler(controllers.Application.addCadeira(disciplina, periodo), HandlerDef(this, "controllers.Application", "addCadeira", Seq(classOf[String], classOf[Int]),"POST", """""", Routes.prefix + """addCadeira/$disciplina<[^/]+>/$periodo<[^/]+>"""))
   }
}
        

// @LINE:12
case controllers_Application_removerDisciplina3(params) => {
   call(params.fromPath[String]("disciplina", None)) { (disciplina) =>
        invokeHandler(controllers.Application.removerDisciplina(disciplina), HandlerDef(this, "controllers.Application", "removerDisciplina", Seq(classOf[String]),"POST", """""", Routes.prefix + """removerDisciplina/$disciplina<[^/]+>"""))
   }
}
        

// @LINE:13
case controllers_Application_removerDisciplina4(params) => {
   call(params.fromPath[String]("disciplina", None)) { (disciplina) =>
        invokeHandler(controllers.Application.removerDisciplina(disciplina), HandlerDef(this, "controllers.Application", "removerDisciplina", Seq(classOf[String]),"GET", """""", Routes.prefix + """removerDisciplina/$disciplina<[^/]+>"""))
   }
}
        
}

}
     