
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
object index extends BaseScalaTemplate[play.api.templates.HtmlFormat.Appendable,Format[play.api.templates.HtmlFormat.Appendable]](play.api.templates.HtmlFormat) with play.api.templates.Template1[PlanoDeCurso,play.api.templates.HtmlFormat.Appendable] {

    /**/
    def apply/*1.2*/(plano: PlanoDeCurso):play.api.templates.HtmlFormat.Appendable = {
        _display_ {import helper._


Seq[Any](format.raw/*1.23*/("""

"""),format.raw/*4.1*/("""
<script src="http://code.jquery.com/jquery-1.9.1.js"></script>
<script type='text/javascript' src=""""),_display_(Seq[Any](/*6.38*/routes/*6.44*/.Assets.at("javascripts/funcoes.js"))),format.raw/*6.80*/(""""></script>
<link rel="stylesheet" href=""""),_display_(Seq[Any](/*7.31*/routes/*7.37*/.Assets.at("stylesheets/main.css"))),format.raw/*7.71*/("""">
<link rel="stylesheet" href="//netdna.bootstrapcdn.com/bootstrap/3.1.1/css/bootstrap.min.css">

<body style='background-image:url("""),_display_(Seq[Any](/*10.36*/routes/*10.42*/.Assets.at("images/bg-1.jpg"))),format.raw/*10.71*/(""");'>
"""),_display_(Seq[Any](/*11.2*/main("Meu Plano de Curso")/*11.28*/ {_display_(Seq[Any](format.raw/*11.30*/("""
	<center><h2>"""),_display_(Seq[Any](/*12.15*/Messages("PlanoDeCurso"))),format.raw/*12.39*/("""</h2></center>
	<center><h3 style="position:absolute;left:43%;top:50px;color:#fff;text-shadow:2px 2px #000;">Períodos Do Curso</h3></center>
	<div id="periodos">
		"""),_display_(Seq[Any](/*15.4*/for(periodo <- plano.getPeriodos()) yield /*15.39*/ {_display_(Seq[Any](format.raw/*15.41*/("""
			<div id=""""),_display_(Seq[Any](/*16.14*/periodo/*16.21*/.getNumero())),format.raw/*16.33*/("""" class="periodo" ondrop="drop(event, this)"
				ondragover="allowDrop(event,this)" ondragleave="leave(event, this)">
		
				<ul style="list-style:none;">
					<span style="width:190px;height:30px;font-size:20px;margin-bottom:10px;margin-left:-40px;" color=#000000>"""),_display_(Seq[Any](/*20.112*/periodo/*20.119*/.getNumero())),format.raw/*20.131*/(""" """),_display_(Seq[Any](/*20.133*/Messages("nPeriodo"))),format.raw/*20.153*/(""" </span>
					"""),_display_(Seq[Any](/*21.7*/for(cadeira <- periodo.getDisciplinas()) yield /*21.47*/ {_display_(Seq[Any](format.raw/*21.49*/("""
		  				"""),_display_(Seq[Any](/*22.10*/if(plano.verificaPrerequisito(cadeira.getNome()))/*22.59*/{_display_(Seq[Any](format.raw/*22.60*/("""
		  					<div class="alocadas-vermelho" id=""""),_display_(Seq[Any](/*23.46*/cadeira/*23.53*/.getNome())),format.raw/*23.63*/("""" title="remover" draggable="true" 
								ondragstart="drag(event)" ondragend="dragEnd()">
		  				""")))}/*25.10*/else/*25.14*/{_display_(Seq[Any](format.raw/*25.15*/("""
		  					<div class="alocadas" id=""""),_display_(Seq[Any](/*26.37*/cadeira/*26.44*/.getNome())),format.raw/*26.54*/("""" title="remover" draggable="true" 
								ondragstart="drag(event)" ondragend="dragEnd()">
		  				""")))})),format.raw/*28.10*/("""		  	
		  				<center><label class="nome"> <span>"""),_display_(Seq[Any](/*29.45*/cadeira/*29.52*/.getNome())),format.raw/*29.62*/("""</span></label></center>
		  				<label class="creditos">
		  					<font color=000000>"""),_display_(Seq[Any](/*31.30*/cadeira/*31.37*/.getCreditos())),format.raw/*31.51*/(""" """),_display_(Seq[Any](/*31.53*/Messages("creditos"))),format.raw/*31.73*/("""</font> 
		  				</label>
		  				<label class="dificuldade"> 
		  					<font color=000000>dificuldade: """),_display_(Seq[Any](/*34.43*/cadeira/*34.50*/.getDificuldade())),format.raw/*34.67*/("""</font> 
		  				</label>
		  		
		  				<span style="margin-top: 5px;margin-left:150px;" 
		  					onclick="removerDisciplina('"""),_display_(Seq[Any](/*38.39*/cadeira/*38.46*/.getNome())),format.raw/*38.56*/("""', '"""),_display_(Seq[Any](/*38.61*/plano/*38.66*/.isPreRequisito(cadeira.getNome()))),format.raw/*38.100*/("""')"></span>	
		  				</div>
					""")))})),format.raw/*40.7*/("""	 
				</ul>
				<span class="periodoCreditos">
					<font color=000000>"""),_display_(Seq[Any](/*43.26*/periodo/*43.33*/.getCreditos())),format.raw/*43.47*/(""" """),_display_(Seq[Any](/*43.49*/Messages("creditos"))),format.raw/*43.69*/("""</font>
				</span>
				<span class="periodoDificuldade">
					<font color=000000>Dificuldade: """),_display_(Seq[Any](/*46.39*/periodo/*46.46*/.getDificuldadeTotal())),format.raw/*46.68*/("""</font>
				</span>
			</div>
		""")))})),format.raw/*49.4*/("""
	</div>
	<br/>
	<center><div class="separator"> </div> </center>
	
		<h5 style="float:right;width:70%;position:absolute;top:620px;left:90px;margin:0;color:#000000;font-size:18px;
			background-color:#fff;">
			- Arraste a cadeira para o período que você quer alocá-la
		</h5>
		<div style="width:0%;height:500px;overflow-y:auto;margin-top:20px;margin-left:30px;">
			<ul style="list-style:none;overflow-y:auto;margin-top:20px;"> 
				"""),_display_(Seq[Any](/*60.6*/for(cadeira <- plano.getDisciplinaDispniveisOrdenadas()) yield /*60.62*/ {_display_(Seq[Any](format.raw/*60.64*/("""
					<div class="disponiveis" id=""""),_display_(Seq[Any](/*61.36*/cadeira/*61.43*/.getNome())),format.raw/*61.53*/(""""	draggable="true" 
						ondragstart="drag(event)" ondragend="dragEnd()">
	  						<center><label class="nome">"""),_display_(Seq[Any](/*63.39*/cadeira/*63.46*/.getNome())),format.raw/*63.56*/("""</label></center>
		  					<label class="creditos">"""),_display_(Seq[Any](/*64.35*/cadeira/*64.42*/.getCreditos())),format.raw/*64.56*/(""" """),_display_(Seq[Any](/*64.58*/Messages("creditos"))),format.raw/*64.78*/("""</label>
		  					<label class="dificuldade"> dificuldade: """),_display_(Seq[Any](/*65.52*/cadeira/*65.59*/.getDificuldade())),format.raw/*65.76*/("""</label>
	  				</div>
			""")))})),format.raw/*67.5*/(""" 
			</ul>
		</div>	
	""")))})),format.raw/*70.3*/("""
</body>"""))}
    }
    
