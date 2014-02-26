
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
object index extends BaseScalaTemplate[play.api.templates.HtmlFormat.Appendable,Format[play.api.templates.HtmlFormat.Appendable]](play.api.templates.HtmlFormat) with play.api.templates.Template3[PlanejamentoDeCurso,Form[form.FormHandler],String,play.api.templates.HtmlFormat.Appendable] {

    /**/
    def apply/*1.2*/(sistema: PlanejamentoDeCurso, formHandler: Form[form.FormHandler], message: String):play.api.templates.HtmlFormat.Appendable = {
        _display_ {import helper._


Seq[Any](format.raw/*1.86*/("""

"""),format.raw/*4.1*/("""
"""),_display_(Seq[Any](/*5.2*/main("Meu Curso")/*5.19*/ {_display_(Seq[Any](format.raw/*5.21*/("""
	
	<style type="text/css">
		<!--
			body """),format.raw/*9.9*/("""{"""),format.raw/*9.10*/("""
				margin-top: 0px;
				background-image: url(assets/images/bg.jpg);
				background-repeat: repeat-x;
				background-position: top;
				background-color: #E7E7E7;
			"""),format.raw/*15.4*/("""}"""),format.raw/*15.5*/("""
		-->
	</style>

	<body>
		<div class="containerWrap">
		  	<div class="header">
    			<h1>Meu Curso</h1>
    			<div class="content" id="wrapper">
      				<article>
        				<h3 class="shad">Planejamento de curso</h3>
        				<center>
        					<img src="assets/images/estudos.jpg" class="img-polaroid" style="width:450; height:280px;">
        				</center>
      				</article>
					<aside>
						<br>
						<br>
						<br>
        				<hgroup>
         					<h1>Bem-vindo ao Meu Curso</h1>
         					<br>
          					<h2>Planeje seu curso com facilidade e rapidez.</h2>
        				</hgroup>
						<br>
    					<br>
    					<br>
    					<br>
    					<br>
    					<br>
    					<br>
    				</aside> 				
    					<font color="black" size=12>Periodos</font>
    	    			<br>
    	    			<br>
    	    			<br>
        				Planeje seus periodos aqui. 
						<div id="colunas">
	 						"""),_display_(Seq[Any](/*53.10*/for(periodo <- sistema.getPeriodos()) yield /*53.47*/ {_display_(Seq[Any](format.raw/*53.49*/("""
	 							<br>
	 							<nome> ------------------------------------------------------- </nome>
	 	 						<br>
		 	  					<h2>	
		 	  						<small> """),_display_(Seq[Any](/*58.22*/(sistema.getPeriodos.indexOf(periodo)+1))),format.raw/*58.62*/("""º periodo </small>
	 		  					</h2>
		  						"""),_display_(Seq[Any](/*60.12*/for(disciplina <- sistema.getDisciplinasDoPeriodo(sistema.getPeriodos.indexOf(periodo))) yield /*60.100*/{_display_(Seq[Any](format.raw/*60.101*/("""
			 						<div id="c1" class="coluna">
			 							<div class="disciplina" name="inputNameDisciplina">
											<nome>"""),_display_(Seq[Any](/*63.19*/disciplina/*63.29*/.getNome())),format.raw/*63.39*/(""" </nome>
											<descricao> ---- """),_display_(Seq[Any](/*64.30*/disciplina/*64.40*/.getNumeroDeCreditos())),format.raw/*64.62*/(""" creditos</descricao>
			 							</div>
				 					</div>
			  					""")))})),format.raw/*67.12*/("""	
			  					<br>
		 		 				<nome> Total de créditos: """),_display_(Seq[Any](/*69.38*/sistema/*69.45*/.numeroDeCreditosDoPeriodo(sistema.getPeriodos.indexOf(periodo)))),format.raw/*69.109*/("""</nome>
		 		 				<br>
		 		 				<nome> Dificuldade do Período: """),_display_(Seq[Any](/*71.43*/sistema/*71.50*/.dificuldadeDoPeriodo(sistema.getPeriodos.indexOf(periodo)))),format.raw/*71.109*/("""</nome>
		   						"""),_display_(Seq[Any](/*72.13*/if(sistema.periodoComCreditosAbaixoDoLimiteMinimo(sistema.getPeriodos().indexOf(periodo)))/*72.103*/ {_display_(Seq[Any](format.raw/*72.105*/("""
									<br>
									<nome>Aviso: Mínimo de creditos não atingido</nome>
									<br>
		  						""")))})),format.raw/*76.12*/("""
								<br>
							""")))})),format.raw/*78.9*/("""
							<br>
							<div id="c2" class="coluna"></div>
							<div id="c3" class="coluna"></div>
							<div id="c4" class="coluna"></div>
							<div id="c5" class="coluna"></div>
							<div id="c6" class="coluna"></div>
							<div id="c7" class="coluna"></div>
							<div id="c8" class="coluna"></div>
						</div>
						"""),_display_(Seq[Any](/*88.8*/if(message != "")/*88.25*/ {_display_(Seq[Any](format.raw/*88.27*/("""
			 				<SCRIPT LANGUAGE="JavaScript" TYPE="text/javascript">alert (""""),_display_(Seq[Any](/*89.71*/message/*89.78*/.toString())),format.raw/*89.89*/("""")</SCRIPT>
						""")))})),format.raw/*90.8*/("""
						<br>
						<div id="alocarDisciplina">
							"""),_display_(Seq[Any](/*93.9*/form(routes.Application.adicionaDisciplinaEmPeriodo())/*93.63*/{_display_(Seq[Any](format.raw/*93.64*/("""
								"""),_display_(Seq[Any](/*94.10*/if(sistema.getPeriodos.size() > 1)/*94.44*/ {_display_(Seq[Any](format.raw/*94.46*/("""
		    						<nome> --------------------------- </nome>
			   		 				<br>
			    					<nome> Alocar Disciplina a Periodo</nome>
									<br>
									<select class="selectpicker" data-live-search="true" name="inputNameDisciplina" >
        								"""),_display_(Seq[Any](/*100.18*/for(disciplina <- sistema.listaDisciplinasDoCurso()) yield /*100.70*/ {_display_(Seq[Any](format.raw/*100.72*/(""" 
											<option value=""""),_display_(Seq[Any](/*101.28*/disciplina/*101.38*/.getNome())),format.raw/*101.48*/("""" name="inputNameDisciplina" >"""),_display_(Seq[Any](/*101.79*/disciplina/*101.89*/.getNome())),format.raw/*101.99*/("""</option> 
										""")))})),format.raw/*102.12*/("""	
        							</select> 
	        						<br>
    	    						<select class="selectpicker" data-width="100px" name="idPeriodo" >
        								"""),_display_(Seq[Any](/*106.18*/for(periodo <- sistema.getPeriodos()) yield /*106.55*/ {_display_(Seq[Any](format.raw/*106.57*/("""
        		   							"""),_display_(Seq[Any](/*107.22*/if(sistema.getPeriodos().indexOf(periodo) != 0)/*107.69*/ {_display_(Seq[Any](format.raw/*107.71*/("""
						 						<option value="""),_display_(Seq[Any](/*108.29*/(sistema.getPeriodos().indexOf(periodo)+1))),format.raw/*108.71*/(""" name="idPeriodo" >"""),_display_(Seq[Any](/*108.91*/(sistema.getPeriodos().indexOf(periodo)+1))),format.raw/*108.133*/("""</option> 
			   								""")))})),format.raw/*109.16*/("""
										""")))})),format.raw/*110.12*/("""
        							</select> 
	        						<button type="submit" class="btn btn-primary" type="button">Alocar Disciplina</button>		
    	    					""")))})),format.raw/*113.16*/("""
							""")))})),format.raw/*114.9*/("""
						</div>
						
						<br>
						<div id="addperiodos">
							"""),_display_(Seq[Any](/*119.9*/form(routes.Application.novoPeriodo())/*119.47*/ {_display_(Seq[Any](format.raw/*119.49*/("""
								"""),_display_(Seq[Any](/*120.10*/if(sistema.numeroDeCreditosDoPeriodo(sistema.getPeriodos().size() -1) < 14)/*120.85*/ {_display_(Seq[Any](format.raw/*120.87*/("""
									<button type="submit" class="btn btn-large btn-primary disabled" type="button" disabled="disabled">Adicionar periodo</button>
								""")))}/*122.11*/else/*122.16*/{_display_(Seq[Any](format.raw/*122.17*/("""
									<button type="submit" class="btn btn-large btn-primary type="button">Adicionar periodo</button>
								""")))})),format.raw/*124.10*/("""
	    					""")))})),format.raw/*125.12*/("""	
    					</div>
    					<br>
    					<br>
    					<br>
    					<hr>
    					<font color="black" size=12>Grade Curricular</font>
    					<br>
    					<br>
    					<br>
        				<form name="contactformfree" method="post" action="free_process.php" onSubmit="return validate.check(this)">
							<table width="400px" class="cffree">
								<tr>
	 								<td colspan="2"></td>
								</tr>
								<div id="gradeCurricular">
									<table class="table table-hover">
            	       					<tr style="background-color: #8B8B7A;">
                	       					<th >Disciplina</td>
                    	   					<th >Creditos</td>
                    	   					<th >Dificuldade</td>
                       						<th >Requisitos</td>
          	     						</tr>
        								"""),_display_(Seq[Any](/*148.18*/for(disciplina <- sistema.listaDisciplinasDoCurso()) yield /*148.70*/{_display_(Seq[Any](format.raw/*148.71*/("""
            								<tr>
                    							<td >"""),_display_(Seq[Any](/*150.34*/disciplina/*150.44*/.getNome())),format.raw/*150.54*/("""</td>
                    							<td >"""),_display_(Seq[Any](/*151.34*/disciplina/*151.44*/.getNumeroDeCreditos())),format.raw/*151.66*/("""</td>
                    							<td >"""),_display_(Seq[Any](/*152.34*/disciplina/*152.44*/.getDificuldade())),format.raw/*152.61*/("""</td>
                 					   			<td >"""),_display_(Seq[Any](/*153.35*/disciplina/*153.45*/.getPreRequisitosToString())),format.raw/*153.72*/("""</td>                
            								</tr>
        								""")))})),format.raw/*155.18*/("""
    								</table>
								</div>
							</table>
        				</form>
        				
    			</div>
			</div>
		</div>
 		<footer>
			<img src="assets/images/fundo_rodape.jpg" class="img-polaroid">
		</footer> 
	</body>
""")))})),format.raw/*168.2*/("""
"""))}
    }
    
