package models;

import javax.persistence.*;
import play.data.validation.Constraints.*;

import play.db.ebean.Model;

/**
 * Classe que representa um usuario do sistema.
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

	/**
	 * Construtor de um Usuario.
	 * 
	 * @param nome
	 *            O nome do usuario.
	 * @param login
	 *            O login do usuario
	 * @param senha
	 *            A senha do usuario.
	 */
	public Usuario(String nome, String login, String senha) {
		this.nome = nome;
		this.login = login;
		this.senha = senha;
		plano = new PlanoDeCurso();
	}

	/**
	 * Distribui as disciplinas em seus respectivos períodos.
	 */
	public void distribuiCadeiras() {
		plano.distribuiCaderas(Disciplina.find.all());
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

	/**
	 * Obtem o nome do usuario
	 * 
	 * @return o nome do usuario.
	 */
	public String getNome() {
		return nome;
	}

	/**
	 * modifica o nome do usuario
	 * 
	 * @param nome
	 *            O novo nome do usuario.
	 */
	public void setNome(String nome) {
		this.nome = nome;
	}

	/**
	 * Obtem o login do usuario.
	 * 
	 * @return O login do usuario.
	 */
	public String getLogin() {
		return login;
	}

	/**
	 * Modifica o login
	 * 
	 * @param login
	 *            O novo login
	 */
	public void setLogin(String login) {
		this.login = login;
	}

	/**
	 * Obtem a senha do usuario.
	 */
	public String getSenha() {
		return senha;
	}

	/**
	 * Modifica a senha do usuario
	 * 
	 * @param senha
	 *            A nova senha
	 */
	public void setSenha(String senha) {
		this.senha = senha;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * Formata o usuario como string
	 */
	@Override
	public String toString() {
		return "usuario: " + nome + " - " + login;
	}

	/**
	 * Obtem o plano de curso referente ao usuario.
	 * 
	 * @return O objeto PlanoDeCurso do usuario.
	 */
	public PlanoDeCurso getPlano() {
		return this.plano;
	}

	/**
	 * Compara um usuario com outro pelo nome. Nao diferencia letras maiusculas
	 * de minusculas na comparacao.
	 */
	@Override
	public int compareTo(Usuario outro) {
		return getNome().toLowerCase().compareTo(outro.getNome().toLowerCase());
	}

}