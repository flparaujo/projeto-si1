package controllers;


import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

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
			plano.atualizaValidadores();
		} 
		return ok(views.html.index.render(plano, formHandler));
	}
	
	public static class Login {
        
        public String login;
        public String senha;
        
        public String validate() {
			Usuario user = Usuario.find.where().eq("login", login)
					.eq("senha", stringHexa(gerarHash(senha))).findUnique();
    		if(user == null){
    			return "Login ou senha invalidos.";
    		}
    		session("session", String.valueOf(user.getPlano().getId()));
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

	private static byte[] gerarHash(String frase) {
		  try {
		    MessageDigest md = MessageDigest.getInstance("SHA-256");
		    md.update(frase.getBytes());
		    return md.digest();
		  } catch (NoSuchAlgorithmException e) {
		    return null;
		  }
	}
	
	private static String stringHexa(byte[] bytes) {
		   StringBuilder s = new StringBuilder();
		   for (int i = 0; i < bytes.length; i++) {
		       int parteAlta = ((bytes[i] >> 4) & 0xf) << 4;
		       int parteBaixa = bytes[i] & 0xf;
		       if (parteAlta == 0) s.append('0');
		       s.append(Integer.toHexString(parteAlta | parteBaixa));
		   }
		   return s.toString();
		}
	
	public static Result novoCadastro() {
		geraUsuarios();
		Form<Cadastro> cadastroForm = Form.form(Cadastro.class).bindFromRequest();
		String nome = cadastroForm.get().nome;
	    String login = cadastroForm.get().login;
	    String senha = stringHexa(gerarHash(cadastroForm.get().senha));
	    Usuario user = Usuario.find.where().eq("login", login).findUnique();
	    
	    if(user == null) {
	    	Usuario novo = new Usuario(nome, login, senha);
		    novo.distribuiCadeiras();
			novo.save();
			flash("sucesso", "cadastrou!");
			return cadastro();
	    } else {
	    	flash("fail", "Nome de usuário indisponível");
	    	return cadastro();
	    }
	}
    /**
     * Login page.
     */
    public static Result login() {
        return ok(views.html.login.render(Form.form(Login.class)));
    }
    
    /**
     * Handle login form submission.
     */
    public static Result authenticate() {
    	geraUsuarios();
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
    	session().clear();
    	plano = null;
    	return redirect(routes.Application.login());
	}
	
	private static void geraUsuarios() {
		if(Usuario.find.all().size() < 30) {
			for(int i = 1; i <= 30; i++) {
				Usuario user = new Usuario("Usuario " + i, "usuario" + i + 
						"@meuplano.com", stringHexa(gerarHash(i + "000")));
				user.distribuiCadeiras();
				user.save();
			}
    	}
	}
}
