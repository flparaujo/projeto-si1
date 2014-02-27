
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
											<font """),_display_(Seq[Any](/*63.19*/if(!sistema.preRequisitosSatisfeitos(disciplina.getNome()))/*63.78*/{_display_(Seq[Any](format.raw/*63.79*/("""color="red"""")))})),format.raw/*63.91*/(""">
												<nome>

													"""),_display_(Seq[Any](/*66.15*/disciplina/*66.25*/.getNome())),format.raw/*66.35*/("""
												
												</nome>
												<descricao> ---- """),_display_(Seq[Any](/*69.31*/disciplina/*69.41*/.getNumeroDeCreditos())),format.raw/*69.63*/(""" creditos</descricao>
											</font>
			 							</div>
				 					</div>
			  					""")))})),format.raw/*73.12*/("""	
			  					<br>
		 		 				<nome> Total de créditos: """),_display_(Seq[Any](/*75.38*/sistema/*75.45*/.numeroDeCreditosDoPeriodo(sistema.getPeriodos.indexOf(periodo)))),format.raw/*75.109*/("""</nome>
		 		 				<br>
		 		 				<nome> Dificuldade do Período: """),_display_(Seq[Any](/*77.43*/sistema/*77.50*/.dificuldadeDoPeriodo(sistema.getPeriodos.indexOf(periodo)))),format.raw/*77.109*/("""</nome>
		   						"""),_display_(Seq[Any](/*78.13*/if(sistema.periodoComCreditosAbaixoDoLimiteMinimo(sistema.getPeriodos().indexOf(periodo)))/*78.103*/ {_display_(Seq[Any](format.raw/*78.105*/("""
									<br>
									<nome>Aviso: Mínimo de creditos não atingido</nome>
									<br>
		  						""")))})),format.raw/*82.12*/("""
								<br>
							""")))})),format.raw/*84.9*/("""
							<br>
							<div id="c2" class="coluna"></div>
							<div id="c3" class="coluna"></div>
							<div id="c4" class="coluna"></div>
							<div id="c5" class="coluna"></div>
							<div id="c6" class="coluna"></div>
							<div id="c7" class="coluna"></div>
							<div id="c8" class="coluna"></div>
						</div>
						"""),_display_(Seq[Any](/*94.8*/if(message != "")/*94.25*/ {_display_(Seq[Any](format.raw/*94.27*/("""
			 				<SCRIPT LANGUAGE="JavaScript" TYPE="text/javascript">alert (""""),_display_(Seq[Any](/*95.71*/message/*95.78*/.toString())),format.raw/*95.89*/("""")</SCRIPT>
						""")))})),format.raw/*96.8*/("""
						<br>
						<div id="alocarDisciplina">
							"""),_display_(Seq[Any](/*99.9*/form(routes.Application.moveDisciplinaParaPeriodo())/*99.61*/{_display_(Seq[Any](format.raw/*99.62*/("""
								"""),_display_(Seq[Any](/*100.10*/if(sistema.getPeriodos.size() > 1)/*100.44*/ {_display_(Seq[Any](format.raw/*100.46*/("""
		    						<nome> --------------------------- </nome>
			   		 				<br>
			    					<nome> Move disciplina para um periodo </nome>
									<br>
									<select class="selectpicker" data-live-search="true" name="inputNameDisciplina" >
        								"""),_display_(Seq[Any](/*106.18*/for(disciplina <- sistema.disciplinasAlocadas()) yield /*106.66*/ {_display_(Seq[Any](format.raw/*106.68*/(""" 
											<option value=""""),_display_(Seq[Any](/*107.28*/disciplina/*107.38*/.getNome())),format.raw/*107.48*/("""" name="inputNameDisciplina" >"""),_display_(Seq[Any](/*107.79*/disciplina/*107.89*/.getNome())),format.raw/*107.99*/("""</option> 
										""")))})),format.raw/*108.12*/("""	
        							</select> 
	        						<br>
    	    						<select class="selectpicker" data-width="100px" name="idPeriodo" >
        								"""),_display_(Seq[Any](/*112.18*/for(periodo <- sistema.getPeriodos()) yield /*112.55*/ {_display_(Seq[Any](format.raw/*112.57*/("""
        		   							<option value="""),_display_(Seq[Any](/*113.36*/(sistema.getPeriodos().indexOf(periodo)+1))),format.raw/*113.78*/(""" name="idPeriodo" >"""),_display_(Seq[Any](/*113.98*/(sistema.getPeriodos().indexOf(periodo)+1))),format.raw/*113.140*/("""</option> 
			   								
										""")))})),format.raw/*115.12*/("""
        							</select> 
	        						<button type="submit" class="btn btn-primary" type="button">mover disciplina</button>		
    	    					""")))})),format.raw/*118.16*/("""
							""")))})),format.raw/*119.9*/("""
						</div>
						
						<br>
						<div id="addperiodos">
							"""),_display_(Seq[Any](/*124.9*/form(routes.Application.novoPeriodo())/*124.47*/ {_display_(Seq[Any](format.raw/*124.49*/("""
								"""),_display_(Seq[Any](/*125.10*/if(sistema.numeroDeCreditosDoPeriodo(sistema.getPeriodos().size() -1) < 14)/*125.85*/ {_display_(Seq[Any](format.raw/*125.87*/("""
									<button type="submit" class="btn btn-large btn-primary disabled" type="button" disabled="disabled">Adicionar periodo</button>
								""")))}/*127.11*/else/*127.16*/{_display_(Seq[Any](format.raw/*127.17*/("""
									<button type="submit" class="btn btn-large btn-primary type="button">Adicionar periodo</button>
								""")))})),format.raw/*129.10*/("""
	    					""")))})),format.raw/*130.12*/("""	
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
        								"""),_display_(Seq[Any](/*153.18*/for(disciplina <- sistema.disciplinasDoCurso()) yield /*153.65*/{_display_(Seq[Any](format.raw/*153.66*/("""
            								<tr>
                    							<td >"""),_display_(Seq[Any](/*155.34*/disciplina/*155.44*/.getNome())),format.raw/*155.54*/("""</td>
                    							<td >"""),_display_(Seq[Any](/*156.34*/disciplina/*156.44*/.getNumeroDeCreditos())),format.raw/*156.66*/("""</td>
                    							<td >"""),_display_(Seq[Any](/*157.34*/disciplina/*157.44*/.getDificuldade())),format.raw/*157.61*/("""</td>
                 					   			<td >"""),_display_(Seq[Any](/*158.35*/disciplina/*158.45*/.getPreRequisitosToString())),format.raw/*158.72*/("""</td>                
            								</tr>
        								""")))})),format.raw/*160.18*/("""
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
""")))})),format.raw/*173.2*/("""
"""))}
    }
    
    def render(sistema:PlanejamentoDeCurso,formHandler:Form[form.FormHandler],message:String): play.api.templates.HtmlFormat.Appendable = apply(sistema,formHandler,message)
    
    def f:((PlanejamentoDeCurso,Form[form.FormHandler],String) => play.api.templates.HtmlFormat.Appendable) = (sistema,formHandler,message) => apply(sistema,formHandler,message)
    
    def ref: this.type = this

}
                /*
                    -- GENERATED --
                    DATE: Thu Feb 27 12:57:07 GMT-03:00 2014
                    SOURCE: C:/Users/Franklin Wesley/si1/projeto-si1/app/views/index.scala.html
                    HASH: 01f580da2d790b138223c8b534a8357c57ce6447
                    MATRIX: 817->1|1012->85|1042->107|1079->110|1104->127|1143->129|1216->176|1244->177|1445->351|1473->352|2460->1303|2513->1340|2553->1342|2742->1495|2804->1535|2889->1584|2994->1672|3034->1673|3194->1797|3262->1856|3301->1857|3345->1869|3420->1908|3439->1918|3471->1928|3574->1995|3593->2005|3637->2027|3760->2118|3852->2174|3868->2181|3955->2245|4058->2312|4074->2319|4156->2378|4213->2399|4313->2489|4354->2491|4491->2596|4546->2620|4918->2957|4944->2974|4984->2976|5092->3048|5108->3055|5141->3066|5192->3086|5284->3143|5345->3195|5384->3196|5432->3207|5476->3241|5517->3243|5815->3504|5880->3552|5921->3554|5988->3584|6008->3594|6041->3604|6109->3635|6129->3645|6162->3655|6218->3678|6406->3829|6460->3866|6501->3868|6575->3905|6640->3947|6697->3967|6763->4009|6835->4048|7016->4196|7058->4206|7168->4280|7216->4318|7257->4320|7305->4331|7390->4406|7431->4408|7598->4556|7612->4561|7652->4562|7802->4679|7848->4692|8696->5503|8760->5550|8800->5551|8898->5612|8918->5622|8951->5632|9028->5672|9048->5682|9093->5704|9170->5744|9190->5754|9230->5771|9308->5812|9328->5822|9378->5849|9478->5916|9744->6150
                    LINES: 26->1|30->1|32->4|33->5|33->5|33->5|37->9|37->9|43->15|43->15|81->53|81->53|81->53|86->58|86->58|88->60|88->60|88->60|91->63|91->63|91->63|91->63|94->66|94->66|94->66|97->69|97->69|97->69|101->73|103->75|103->75|103->75|105->77|105->77|105->77|106->78|106->78|106->78|110->82|112->84|122->94|122->94|122->94|123->95|123->95|123->95|124->96|127->99|127->99|127->99|128->100|128->100|128->100|134->106|134->106|134->106|135->107|135->107|135->107|135->107|135->107|135->107|136->108|140->112|140->112|140->112|141->113|141->113|141->113|141->113|143->115|146->118|147->119|152->124|152->124|152->124|153->125|153->125|153->125|155->127|155->127|155->127|157->129|158->130|181->153|181->153|181->153|183->155|183->155|183->155|184->156|184->156|184->156|185->157|185->157|185->157|186->158|186->158|186->158|188->160|201->173
                    -- GENERATED --
                */
            