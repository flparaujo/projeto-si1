package controllers;

import models.PlanejamentoDeCurso;
import exceptions.LimiteDeCreditosExcedidoException;
import exceptions.LimiteDePeriodosException;
import form.FormHandler;
import play.data.Form;
import play.mvc.*;

public class Application extends Controller {
	
	static PlanejamentoDeCurso sistema = new PlanejamentoDeCurso();
	static Form<FormHandler> formHandler = Form.form(FormHandler.class);
	static String message = "";

    public static Result index() {
    	return redirect(routes.Application.planejamentoDeCurso());
    }
    
    public static Result planejamentoDeCurso() {
    	String tempMsg = message; 
    	message = ""; 
    	return ok(views.html.index.render(sistema, formHandler, tempMsg));
    }
    
    public static Result novoPeriodo() {
    	try {
			sistema.adicionaPeriodo();
		} catch (LimiteDePeriodosException e) {
			message = e.getMessage();
		}
    	return redirect(routes.Application.planejamentoDeCurso());
    }
    
    public static Result moveDisciplinaParaPeriodo() {
    	Form<FormHandler> form = formHandler.bindFromRequest();
    	int idPeriodo = form.get().getIdPeriodo()-1;
    	try {
    		sistema.moveDisciplina(form.get().getInputNameDisciplina(), idPeriodo);
    	}
    	catch(LimiteDeCreditosExcedidoException e) {
    		 message = e.getMessage();
    	}
    	return redirect(routes.Application.planejamentoDeCurso());
    }

}