    def render(plano:PlanoDeCurso): play.api.templates.HtmlFormat.Appendable = apply(plano)
    
    def f:((PlanoDeCurso) => play.api.templates.HtmlFormat.Appendable) = (plano) => apply(plano)
    
    def ref: this.type = this

}
                /*
                    -- GENERATED --
                    DATE: Thu Mar 06 00:03:21 GMT-03:00 2014
                    SOURCE: C:/Users/Franklin Wesley/Downloads/projeto-si1-master/app/views/index.scala.html
                    HASH: 917cafc590ebb503b56650f1c815102d96854ef3
                    MATRIX: 780->1|911->22|939->41|1075->142|1089->148|1146->184|1223->226|1237->232|1292->266|1462->400|1477->406|1528->435|1569->441|1604->467|1644->469|1695->484|1741->508|1941->673|1992->708|2032->710|2082->724|2098->731|2132->743|2435->1009|2452->1016|2487->1028|2526->1030|2569->1050|2619->1065|2675->1105|2715->1107|2761->1117|2819->1166|2858->1167|2940->1213|2956->1220|2988->1230|3109->1332|3122->1336|3161->1337|3234->1374|3250->1381|3282->1391|3416->1493|3502->1543|3518->1550|3550->1560|3673->1647|3689->1654|3725->1668|3763->1670|3805->1690|3946->1795|3962->1802|4001->1819|4166->1948|4182->1955|4214->1965|4255->1970|4269->1975|4326->2009|4391->2043|4500->2116|4516->2123|4552->2137|4590->2139|4632->2159|4764->2255|4780->2262|4824->2284|4888->2317|5359->2753|5431->2809|5471->2811|5543->2847|5559->2854|5591->2864|5740->2977|5756->2984|5788->2994|5876->3046|5892->3053|5928->3067|5966->3069|6008->3089|6104->3149|6120->3156|6159->3173|6217->3200|6271->3223
                    LINES: 26->1|30->1|32->4|34->6|34->6|34->6|35->7|35->7|35->7|38->10|38->10|38->10|39->11|39->11|39->11|40->12|40->12|43->15|43->15|43->15|44->16|44->16|44->16|48->20|48->20|48->20|48->20|48->20|49->21|49->21|49->21|50->22|50->22|50->22|51->23|51->23|51->23|53->25|53->25|53->25|54->26|54->26|54->26|56->28|57->29|57->29|57->29|59->31|59->31|59->31|59->31|59->31|62->34|62->34|62->34|66->38|66->38|66->38|66->38|66->38|66->38|68->40|71->43|71->43|71->43|71->43|71->43|74->46|74->46|74->46|77->49|88->60|88->60|88->60|89->61|89->61|89->61|91->63|91->63|91->63|92->64|92->64|92->64|92->64|92->64|93->65|93->65|93->65|95->67|98->70
                    -- GENERATED --
                */
            