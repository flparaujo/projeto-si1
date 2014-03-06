// @SOURCE:C:/Users/Franklin Wesley/Downloads/projeto-si1-master/conf/routes
// @HASH:812d818b2b7430d14ea7d2814ae256e5d37a966d
// @DATE:Wed Mar 05 19:28:44 GMT-03:00 2014

import Routes.{prefix => _prefix, defaultPrefix => _defaultPrefix}
import play.core._
import play.core.Router._
import play.core.j._

import play.api.mvc._
import play.libs.F

import Router.queryString


// @LINE:13
// @LINE:12
// @LINE:11
// @LINE:9
// @LINE:6
package controllers {

// @LINE:9
class ReverseAssets {
    

// @LINE:9
def at(file:String): Call = {
   Call("GET", _prefix + { _defaultPrefix } + "assets/" + implicitly[PathBindable[String]].unbind("file", file))
}
                                                
    
}
                          

// @LINE:13
// @LINE:12
// @LINE:11
// @LINE:6
class ReverseApplication {
    

// @LINE:11
def addCadeira(disciplina:String, periodo:Int): Call = {
   Call("POST", _prefix + { _defaultPrefix } + "addCadeira/" + implicitly[PathBindable[String]].unbind("disciplina", dynamicString(disciplina)) + "/" + implicitly[PathBindable[Int]].unbind("periodo", periodo))
}
                                                

// @LINE:13
// @LINE:12
def removerDisciplina(disciplina:String): Call = {
   (disciplina: @unchecked) match {
// @LINE:12
case (disciplina) if true => Call("POST", _prefix + { _defaultPrefix } + "removerDisciplina/" + implicitly[PathBindable[String]].unbind("disciplina", dynamicString(disciplina)))
                                                        
// @LINE:13
case (disciplina) if true => Call("GET", _prefix + { _defaultPrefix } + "removerDisciplina/" + implicitly[PathBindable[String]].unbind("disciplina", dynamicString(disciplina)))
                                                        
   }
}
                                                

// @LINE:6
def index(): Call = {
   Call("GET", _prefix)
}
                                                
    
}
                          
}
                  


// @LINE:13
// @LINE:12
// @LINE:11
// @LINE:9
// @LINE:6
package controllers.javascript {

// @LINE:9
class ReverseAssets {
    

// @LINE:9
def at : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.Assets.at",
   """
      function(file) {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "assets/" + (""" + implicitly[PathBindable[String]].javascriptUnbind + """)("file", file)})
      }
   """
)
                        
    
}
              

// @LINE:13
// @LINE:12
// @LINE:11
// @LINE:6
class ReverseApplication {
    

// @LINE:11
def addCadeira : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.Application.addCadeira",
   """
      function(disciplina,periodo) {
      return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "addCadeira/" + (""" + implicitly[PathBindable[String]].javascriptUnbind + """)("disciplina", encodeURIComponent(disciplina)) + "/" + (""" + implicitly[PathBindable[Int]].javascriptUnbind + """)("periodo", periodo)})
      }
   """
)
                        

// @LINE:13
// @LINE:12
def removerDisciplina : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.Application.removerDisciplina",
   """
      function(disciplina) {
      if (true) {
      return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "removerDisciplina/" + (""" + implicitly[PathBindable[String]].javascriptUnbind + """)("disciplina", encodeURIComponent(disciplina))})
      }
      if (true) {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "removerDisciplina/" + (""" + implicitly[PathBindable[String]].javascriptUnbind + """)("disciplina", encodeURIComponent(disciplina))})
      }
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
        


// @LINE:13
// @LINE:12
// @LINE:11
// @LINE:9
// @LINE:6
package controllers.ref {


// @LINE:9
class ReverseAssets {
    

// @LINE:9
def at(path:String, file:String): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Assets.at(path, file), HandlerDef(this, "controllers.Assets", "at", Seq(classOf[String], classOf[String]), "GET", """ Map static resources from the /public folder to the /assets URL path""", _prefix + """assets/$file<.+>""")
)
                      
    
}
                          

// @LINE:13
// @LINE:12
// @LINE:11
// @LINE:6
class ReverseApplication {
    

// @LINE:11
def addCadeira(disciplina:String, periodo:Int): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Application.addCadeira(disciplina, periodo), HandlerDef(this, "controllers.Application", "addCadeira", Seq(classOf[String], classOf[Int]), "POST", """""", _prefix + """addCadeira/$disciplina<[^/]+>/$periodo<[^/]+>""")
)
                      

// @LINE:12
def removerDisciplina(disciplina:String): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Application.removerDisciplina(disciplina), HandlerDef(this, "controllers.Application", "removerDisciplina", Seq(classOf[String]), "POST", """""", _prefix + """removerDisciplina/$disciplina<[^/]+>""")
)
                      

// @LINE:6
def index(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Application.index(), HandlerDef(this, "controllers.Application", "index", Seq(), "GET", """ Home page""", _prefix + """""")
)
                      
    
}
                          
}
        
    