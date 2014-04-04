package models;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

import models.exceptions.LimiteDePeriodosException;
import models.exceptions.LimiteUltrapassadoException;
import play.db.ebean.Model;

/**
 * Essa classe representa o Plano de Curso.
 */
@Entity
public class PlanoDeCurso extends Model {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	Long id;

	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinTable(name = "plano_periodo", joinColumns = { @JoinColumn(name = "fk_plano") }, inverseJoinColumns = { @JoinColumn(name = "fk_periodo") })
	private List<Periodo> periodos;
	private int periodoAtual;
	private Map<String, Disciplina> mapaDeDisciplinas;

	public static final int MAXIMO_DE_PERIODOS = 14;
	public static final int MAXIMO_CREDITOS = 28;
	public static final int MINIMO_CREDITOS = 12;
	public static final int MINIMO_CREDITOS_CONCLUIR = 208;

	public PlanoDeCurso() {
		this.periodos = new ArrayList<Periodo>();
		for (int i = 1; i <= 8; i++) {
			periodos.add(new Periodo(i, MAXIMO_CREDITOS));
		}
		this.periodos.get(this.periodos.size() - 1).setEValido(
				new ValidadorMin());
		this.mapaDeDisciplinas = new HashMap<String, Disciplina>();
		distribuiDisciplinas();
		this.periodoAtual = 1;
	}

	public Long getId() {
		return id;
	}

	public static Finder<Long, PlanoDeCurso> find = new Finder<Long, PlanoDeCurso>(
			Long.class, PlanoDeCurso.class);

	public static void create(PlanoDeCurso p) {
		p.save();
	}

	public static void delete(Long id) {
		find.ref(id).delete();
	}

	public static void atualizar(Long id) {
		PlanoDeCurso p = find.ref(id);
		p.update();
	}

	public void atualizaValidadores() {
		for (Periodo p : this.periodos) {
			if (p.getNumero() == this.periodos.size()) {
				p.setEValido(new ValidadorMin());
			} else {
				p.setEValido(new ValidadorMax(MAXIMO_CREDITOS));
			}
		}
	}

	/**
	 * Calcula o total de créditos já cursados.
	 * 
	 * @return um inteiro sendo o total de créditos já cursados.
	 */
	public int getCreditosCursados() {
		int result = 0;
		for (int i = 1; i < periodoAtual; i++) {
			result += getPeriodo(i).getCreditos();
		}
		return result;
	}

	/**
	 * Calcula o total de créditos planejados.
	 * 
	 * @return um inteiro sendo o total de créditos planejados.
	 */
	public int getTotalDeCreditos() {
		int result = 0;
		for (Periodo p : periodos) {
			result += p.getCreditos();
		}
		return result;
	}

	/**
	 * Retorna o número de créditos mínimo para concluir o curso de ciência da
	 * computação.
	 * 
	 * @return um inteiro sendo o número de créditos mínimo para concluir o
	 *         curso de ciência da computação.
	 */
	public int getMinimoCreditosConcluir() {
		return MINIMO_CREDITOS_CONCLUIR;
	}

	/**
	 * Retorna verdadeiro se em todos os períodos planejados satisfazem a
	 * quantidade mínima de créditos.
	 * 
	 * @return true se em todos os períodos planejados satisfazem a quantidade
	 *         mínima de créditos.
	 */
	public boolean minimoCreditosSatisfeitos() {
		boolean result = true;
		for (int i = periodoAtual; i <= periodos.size(); i++) {
			if (getPeriodo(i).getCreditos() < MINIMO_CREDITOS) {
				result = false;
			}
		}
		return result;
	}

	public int getPeriodoAtual() {
		return this.periodoAtual;
	}

	public void setPeriodoAtual(int novoPeriodoAtual) {
		for (int i = periodoAtual; i < novoPeriodoAtual; i++) {
			getPeriodo(i).setEValido(new ValidadorMax(MAXIMO_CREDITOS));
		}
		this.periodoAtual = novoPeriodoAtual;
	}

	/**
	 * Distribui as disciplinas em seus respectivos períodos.
	 */
	private void distribuiDisciplinas() {
		for (Disciplina d : mapaDeDisciplinas.values()) {
			if (d.getPeriodo() != 0) {
				Periodo p = getPeriodo(d.getPeriodo());
				try {
					p.adicionarDisciplina(d);
				} catch (LimiteUltrapassadoException e) {
					e.printStackTrace();
				}
			}
		}
		atualizaValidadores();
	}

