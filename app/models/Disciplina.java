package models;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

import play.data.validation.Constraints;
import play.db.ebean.Model;

import com.google.common.base.Objects;

/**
 * Essa classe representa uma disciplina.
 */
@Entity
public class Disciplina extends Model implements Comparable<Disciplina> {

	private static final long serialVersionUID = 1L;

	@Id
	Long id;

	@Constraints.Required
	@Column(unique = true, nullable = false)
	private String nome;

	private int creditos;

	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinTable(name = "disciplina_requisito", joinColumns = { @JoinColumn(name = "fk_disciplina") }, inverseJoinColumns = { @JoinColumn(name = "fk_requisito") })
	private List<Disciplina> preRequisitos;

	private int dificuldade;

	@Column(name = "periodo_original")
	private int periodo;

	public Disciplina(String nome, int creditos,
			List<Disciplina> preRequisitos, int dificuldade, int periodoSugerido) {
		this.nome = nome;
		this.creditos = creditos;
		this.preRequisitos = preRequisitos;
		this.dificuldade = dificuldade;
		this.periodo = periodoSugerido;
	}

	/**
	 * Retorna verdadeiro caso a disciplina seja pre-requisito.
	 */
	public boolean isPreRequisito(Disciplina d) {
		return this.getPreRequisitos().contains(d);
	}

	/**
	 * Adiciona uma lista de disciplinas em seus pre-requisitos.
	 */
	public void addPreRequisito(Disciplina... d) {
		Disciplina[] lista = d;
		for (Disciplina disciplina : lista) {
			getPreRequisitos().add(disciplina);
		}
	}

	/**
	 * Muda o valor dos créditos
	 * 
	 * @param creditos
	 *            Novo valor para os créditos da disciplina.
	 */
	public void setCreditos(int creditos) {
		this.creditos = creditos;
	}

	/**
	 * Retorna o valor dos créditos da disciplina.
	 * 
	 * @return um inteiro sendo o valor dos créditos da disciplina.
	 */
	public int getCreditos() {
		return this.creditos;
	}

	/**
	 * Retorna o nome da disciplina.
	 * 
	 * @return um string sendo o nome da disciplina.
	 */
	public String getNome() {
		return this.nome;
	}

	/**
	 * Muda o valor da dificuldado da disciplina.
	 * 
	 * @param dificuldade
	 *            Novo valor para a dificuldade da disciplina.
	 */
	public void setDificuldade(int dificuldade) {
		this.dificuldade = dificuldade;
	}

	/**
	 * Retorna o valor da dificuldade da disciplina.
	 * 
	 * @return um inteiro sendo o valor da dificuldade da disciplina.
	 */
	public int getDificuldade() {
		return dificuldade;
	}

	/**
	 * Retorna uma lista de disciplinas sendo os pre-requisitos.
	 * 
	 * @return uma lista de disciplinas sendo os pre-requisitos.
	 */
	public List<Disciplina> getPreRequisitos() {
		return preRequisitos;
	}

	/**
	 * Muda o nome da disciplina.
	 * 
	 * @param nome
	 *            Novo nome da disciplina
	 */
	public void setNome(String nome) {
		this.nome = nome;
	}

	/**
	 * Muda a lista de disciplinas que são os pre-requisitos.
	 * 
	 * @param preRequisitos
	 *            Nova lista de disciplinas que são os pre-requisitos.
	 */
	public void setPreRequisitos(List<Disciplina> preRequisitos) {
		this.preRequisitos = preRequisitos;
	}

	/**
	 * Retorna o período sugerido da disciplina.
	 * 
	 * @return um inteiro sendo o período sugerido da disciplina.
	 */
	public int getPeriodo() {
		return periodo;
	}

	/**
	 * Muda o período sugerido da disciplina.
	 * 
	 * @param periodo
	 *            Novo período sugerido da disciplina.
	 */
	public void setPeriodo(int periodo) {
		this.periodo = periodo;
	}

	public static Finder<Long, Disciplina> find = new Finder<Long, Disciplina>(
			Long.class, Disciplina.class);

	public static void create(Disciplina c) {
		c.save();
	}

	public static void delete(Long id) {
		find.ref(id).delete();
	}

	public static void atualizar(Long id) {
		Disciplina p = find.ref(id);
		p.update();
	}

	@Override
	public int compareTo(Disciplina outra) {
		return getNome().toLowerCase().compareTo(outra.getNome().toLowerCase());
	}

	@Override
	public int hashCode() {
		return Objects.hashCode(getNome(), creditos);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Disciplina other = (Disciplina) obj;
		return Objects.equal(this.getCreditos(), other.getCreditos())
				&& Objects.equal(this.getNome(), other.getNome());
	}

	@Override
	public String toString() {
		return "Disciplina [id=" + id + ", nome=" + nome + ", periodo="
				+ periodo + "]";
	}
}