    def render(sistema:PlanejamentoDeCurso,formHandler:Form[form.FormHandler],message:String): play.api.templates.HtmlFormat.Appendable = apply(sistema,formHandler,message)
    
    def f:((PlanejamentoDeCurso,Form[form.FormHandler],String) => play.api.templates.HtmlFormat.Appendable) = (sistema,formHandler,message) => apply(sistema,formHandler,message)
    
    def ref: this.type = this

}
                /*
                    -- GENERATED --
                    DATE: Sat Feb 22 10:02:27 GMT-03:00 2014
                    SOURCE: C:/Users/Win 7/si1/meu-plano-de-curso/app/views/index.scala.html
                    HASH: 2434e1a077d0966a382130955f730dd3e304ac39
                    MATRIX: 817->1|1011->85|1039->104|1075->106|1100->123|1139->125|1208->168|1236->169|1431->337|1459->338|2408->1251|2461->1288|2501->1290|2685->1438|2747->1478|2830->1525|2935->1613|2975->1614|3132->1735|3151->1745|3183->1755|3257->1793|3276->1803|3320->1825|3420->1893|3510->1947|3526->1954|3613->2018|3714->2083|3730->2090|3812->2149|3868->2169|3968->2259|4009->2261|4142->2362|4195->2384|4557->2711|4583->2728|4623->2730|4730->2801|4746->2808|4779->2819|4829->2838|4918->2892|4981->2946|5020->2947|5066->2957|5109->2991|5149->2993|5436->3243|5505->3295|5546->3297|5612->3326|5632->3336|5665->3346|5733->3377|5753->3387|5786->3397|5841->3419|6025->3566|6079->3603|6120->3605|6179->3627|6236->3674|6277->3676|6343->3705|6408->3747|6465->3767|6531->3809|6590->3835|6635->3847|6814->3993|6855->4002|6960->4071|7008->4109|7049->4111|7096->4121|7181->4196|7222->4198|7387->4344|7401->4349|7441->4350|7589->4465|7634->4477|8459->5265|8528->5317|8568->5318|8664->5377|8684->5387|8717->5397|8793->5436|8813->5446|8858->5468|8934->5507|8954->5517|8994->5534|9071->5574|9091->5584|9141->5611|9239->5676|9492->5897
                    LINES: 26->1|30->1|32->4|33->5|33->5|33->5|37->9|37->9|43->15|43->15|81->53|81->53|81->53|86->58|86->58|88->60|88->60|88->60|91->63|91->63|91->63|92->64|92->64|92->64|95->67|97->69|97->69|97->69|99->71|99->71|99->71|100->72|100->72|100->72|104->76|106->78|116->88|116->88|116->88|117->89|117->89|117->89|118->90|121->93|121->93|121->93|122->94|122->94|122->94|128->100|128->100|128->100|129->101|129->101|129->101|129->101|129->101|129->101|130->102|134->106|134->106|134->106|135->107|135->107|135->107|136->108|136->108|136->108|136->108|137->109|138->110|141->113|142->114|147->119|147->119|147->119|148->120|148->120|148->120|150->122|150->122|150->122|152->124|153->125|176->148|176->148|176->148|178->150|178->150|178->150|179->151|179->151|179->151|180->152|180->152|180->152|181->153|181->153|181->153|183->155|196->168
                    -- GENERATED --
                */
            