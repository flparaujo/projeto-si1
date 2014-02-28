<<<<<<< HEAD
// @SOURCE:C:/Users/Franklin Wesley/si1/projeto-si1/conf/routes
// @HASH:8096231e1b1d93bdf9ab7a80631a7f24732d4887
// @DATE:Fri Feb 28 11:19:20 GMT-03:00 2014
=======
// @SOURCE:/home/felipeaa/herokuProjeto/projeto-si1/conf/routes
// @HASH:e7509f0b490347ac336a2ed26e80801c9d543e5e
// @DATE:Thu Feb 27 13:41:10 BRT 2014
>>>>>>> 750a635dcd05adba9684535e43d0001ed7f956c1


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
private[this] lazy val controllers_Application_planejamentoDeCurso1 = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("planejamentoDeCurso"))))
        

// @LINE:10
private[this] lazy val controllers_Application_novoPeriodo2 = Route("POST", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("planejamentoDeCurso/novoPeriodo"))))
        

// @LINE:11
private[this] lazy val controllers_Application_moveDisciplinaParaPeriodo3 = Route("POST", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("planejamentoDeCurso/moveDisciplinaParaPeriodo"))))
        

// @LINE:14
private[this] lazy val controllers_Assets_at4 = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("assets/"),DynamicPart("file", """.+""",false))))
        
def documentation = List(("""GET""", prefix,"""controllers.Application.index()"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """planejamentoDeCurso""","""controllers.Application.planejamentoDeCurso()"""),("""POST""", prefix + (if(prefix.endsWith("/")) "" else "/") + """planejamentoDeCurso/novoPeriodo""","""controllers.Application.novoPeriodo()"""),("""POST""", prefix + (if(prefix.endsWith("/")) "" else "/") + """planejamentoDeCurso/moveDisciplinaParaPeriodo""","""controllers.Application.moveDisciplinaParaPeriodo()"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """assets/$file<.+>""","""controllers.Assets.at(path:String = "/public", file:String)""")).foldLeft(List.empty[(String,String,String)]) { (s,e) => e.asInstanceOf[Any] match {
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
case controllers_Application_planejamentoDeCurso1(params) => {
   call { 
        invokeHandler(controllers.Application.planejamentoDeCurso(), HandlerDef(this, "controllers.Application", "planejamentoDeCurso", Nil,"GET", """ Disciplinas""", Routes.prefix + """planejamentoDeCurso"""))
   }
}
        

// @LINE:10
case controllers_Application_novoPeriodo2(params) => {
   call { 
        invokeHandler(controllers.Application.novoPeriodo(), HandlerDef(this, "controllers.Application", "novoPeriodo", Nil,"POST", """""", Routes.prefix + """planejamentoDeCurso/novoPeriodo"""))
   }
}
        

// @LINE:11
case controllers_Application_moveDisciplinaParaPeriodo3(params) => {
   call { 
        invokeHandler(controllers.Application.moveDisciplinaParaPeriodo(), HandlerDef(this, "controllers.Application", "moveDisciplinaParaPeriodo", Nil,"POST", """""", Routes.prefix + """planejamentoDeCurso/moveDisciplinaParaPeriodo"""))
   }
}
        

// @LINE:14
case controllers_Assets_at4(params) => {
   call(Param[String]("path", Right("/public")), params.fromPath[String]("file", None)) { (path, file) =>
        invokeHandler(controllers.Assets.at(path, file), HandlerDef(this, "controllers.Assets", "at", Seq(classOf[String], classOf[String]),"GET", """ Map static resources from the /public folder to the /assets URL path""", Routes.prefix + """assets/$file<.+>"""))
   }
}
        
}

}
     