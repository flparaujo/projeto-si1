package models;

import java.util.List;

import javax.persistence.*;

import models.exceptions.LimiteDePeriodosException;
import models.exceptions.LimiteUltrapassadoException;
import play.data.validation.Constraints.*;

import play.db.ebean.Model;

@Entity
public class Usuario extends Model {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	Long id;
	
    @Required
	private String nome;
	
    @Required
    @Email
	private String login;
	
    @Required
	private String senha;
	
	@OneToOne(cascade=CascadeType.ALL, fetch=FetchType.EAGER)
	//@JoinTable(name = "usuario_plano", 
    //joinColumns = {@JoinColumn (name = "fk_usuario")}, inverseJoinColumns = {@JoinColumn(name = "fk_plano")})
	private PlanoDeCurso plano;
	
	public Usuario(String nome, String login, String senha) {
		this.nome = nome;
		this.login = login;
		this.senha = senha;
		plano = new PlanoDeCurso();
	} 
	
	public void setPeriodoAtualPlano(int novoPeriodoAtual) {
		plano.setPeriodoAtual(novoPeriodoAtual);
	}
	
	public void atualizaCadeiras() {
		plano.atualizaMapaCadeira(plano.getDisciplinasAlocadas());
	}
	
	public void distribuiCadeiras() {
		plano.distribuiCaderas(Disciplina.find.all());
	}
	
	public boolean minimoDeCreditosSatisfeitos() {
		return plano.minimoCreditosSatisfeitos();
	}
	
	public int getIdPeriodoAtual() {
		return plano.getPeriodoAtual();
	}
	
	public PlanoDeCurso getPlano() {
		return plano;
	}
	
	public int getTotalDeCreditos() {
		return plano.getTotalDeCreditos();
	}
	
	public int getMinimoDeCreditosConcluir() {
		return plano.getMinimoCreditosConcluir();
	}
	
	public int getTotalCreditosCursados() {
		return plano.getCreditosCursados();
	}
	
	public List<Periodo> getPeriodosPlano() {
		return plano.getPeriodos();
	}
	
	public List<Disciplina> getDisciplinaDispniveisOrdenadasPlano() {
		return plano.getDisciplinaDispniveisOrdenadas();
	} 
	
	public void adicionaDisciplina(String nome, int periodo) throws LimiteUltrapassadoException {
		plano.adicionaDisciplina(nome, periodo);
	}
	
	public void removeDisciplina(String nome) {
		plano.removeDisciplina(nome);
	}
	
	public void adicionaPeriodo() throws LimiteDePeriodosException {
		plano.adicionaPeriodo();
	}
	
	public static Finder<Long,Usuario> find = new Finder<Long,Usuario>(
		    Long.class, Usuario.class
	);
	
	public static void create(Usuario user) {
		user.save();
	}

	public static void delete(Long id) {
		find.ref(id).delete();
	}
	
	public static void atualizar(Long id) {
		Usuario user = find.ref(id);
		user.update();
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public Long getId(){
		return id;
	}	
	
	public void setId(Long id){
		this.id = id;	
	}
	
	public String toString() {
		return "usuario: " + nome + " - " + login;
	}

}