package controllers;

import java.util.List;

import form.FormHandler;
import models.Disciplina;
import models.PlanoDeCurso;
import models.Usuario;
import models.exceptions.LimiteDePeriodosException;
import models.exceptions.LimiteUltrapassadoException;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;

public class Application extends Controller {

	static PlanoDeCurso plano;
	static Form<FormHandler> formHandler = Form.form(FormHandler.class);

	public static Result index(){
		if (plano == null){
			plano = PlanoDeCurso.find.byId(Long.parseLong(session("session")));
			plano.atualizaMapaCadeira(Disciplina.find.all());
		} 
		return ok(views.html.index.render(plano, formHandler));
	}
	
	public static class Login {
        
        public String login;
        public String senha;
        
        public String validate() {
        	List<Usuario> usuarios = Usuario.find.where().eq("login", login).findList();
    		if(usuarios == null || usuarios.isEmpty()){
    			return "login ou senha invalidos.";
    		}
    		session("session", String.valueOf(usuarios.get(0).getPlano().getId()));
    		return null;
        }
        
    }
	
	public static class Cadastro {
		
		public String nome;
        public String login;
        public String senha;
		
	}
	
	public static Result cadastro() {
		return ok(views.html.cadastro.render(Form.form(Cadastro.class)));
	}

	public static Result novoCadastro() {
		Form<Cadastro> cadastroForm = Form.form(Cadastro.class).bindFromRequest();
		String nome = cadastroForm.get().nome;
	    String login = cadastroForm.get().login;
	    String senha = cadastroForm.get().senha;
	    List<Usuario> usuarios = Usuario.find.where().eq("login", login).findList();
	    
	    if(usuarios == null || usuarios.isEmpty()) {
	    	Usuario novo = new Usuario(nome, login, senha);
		    novo.distribuiCadeiras();
			novo.save();
			flash("sucesso", "cadastrou!");
			return login();
	    } else {
	    	flash("fail", "Nome de usuário indisponível");
	    	return cadastro();
	    }
	}
    /**
     * Login page.
     */
    public static Result login() {
    	if(Usuario.find.all().isEmpty()) {
    		Usuario user = new Usuario("nome", "login@login.com", "1");
			user.distribuiCadeiras();
			user.save();
			Usuario user2 = new Usuario("nome", "other@login.com", "1");
			user2.distribuiCadeiras();
			user2.save();
    	}
        return ok(views.html.login.render(Form.form(Login.class)));
    }
    
    /**
     * Handle login form submission.
     */
    public static Result authenticate() {
        Form<Login> loginForm = Form.form(Login.class).bindFromRequest();
        if(loginForm.hasErrors()) {
            return badRequest(views.html.login.render(loginForm));
        } else {
            session("connected", loginForm.get().login);
            return redirect(routes.Application.index());
        }
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

	public static Result remDisciplina(String disciplina){
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
	
	public static Result logout() {
    	plano.update();
    	return redirect(routes.Application.login());
	}
}