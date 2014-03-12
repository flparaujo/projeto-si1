package controllers;

import form.FormHandler;
import models.Disciplina;
import models.Periodo;
import models.PlanoDeCurso;
import models.exceptions.LimiteDePeriodosException;
import models.exceptions.LimiteUltrapassadoException;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;

public class Application extends Controller {

	static PlanoDeCurso plano;
	static Form<FormHandler> formHandler = Form.form(FormHandler.class);

	public static Result index(){
		if (plano == null) {
			if (!PlanoDeCurso.find.all().isEmpty()){
				plano = PlanoDeCurso.find.all().get(0);
				plano.atualizaMapaCadeira(plano.getDisciplinasAlocadas());
			} else { 
				plano = new PlanoDeCurso();
				plano.distribuiCaderas(Disciplina.find.all());
				plano.save();
			}
		}
		return ok(views.html.index.render(plano, formHandler));
	}
	
	public static Result addCadeira(String disciplina, int periodo){
		try {
			plano.adicionaDisciplina(disciplina, periodo);
		} catch (LimiteUltrapassadoException e) {
			return badRequest(e.getMessage());
		}
		plano.update();
		return redirect(routes.Application.index());
	}

	public static Result remCadeira(String disciplina){
		plano.removeDisciplina(disciplina);
		plano.update();
		return redirect(routes.Application.index());
	}
	
	public static Result novoPeriodo() {
    	try {
			plano.adicionaPeriodo();
		} catch (LimiteDePeriodosException e) {
			return badRequest(e.getMessage());
		}
    	plano.update();
    	return redirect(routes.Application.index());
    }
	
	public static Result setPeriodoAtual() {
		Form<FormHandler> form = formHandler.bindFromRequest();
    	int idPeriodo = form.get().getIdPeriodo();
		plano.setPeriodoAtual(idPeriodo);
		plano.update();
		return redirect(routes.Application.index());
	}
}