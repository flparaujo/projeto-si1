
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
object index extends BaseScalaTemplate[play.api.templates.HtmlFormat.Appendable,Format[play.api.templates.HtmlFormat.Appendable]](play.api.templates.HtmlFormat) with play.api.templates.Template3[SistemaDePlanejamentoDeCurso,Form[form.FormHandler],String,play.api.templates.HtmlFormat.Appendable] {

    /**/
    def apply/*1.2*/(sistema: SistemaDePlanejamentoDeCurso, formHandler: Form[form.FormHandler], message: String):play.api.templates.HtmlFormat.Appendable = {
        _display_ {import helper._


Seq[Any](format.raw/*1.95*/("""

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
    
    def render(sistema:SistemaDePlanejamentoDeCurso,formHandler:Form[form.FormHandler],message:String): play.api.templates.HtmlFormat.Appendable = apply(sistema,formHandler,message)
    
    def f:((SistemaDePlanejamentoDeCurso,Form[form.FormHandler],String) => play.api.templates.HtmlFormat.Appendable) = (sistema,formHandler,message) => apply(sistema,formHandler,message)
    
    def ref: this.type = this

}
                /*
                    -- GENERATED --
<<<<<<< HEAD
                    DATE: Fri Feb 28 11:12:14 GMT-03:00 2014
                    SOURCE: C:/Users/Franklin Wesley/si1/projeto-si1/app/views/index.scala.html
                    HASH: c6db1e01761f5269d1ebbb4e004bf8fb9eda1942
                    MATRIX: 826->1|1030->94|1060->116|1097->119|1122->136|1161->138|1234->185|1262->186|1463->360|1491->361|2478->1312|2531->1349|2571->1351|2760->1504|2822->1544|2907->1593|3012->1681|3052->1682|3212->1806|3280->1865|3319->1866|3363->1878|3438->1917|3457->1927|3489->1937|3592->2004|3611->2014|3655->2036|3778->2127|3870->2183|3886->2190|3973->2254|4076->2321|4092->2328|4174->2387|4231->2408|4331->2498|4372->2500|4509->2605|4564->2629|4936->2966|4962->2983|5002->2985|5110->3057|5126->3064|5159->3075|5210->3095|5302->3152|5363->3204|5402->3205|5450->3216|5494->3250|5535->3252|5833->3513|5898->3561|5939->3563|6006->3593|6026->3603|6059->3613|6127->3644|6147->3654|6180->3664|6236->3687|6424->3838|6478->3875|6519->3877|6593->3914|6658->3956|6715->3976|6781->4018|6853->4057|7034->4205|7076->4215|7186->4289|7234->4327|7275->4329|7323->4340|7408->4415|7449->4417|7616->4565|7630->4570|7670->4571|7820->4688|7866->4701|8714->5512|8778->5559|8818->5560|8916->5621|8936->5631|8969->5641|9046->5681|9066->5691|9111->5713|9188->5753|9208->5763|9248->5780|9326->5821|9346->5831|9396->5858|9496->5925|9762->6159
=======
                    DATE: Thu Feb 27 13:41:11 BRT 2014
                    SOURCE: /home/felipeaa/herokuProjeto/projeto-si1/app/views/index.scala.html
                    HASH: df024442341c8ecd2eb4a3654749a26d529e98ff
                    MATRIX: 817->1|1011->85|1039->104|1075->106|1100->123|1139->125|1208->168|1236->169|1431->337|1459->338|2408->1251|2461->1288|2501->1290|2685->1438|2747->1478|2830->1525|2935->1613|2975->1614|3132->1735|3200->1794|3239->1795|3283->1807|3355->1843|3374->1853|3406->1863|3506->1927|3525->1937|3569->1959|3688->2046|3778->2100|3794->2107|3881->2171|3982->2236|3998->2243|4080->2302|4136->2322|4236->2412|4277->2414|4410->2515|4463->2537|4825->2864|4851->2881|4891->2883|4998->2954|5014->2961|5047->2972|5097->2991|5186->3045|5247->3097|5286->3098|5333->3108|5377->3142|5418->3144|5710->3399|5775->3447|5816->3449|5882->3478|5902->3488|5935->3498|6003->3529|6023->3539|6056->3549|6111->3571|6295->3718|6349->3755|6390->3757|6463->3793|6528->3835|6585->3855|6651->3897|6721->3934|6899->4079|6940->4088|7045->4157|7093->4195|7134->4197|7181->4207|7266->4282|7307->4284|7472->4430|7486->4435|7526->4436|7674->4551|7719->4563|8544->5351|8608->5398|8648->5399|8744->5458|8764->5468|8797->5478|8873->5517|8893->5527|8938->5549|9014->5588|9034->5598|9074->5615|9151->5655|9171->5665|9221->5692|9319->5757|9572->5978
>>>>>>> 750a635dcd05adba9684535e43d0001ed7f956c1
                    LINES: 26->1|30->1|32->4|33->5|33->5|33->5|37->9|37->9|43->15|43->15|81->53|81->53|81->53|86->58|86->58|88->60|88->60|88->60|91->63|91->63|91->63|91->63|94->66|94->66|94->66|97->69|97->69|97->69|101->73|103->75|103->75|103->75|105->77|105->77|105->77|106->78|106->78|106->78|110->82|112->84|122->94|122->94|122->94|123->95|123->95|123->95|124->96|127->99|127->99|127->99|128->100|128->100|128->100|134->106|134->106|134->106|135->107|135->107|135->107|135->107|135->107|135->107|136->108|140->112|140->112|140->112|141->113|141->113|141->113|141->113|143->115|146->118|147->119|152->124|152->124|152->124|153->125|153->125|153->125|155->127|155->127|155->127|157->129|158->130|181->153|181->153|181->153|183->155|183->155|183->155|184->156|184->156|184->156|185->157|185->157|185->157|186->158|186->158|186->158|188->160|201->173
                    -- GENERATED --
                */
            