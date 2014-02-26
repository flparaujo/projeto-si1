// @SOURCE:C:/Users/Win 7/si1/meu-plano-de-curso/conf/routes
// @HASH:a0de833fdf067a52bb866290d0ee916031f78ac1
// @DATE:Sat Feb 22 05:50:05 GMT-03:00 2014

import Routes.{prefix => _prefix, defaultPrefix => _defaultPrefix}
import play.core._
import play.core.Router._
import play.core.j._

import play.api.mvc._
import play.libs.F

import Router.queryString


// @LINE:15
// @LINE:12
// @LINE:11
// @LINE:10
// @LINE:9
// @LINE:6
package controllers {

// @LINE:15
class ReverseAssets {
    

// @LINE:15
def at(file:String): Call = {
   Call("GET", _prefix + { _defaultPrefix } + "assets/" + implicitly[PathBindable[String]].unbind("file", file))
}
                                                
    
}
                          

// @LINE:12
// @LINE:11
// @LINE:10
// @LINE:9
// @LINE:6
class ReverseApplication {
    

// @LINE:9
def planejamentoDeCurso(): Call = {
   Call("GET", _prefix + { _defaultPrefix } + "planejamentoDeCurso")
}
                                                

// @LINE:12
def desalocarDisciplinaDePeriodo(): Call = {
   Call("POST", _prefix + { _defaultPrefix } + "planejamentoDeCurso/desalocarDisciplinaDePeriodo")
}
                                                

// @LINE:10
def novoPeriodo(): Call = {
   Call("POST", _prefix + { _defaultPrefix } + "planejamentoDeCurso/novoPeriodo")
}
                                                

// @LINE:11
def adicionaDisciplinaEmPeriodo(): Call = {
   Call("POST", _prefix + { _defaultPrefix } + "planejamentoDeCurso/adicionaDisciplinaEmPeriodo")
}
                                                

// @LINE:6
def index(): Call = {
   Call("GET", _prefix)
}
                                                
    
}
                          
}
                  


// @LINE:15
// @LINE:12
// @LINE:11
// @LINE:10
// @LINE:9
// @LINE:6
package controllers.javascript {

// @LINE:15
class ReverseAssets {
    

// @LINE:15
def at : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.Assets.at",
   """
      function(file) {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "assets/" + (""" + implicitly[PathBindable[String]].javascriptUnbind + """)("file", file)})
      }
   """
)
                        
    
}
              

// @LINE:12
// @LINE:11
// @LINE:10
// @LINE:9
// @LINE:6
class ReverseApplication {
    

// @LINE:9
def planejamentoDeCurso : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.Application.planejamentoDeCurso",
   """
      function() {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "planejamentoDeCurso"})
      }
   """
)
                        

// @LINE:12
def desalocarDisciplinaDePeriodo : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.Application.desalocarDisciplinaDePeriodo",
   """
      function() {
      return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "planejamentoDeCurso/desalocarDisciplinaDePeriodo"})
      }
   """
)
                        

// @LINE:10
def novoPeriodo : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.Application.novoPeriodo",
   """
      function() {
      return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "planejamentoDeCurso/novoPeriodo"})
      }
   """
)
                        

// @LINE:11
def adicionaDisciplinaEmPeriodo : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.Application.adicionaDisciplinaEmPeriodo",
   """
      function() {
      return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "planejamentoDeCurso/adicionaDisciplinaEmPeriodo"})
      }
   """
)
                        

// @LINE:6
def index : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.Application.index",
   """
      function() {
      return _wA({method:"GET", url:"""" + _prefix + """"})
      }
   """
)
                        
    
}
              
}
        


// @LINE:15
// @LINE:12
// @LINE:11
// @LINE:10
// @LINE:9
// @LINE:6
package controllers.ref {


// @LINE:15
class ReverseAssets {
    

// @LINE:15
def at(path:String, file:String): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Assets.at(path, file), HandlerDef(this, "controllers.Assets", "at", Seq(classOf[String], classOf[String]), "GET", """ Map static resources from the /public folder to the /assets URL path""", _prefix + """assets/$file<.+>""")
)
                      
    
}
                          

// @LINE:12
// @LINE:11
// @LINE:10
// @LINE:9
// @LINE:6
class ReverseApplication {
    

// @LINE:9
def planejamentoDeCurso(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Application.planejamentoDeCurso(), HandlerDef(this, "controllers.Application", "planejamentoDeCurso", Seq(), "GET", """ Disciplinas""", _prefix + """planejamentoDeCurso""")
)
                      

// @LINE:12
def desalocarDisciplinaDePeriodo(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Application.desalocarDisciplinaDePeriodo(), HandlerDef(this, "controllers.Application", "desalocarDisciplinaDePeriodo", Seq(), "POST", """""", _prefix + """planejamentoDeCurso/desalocarDisciplinaDePeriodo""")
)
                      

// @LINE:10
def novoPeriodo(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Application.novoPeriodo(), HandlerDef(this, "controllers.Application", "novoPeriodo", Seq(), "POST", """""", _prefix + """planejamentoDeCurso/novoPeriodo""")
)
                      

// @LINE:11
def adicionaDisciplinaEmPeriodo(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Application.adicionaDisciplinaEmPeriodo(), HandlerDef(this, "controllers.Application", "adicionaDisciplinaEmPeriodo", Seq(), "POST", """""", _prefix + """planejamentoDeCurso/adicionaDisciplinaEmPeriodo""")
)
                      

// @LINE:6
def index(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Application.index(), HandlerDef(this, "controllers.Application", "index", Seq(), "GET", """ Home page""", _prefix + """""")
)
                      
    
}
                          
}
        
    