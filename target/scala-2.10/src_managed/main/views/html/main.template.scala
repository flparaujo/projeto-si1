
package views.html

import play.templates._
import play.templates.TemplateMagic._

import play.api.templates._
import play.api.templates.PlayMagic._
import models._
import controllers._
import java.lang._
import java.util._
import scala.collection.JavaConversions._
import scala.collection.JavaConverters._
import play.api.i18n._
import play.core.j.PlayMagicForJava._
import play.mvc._
import play.data._
import play.api.data.Field
import play.mvc.Http.Context.Implicit._
import views.html._
/**/
object main extends BaseScalaTemplate[play.api.templates.HtmlFormat.Appendable,Format[play.api.templates.HtmlFormat.Appendable]](play.api.templates.HtmlFormat) with play.api.templates.Template2[String,Html,play.api.templates.HtmlFormat.Appendable] {

    /**/
    def apply/*1.2*/(title: String)(content: Html):play.api.templates.HtmlFormat.Appendable = {
        _display_ {

Seq[Any](format.raw/*1.32*/("""

<!DOCTYPE html>

<html>
    <head>
        <title>"""),_display_(Seq[Any](/*7.17*/title)),format.raw/*7.22*/("""</title>
        <link rel="stylesheet" media="screen" href=""""),_display_(Seq[Any](/*8.54*/routes/*8.60*/.Assets.at("stylesheets/main.css"))),format.raw/*8.94*/("""">
        <!-- bootstrap css -->
        <link rel="stylesheet" type="text/css" media="screen"
        	href=""""),_display_(Seq[Any](/*11.17*/routes/*11.23*/.Assets.at("stylesheets/bootstrap.min.css"))),format.raw/*11.66*/("""">
        <link rel="shortcut icon" type="image/png" href=""""),_display_(Seq[Any](/*12.59*/routes/*12.65*/.Assets.at("images/favicon.png"))),format.raw/*12.97*/("""">
        <link rel="stylesheet" type="text/css" media="screen"
        	href=""""),_display_(Seq[Any](/*14.17*/routes/*14.23*/.Assets.at("stylesheets/xom.css"))),format.raw/*14.56*/("""">
        <script src=""""),_display_(Seq[Any](/*15.23*/routes/*15.29*/.Assets.at("javascripts/jquery-1.9.0.min.js"))),format.raw/*15.74*/("""" type="text/javascript"></script>
        <!-- bootstrap javascript -->
        <script type="text/javascript" src=""""),_display_(Seq[Any](/*17.46*/routes/*17.52*/.Assets.at("javascripts/bootstrap.min.js"))),format.raw/*17.94*/(""""></script> <body>
    	<div class="container">
        	"""),_display_(Seq[Any](/*19.11*/content)),format.raw/*19.18*/("""
        </div>
    </body>
</html>
"""))}
    }
    
    def render(title:String,content:Html): play.api.templates.HtmlFormat.Appendable = apply(title)(content)
    
    def f:((String) => (Html) => play.api.templates.HtmlFormat.Appendable) = (title) => (content) => apply(title)(content)
    
    def ref: this.type = this

}
                /*
                    -- GENERATED --
                    DATE: Thu Feb 27 12:27:17 BRT 2014
                    SOURCE: /home/felipeaa/herokuProjeto/projeto-si1/app/views/main.scala.html
                    HASH: 5839543851f3c0eaff7f64a654eff30f0a68cd95
                    MATRIX: 778->1|902->31|990->84|1016->89|1113->151|1127->157|1182->191|1330->303|1345->309|1410->352|1507->413|1522->419|1576->451|1693->532|1708->538|1763->571|1824->596|1839->602|1906->647|2060->765|2075->771|2139->813|2233->871|2262->878
                    LINES: 26->1|29->1|35->7|35->7|36->8|36->8|36->8|39->11|39->11|39->11|40->12|40->12|40->12|42->14|42->14|42->14|43->15|43->15|43->15|45->17|45->17|45->17|47->19|47->19
                    -- GENERATED --
                */
            