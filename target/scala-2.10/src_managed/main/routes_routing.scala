// @SOURCE:C:/Users/Win 7/si1/meu-plano-de-curso/conf/routes
// @HASH:a0de833fdf067a52bb866290d0ee916031f78ac1
// @DATE:Sat Feb 22 05:50:05 GMT-03:00 2014


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
private[this] lazy val controllers_Application_adicionaDisciplinaEmPeriodo3 = Route("POST", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("planejamentoDeCurso/adicionaDisciplinaEmPeriodo"))))
        

// @LINE:12
private[this] lazy val controllers_Application_desalocarDisciplinaDePeriodo4 = Route("POST", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("planejamentoDeCurso/desalocarDisciplinaDePeriodo"))))
        

// @LINE:15
private[this] lazy val controllers_Assets_at5 = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("assets/"),DynamicPart("file", """.+""",false))))
        
def documentation = List(("""GET""", prefix,"""controllers.Application.index()"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """planejamentoDeCurso""","""controllers.Application.planejamentoDeCurso()"""),("""POST""", prefix + (if(prefix.endsWith("/")) "" else "/") + """planejamentoDeCurso/novoPeriodo""","""controllers.Application.novoPeriodo()"""),("""POST""", prefix + (if(prefix.endsWith("/")) "" else "/") + """planejamentoDeCurso/adicionaDisciplinaEmPeriodo""","""controllers.Application.adicionaDisciplinaEmPeriodo()"""),("""POST""", prefix + (if(prefix.endsWith("/")) "" else "/") + """planejamentoDeCurso/desalocarDisciplinaDePeriodo""","""controllers.Application.desalocarDisciplinaDePeriodo()"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """assets/$file<.+>""","""controllers.Assets.at(path:String = "/public", file:String)""")).foldLeft(List.empty[(String,String,String)]) { (s,e) => e.asInstanceOf[Any] match {
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
case controllers_Application_adicionaDisciplinaEmPeriodo3(params) => {
   call { 
        invokeHandler(controllers.Application.adicionaDisciplinaEmPeriodo(), HandlerDef(this, "controllers.Application", "adicionaDisciplinaEmPeriodo", Nil,"POST", """""", Routes.prefix + """planejamentoDeCurso/adicionaDisciplinaEmPeriodo"""))
   }
}
        

// @LINE:12
case controllers_Application_desalocarDisciplinaDePeriodo4(params) => {
   call { 
        invokeHandler(controllers.Application.desalocarDisciplinaDePeriodo(), HandlerDef(this, "controllers.Application", "desalocarDisciplinaDePeriodo", Nil,"POST", """""", Routes.prefix + """planejamentoDeCurso/desalocarDisciplinaDePeriodo"""))
   }
}
        

// @LINE:15
case controllers_Assets_at5(params) => {
   call(Param[String]("path", Right("/public")), params.fromPath[String]("file", None)) { (path, file) =>
        invokeHandler(controllers.Assets.at(path, file), HandlerDef(this, "controllers.Assets", "at", Seq(classOf[String], classOf[String]),"GET", """ Map static resources from the /public folder to the /assets URL path""", Routes.prefix + """assets/$file<.+>"""))
   }
}
        
}

}
     