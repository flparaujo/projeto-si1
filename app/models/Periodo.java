package models;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Transient;

import models.exceptions.LimiteUltrapassadoException;
import play.db.ebean.Model;

/**
 * Essa classe representa um período
 */
@Entity
public class Periodo extends Model {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	Long id;

	@Transient
	private ValidadorDeCreditos validador;

	private int numero;

	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinTable(name = "periodo_disciplina", joinColumns = { @JoinColumn(name = "fk_periodo") }, inverseJoinColumns = { @JoinColumn(name = "fk_disciplina") })
	private List<Disciplina> disciplinas;

	/**
	 * Constante que representa o numero minimo de creditos por periodo
	 */
	public static final int MINIMO_CREDITOS = 12;

	/**
	 * Constante que representa o maximo de creditos por periodo
	 */
	public static final int MAXIMO_CREDITOS = 28;

	/**
	 * Constroi um periodo com id e numero de creditos pre-definidos. O numero
	 * de creditos inicial eh maximo.
	 */
	public Periodo() {
		this(1, MAXIMO_CREDITOS);
	}

	/**
	 * Constroi um periodo.
	 * 
	 * @param numeroDoPeriodo
	 *            O numero do periodo (id)
	 * @param maximoCreditos
	 *            o numero maximo de creditos do periodo.
	 */
	public Periodo(int numeroDoPeriodo, int maximoCreditos) {
		this.numero = numeroDoPeriodo;
		disciplinas = new ArrayList<Disciplina>();
		validador = new ValidadorMax(maximoCreditos);
	}

	public Long getId() {
		return id;
	}

	public static Finder<Long, Periodo> find = new Finder<Long, Periodo>(
			Long.class, Periodo.class);

	public static void create(Periodo p) {
		p.save();
	}

	public static void delete(Long id) {
		find.ref(id).delete();
	}

	public static void atualizar(Long id) {
		Periodo p = find.ref(id);
		p.update();
	}

	/**
	 * TODO By Franklin
	 * 
	 * @param validador
	 */
	public void setEValido(ValidadorDeCreditos validador) {
		this.validador = validador;
	}

	/**
	 * Adiciona uma disciplina ao período.
	 * 
	 * @param disciplina
	 *            Disciplina a ser adicionada ao período.
	 * @throws LimiteUltrapassadoException
	 *             Se o período estiver atingido o máximo de créditos.
	 */
	public void adicionarDisciplina(Disciplina disciplina)
			throws LimiteUltrapassadoException {
		if (!validador.eValido(getCreditos(), disciplina.getCreditos())) {
			throw new LimiteUltrapassadoException();
		}
		disciplinas.add(disciplina);
	}

	/**
	 * Remove uma disciplina do período.
	 * 
	 * @param disciplina
	 *            Disciplina a ser removida do período.
	 */
	public void removerDisciplina(Disciplina disciplina) {
		disciplinas.remove(disciplina);
	}

	/**
	 * Obtem a dificuldade total do periodo
	 * 
	 * @return a dificuldade total.
	 */
	public int getDificuldadeTotal() {
		int total = 0;
		for (Disciplina d : getDisciplinas()) {
			total += d.getDificuldade();
		}
		return total;
	}

	/**
	 * Calcula o total de Créditos do Periodo.
	 */
	public int getCreditos() {
		int soma = 0;
		for (Disciplina disciplina : getDisciplinas()) {
			soma += disciplina.getCreditos();
		}
		return soma;
	}

	/**
	 * Obtem a lista das disciplinas deste periodo
	 * 
	 * @return uma lista de objetos Disciplina.
	 */
	public List<Disciplina> getDisciplinas() {
		Collections.sort(disciplinas);
		return disciplinas;
	}

	/**
	 * modifica a lista de disciplinas atual
	 * 
	 * @param disciplinas
	 *            A nova lista de disciplinas.
	 */
	public void setDisciplinas(List<Disciplina> disciplinas) {
		this.disciplinas = disciplinas;
	}

	/**
	 * Obtem o id (numero) do periodo
	 * 
	 * @return o numero do periodo.
	 */
	public int getNumero() {
		return numero;
	}

	/**
	 * seta o numero do periodo.
	 * 
	 * @param numero
	 *            O novo numero.
	 */
	public void setNumero(int numero) {
		this.numero = numero;
	}

	/**
	 * Obtem uma disciplina de nome dado.
	 * 
	 * @param nome
	 *            O nome da discipĺina a ser obtida
	 * @return a disciplina, se estiver no periodo, ou null.
	 */
	public Disciplina getDisciplina(String nome) {
		for (Disciplina disciplina : disciplinas) {
			if (disciplina.getNome().equals(nome)) {
				return disciplina;
			}
		}
		return null;
	}

	/**
	 * Formata o periodo como string.
	 */
	@Override
	public String toString() {
		return "Periodo [id=" + id + ", numero=" + numero + ", disciplinas="
				+ disciplinas + "]";
	}
}