	/**
	 * Distribui disciplinas entre os periodos quando o plano é iniciado pela
	 * primeira vez.
	 */
	public void distribuiCaderas(List<Disciplina> cadeiras) {
		atualizaMapaCadeira(cadeiras);
		distribuiDisciplinas();
		atualizaValidadores();
	}

	/**
	 * Atualiza o mapa de disciplinas das disciplinas com base em uma lista de
	 * todas as cadeiras existentes.
	 */
	public void atualizaMapaCadeira(List<Disciplina> disciplinas) {
		Map<String, Disciplina> mapa = new HashMap<String, Disciplina>();
		for (Disciplina d : disciplinas) {
			mapa.put(d.getNome(), d);
		}
		mapaDeDisciplinas = mapa;
	}

	/**
	 * Adiciona um periodo à lista de períodos, de acordo com o tamanho da
	 * lista.
	 */
	public void adicionaPeriodo() throws LimiteDePeriodosException {
		if (periodos.size() == MAXIMO_DE_PERIODOS) {
			throw new LimiteDePeriodosException();
		}
		this.periodos.get(this.periodos.size() - 1).setEValido(
				new ValidadorMax(MAXIMO_CREDITOS));
		;
		Periodo newPeriodo = new Periodo(this.periodos.size() + 1,
				MAXIMO_CREDITOS);
		newPeriodo.setEValido(new ValidadorMin());
		this.periodos.add(newPeriodo);
	}

	public void adicionaPeriodo(int num_periodo)
			throws LimiteDePeriodosException {
		if (periodos.size() == MAXIMO_DE_PERIODOS) {
			throw new LimiteDePeriodosException();
		}
		if (num_periodo == periodos.size()) {
			this.periodos.get(this.periodos.size() - 1).setEValido(
					new ValidadorMax(MAXIMO_CREDITOS));
			;
			Periodo newPeriodo = new Periodo(num_periodo, MAXIMO_CREDITOS);
			newPeriodo.setEValido(new ValidadorMin());
			this.periodos.add(newPeriodo);
		} else {
			this.periodos.add(new Periodo(num_periodo, MAXIMO_CREDITOS));
		}
	}

	public Map<String, Disciplina> getMapaDisciplina() {
		return mapaDeDisciplinas;
	}

	/**
	 * Retorna o período passado como argumento.
	 * 
	 * @param numPeriodo
	 *            número relativo ao periodo 1,2,3...
	 */
	public Periodo getPeriodo(int numPeriodo) {
		return this.periodos.get(numPeriodo - 1);
	}

	public List<Periodo> getPeriodos() {
		return this.periodos;
	}

	/**
	 * Retorna o Map de disciplinas já alocadas no plano de curso.
	 */
	public List<Disciplina> getDisciplinasAlocadas() {
		List<Disciplina> alocadas = new ArrayList<Disciplina>();
		for (Periodo periodo : periodos) {
			alocadas.addAll(periodo.getDisciplinas());
		}
		return alocadas;
	}

	/**
	 * Retorna o Map de disciplinas ainda não alocadas no plano de curso.
	 */
	public Map<String, Disciplina> getMapDisciplinasDisponiveis() {
		Map<String, Disciplina> disponiveis = new HashMap<String, Disciplina>();
		List<Disciplina> alocadas = getDisciplinasAlocadas();
		for (Disciplina c : mapaDeDisciplinas.values()) {
			if (!alocadas.contains(c)) {
				disponiveis.put(c.getNome(), c);
			}
		}
		return disponiveis;
	}

	/**
	 * Retorna a lista de cadeira disponíveis para alocação ordenadas em ordem
	 * alfabética.
	 */
	public List<Disciplina> getDisciplinaDispniveisOrdenadas() {
		List<Disciplina> cadeirasOrdenadas = new ArrayList<Disciplina>();
		cadeirasOrdenadas.addAll(getMapDisciplinasDisponiveis().values());
		Collections.sort(cadeirasOrdenadas);
		return cadeirasOrdenadas;
	}

	/**
	 * Adiciona uma disciplina ao periodo
	 * 
	 * @throws LimiteUltrapassadoException
	 */
	public void adicionaDisciplina(String disciplinaNome, int periodo)
			throws LimiteUltrapassadoException {
		Disciplina disciplina = mapaDeDisciplinas.get(disciplinaNome);
		if (!getPeriodo(periodo).getDisciplinas().contains(disciplina)) {
			getPeriodo(periodo).adicionarDisciplina(disciplina);
		}
		for (Periodo p : periodos) {
			if (p.getNumero() != periodo
					&& p.getDisciplinas().contains(disciplina)) {
				p.removerDisciplina(disciplina);
			}
		}
	}

