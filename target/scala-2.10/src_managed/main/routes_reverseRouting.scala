// @SOURCE:C:/Users/Franklin Wesley/si1/projeto-si1/conf/routes
// @HASH:446e2f6b548f44e50505a968742af288d8c63152
// @DATE:Thu Feb 27 12:57:04 GMT-03:00 2014

import Routes.{prefix => _prefix, defaultPrefix => _defaultPrefix}
import play.core._
import play.core.Router._
import play.core.j._

import play.api.mvc._
import play.libs.F

import Router.queryString


// @LINE:14
// @LINE:11
// @LINE:10
// @LINE:9
// @LINE:6
package controllers {

// @LINE:14
class ReverseAssets {
    

// @LINE:14
def at(file:String): Call = {
   Call("GET", _prefix + { _defaultPrefix } + "assets/" + implicitly[PathBindable[String]].unbind("file", file))
}
                                                
    
}
                          

// @LINE:11
// @LINE:10
// @LINE:9
// @LINE:6
class ReverseApplication {
    

// @LINE:9
def planejamentoDeCurso(): Call = {
   Call("GET", _prefix + { _defaultPrefix } + "planejamentoDeCurso")
}
                                                

// @LINE:11
def moveDisciplinaParaPeriodo(): Call = {
   Call("POST", _prefix + { _defaultPrefix } + "planejamentoDeCurso/moveDisciplinaParaPeriodo")
}
                                                

// @LINE:6
def index(): Call = {
   Call("GET", _prefix)
}
                                                

// @LINE:10
def novoPeriodo(): Call = {
   Call("POST", _prefix + { _defaultPrefix } + "planejamentoDeCurso/novoPeriodo")
}
                                                
    
}
                          
}
                  


// @LINE:14
// @LINE:11
// @LINE:10
// @LINE:9
// @LINE:6
package controllers.javascript {

// @LINE:14
class ReverseAssets {
    

// @LINE:14
def at : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.Assets.at",
   """
      function(file) {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "assets/" + (""" + implicitly[PathBindable[String]].javascriptUnbind + """)("file", file)})
      }
   """
)
                        
    
}
              

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
                        

// @LINE:11
def moveDisciplinaParaPeriodo : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.Application.moveDisciplinaParaPeriodo",
   """
      function() {
      return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "planejamentoDeCurso/moveDisciplinaParaPeriodo"})
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
                        

// @LINE:10
def novoPeriodo : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.Application.novoPeriodo",
   """
      function() {
      return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "planejamentoDeCurso/novoPeriodo"})
      }
   """
)
                        
    
}
              
}
        


// @LINE:14
// @LINE:11
// @LINE:10
// @LINE:9
// @LINE:6
package controllers.ref {


// @LINE:14
class ReverseAssets {
    

// @LINE:14
def at(path:String, file:String): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Assets.at(path, file), HandlerDef(this, "controllers.Assets", "at", Seq(classOf[String], classOf[String]), "GET", """ Map static resources from the /public folder to the /assets URL path""", _prefix + """assets/$file<.+>""")
)
                      
    
}
                          

// @LINE:11
// @LINE:10
// @LINE:9
// @LINE:6
class ReverseApplication {
    

// @LINE:9
def planejamentoDeCurso(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Application.planejamentoDeCurso(), HandlerDef(this, "controllers.Application", "planejamentoDeCurso", Seq(), "GET", """ Disciplinas""", _prefix + """planejamentoDeCurso""")
)
                      

// @LINE:11
def moveDisciplinaParaPeriodo(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Application.moveDisciplinaParaPeriodo(), HandlerDef(this, "controllers.Application", "moveDisciplinaParaPeriodo", Seq(), "POST", """""", _prefix + """planejamentoDeCurso/moveDisciplinaParaPeriodo""")
)
                      

// @LINE:6
def index(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Application.index(), HandlerDef(this, "controllers.Application", "index", Seq(), "GET", """ Home page""", _prefix + """""")
)
                      

// @LINE:10
def novoPeriodo(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Application.novoPeriodo(), HandlerDef(this, "controllers.Application", "novoPeriodo", Seq(), "POST", """""", _prefix + """planejamentoDeCurso/novoPeriodo""")
)
                      
    
}
                          
}
        
    