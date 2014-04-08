package models;

import javax.persistence.*;
import play.data.validation.Constraints.*;

import play.db.ebean.Model;

/**
 * Essa classe representa um usuario.
 */
@Entity
public class Usuario extends Model implements Comparable<Usuario> {

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

	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private PlanoDeCurso plano;

	public Usuario(String nome, String login, String senha) {
		this.nome = nome;
		this.login = login;
		this.senha = senha;
		plano = new PlanoDeCurso();
	}

	public void distribuiCadeiras(Fluxograma fluxograma) {
		plano.distribuiCaderas(fluxograma.getDisciplinas(), fluxograma.getNumeroDePeriodos());
	}
	
	/**
	 * Distribui as disciplinas em seus respectivos períodos.
	 */
	public void distribuiCadeiras() {
		plano.distribuiCaderas(Disciplina.find.all(), Periodo.find.all().size());
	}

	public static Finder<Long, Usuario> find = new Finder<Long, Usuario>(
			Long.class, Usuario.class);

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

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String toString() {
		return "usuario: " + nome + " - " + login;
	}

	public PlanoDeCurso getPlano() {
		return this.plano;
	}

	@Override
	public int compareTo(Usuario outro) {
		return getNome().toLowerCase().compareTo(outro.getNome().toLowerCase());
	}

}