	/**
	 * Varifica se a cadeira tem pre-requisitos alocados de maneira errada.
	 * 
	 * @param cadeira
	 *            a ser verificada
	 */
	public boolean verificaPrerequisito(String disciplina, int indicePeriodo) {
		boolean result;
		int contPreRequisitos = 0;
		Disciplina d = mapaDeDisciplinas.get(disciplina);
		if (d == null)
			return true;
		for (Disciplina disc : d.getPreRequisitos()) {
			for (int i = 1; i < indicePeriodo; i++) {
				if (getPeriodo(i).getDisciplinas().contains(disc)) {
					contPreRequisitos++;
				}
			}
		}
		if (contPreRequisitos == d.getPreRequisitos().size()) {
			result = true;
		} else {
			result = false;
		}
		return result;
	}

	/**
	 * lista os nomes dos pre-requisitos de uma disciplina insatisfeitos.
	 * 
	 * @param nomeDisciplina
	 *            O nome da disciplina com pre-requisitos insatisfeitos.
	 * @return a lista com os nomes dos pre-requisitos insatisfeitos.
	 */
	public List<String> preRequisitosNaoSatisfeitos(String nomeDisciplina) {
		List<String> requisitos = new ArrayList<String>();
		Disciplina disciplina = pesquisaDisciplina(nomeDisciplina);
		if (disciplina != null) {
			for (int i = periodoDisciplinaAlocada(nomeDisciplina); i < periodos
					.size(); i++) {
				for (Disciplina requisito : disciplina.getPreRequisitos()) {
					if (periodos.get(i).getDisciplinas().contains(requisito)) {
						requisitos.add(requisito.getNome());
					}
					if (!getDisciplinasAlocadas().contains(requisito)
							&& !requisitos.contains(requisito.getNome())) {
						requisitos.add(requisito.getNome());
					}
				}
			}
		}
		return requisitos;
	}

	public int periodoDisciplinaAlocada(String nome) {
		for (int i = 0; i < periodos.size(); i++) {
			if (periodos.get(i).getDisciplina(nome) != null) {
				return i;
			}
		}
		return -1;
	}

	public Disciplina pesquisaDisciplina(String nome) {
		Disciplina disciplina = null;
		for (Periodo periodo : periodos) {
			if ((disciplina = periodo.getDisciplina(nome)) != null) {
				return disciplina;
			}
		}
		return null;
	}

	/**
	 * Retorna true caso a disciplina seja pre-requisito de alguma outra nos
	 * periodos.
	 */
	public boolean isPreRequisito(String disc) {
		Disciplina disciplina = mapaDeDisciplinas.get(disc);
		for (Periodo periodo : periodos) {
			for (Disciplina disciplinaDoPeriodo : periodo.getDisciplinas()) {
				if (disciplinaDoPeriodo.getPreRequisitos().contains(disciplina)) {
					return true;
				}
			}
		}
		return false;
	}

	/**
	 * Remove uma disciplina do plano.
	 * 
	 * @param disciplina
	 *            Disciplina a ser removida do plano.
	 * @return
	 */
	public void removeDisciplina(String disciplina) {
		Disciplina removida = mapaDeDisciplinas.get(disciplina);
		Periodo p;
		if ((p = getPeriodoDeDisciplina(removida)) != null) {
			p.removerDisciplina(removida);
			update();
			for (Disciplina dependente : disciplinasDependentes(removida)) {
				removeDisciplina(dependente.getNome());
			}
		}
	}

	/**
	 * Obtem o periodo ao qual uma disciplina esta alocada.
	 * @param disciplina A disciplina
	 * @return o periodo em que a disciplina esta
	 */
	private Periodo getPeriodoDeDisciplina(Disciplina disciplina) {
		for (Periodo periodo : periodos) {
			if (periodo.getDisciplinas().contains(disciplina)) {
				return periodo;
			}
		}
		return null;
	}

	/**
	 * Obtem uma lista de disciplinas que dependem de uma dada disciplina.
	 * @param disciplina A disciplina da qual se quer obter as dependentes
	 * @return a lista das dependentes da disciplina recebida.
	 */
	public List<Disciplina> disciplinasDependentes(Disciplina disciplina) {
		List<Disciplina> dependentes = new ArrayList<Disciplina>();
		for (Disciplina alocada : getDisciplinasAlocadas()) {
			if (alocada.getPreRequisitos().contains(disciplina)) {
				dependentes.add(alocada);
			}
		}
		return dependentes;
	}
}