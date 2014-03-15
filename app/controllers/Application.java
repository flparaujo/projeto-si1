package controllers;

import form.FormHandler;
import models.Usuario;
import models.exceptions.LimiteDePeriodosException;
import models.exceptions.LimiteUltrapassadoException;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;

public class Application extends Controller {

	static Usuario user;
	static Form<FormHandler> formHandler = Form.form(FormHandler.class);

	public static Result index(){
		if (user == null) {
			if (!Usuario.find.all().isEmpty()){
				user = Usuario.find.all().get(0);
				user.atualizaCadeiras();
			} else { 
				user = new Usuario("", "", "");
				user.distribuiCadeiras();
				user.save();
			}
		}
		return ok(views.html.index.render(user, formHandler));
	}
	
	public static Result addCadeira(String disciplina, int periodo){
		try {
			user.adicionaDisciplina(disciplina, periodo);
		} catch (LimiteUltrapassadoException e) {
			return badRequest(e.getMessage());
		}
		user.update();
		return redirect(routes.Application.index());
	}

	public static Result remDisciplina(String disciplina){
		user.removeDisciplina(disciplina);
		user.update();
		return redirect(routes.Application.index());
	}
	
	public static Result novoPeriodo() {
    	try {
			user.adicionaPeriodo();
		} catch (LimiteDePeriodosException e) {
			return badRequest(e.getMessage());
		}
    	user.update();
    	return redirect(routes.Application.index());
    }
	
	public static Result setPeriodoAtual() {
		Form<FormHandler> form = formHandler.bindFromRequest();
    	int idPeriodo = form.get().getIdPeriodo();
		user.setPeriodoAtualPlano(idPeriodo);
		user.update();
		return redirect(routes.Application.index());
	}
}