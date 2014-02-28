package models;

import java.util.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

import play.db.ebean.Model;
import exceptions.*;

/**
 * Classe que representa o planejamento de curso.
 *
 */
@Entity
public class PlanejamentoDeCurso extends Model {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue
    public Long id;
	
	public static Finder<Long,PlanejamentoDeCurso> find = new Finder<Long,PlanejamentoDeCurso>(Long.class, PlanejamentoDeCurso.class);
	
	private List<Periodo> periodos;
	private GradeCurricular grade;
	
	@OneToMany
	@JoinColumn(name = "disciplina_id")
	private List<Disciplina> handlerDisciplinas;
	
	/**
	 * Constante que representa o numero maximo de periodos do curso.
	 */
	public static final int MAXIMO_DE_PERIODOS = 14;
	
	
	/**
	 * Constante que representa o numero minimo de periodos do curso.
	 */
	public static final int MINIMO_DE_PERIODOS = 8;
	
	/**
	 * Constroi um planejamento de curso, com o primeiro periodo predefinido.
	 */
	public PlanejamentoDeCurso() {
		gerarPeriodos();
		if (!GradeCurricular.find.all().isEmpty()){
			grade = GradeCurricular.find.all().get(0);
		} else {
			grade = new GradeCurricular();
			grade.save();
		}
		handlerDisciplinas = new ArrayList<Disciplina>();
	}
	
	/**
	 * Cria os periodos e aloca as disciplinas
	 */
	private void gerarPeriodos (){
		periodos = new ArrayList <Periodo>();
		List<Disciplina> disciplinas = new ArrayList<Disciplina>();
		if (Disciplina.find.all() != null) {
			disciplinas = Disciplina.find.all();
		}
		for (Disciplina d: disciplinas) {
			if (d.getPeriodoAtual() > 0) {
				try {
					adicionaDisciplinaAoPeriodo(d.getPeriodoAtual() - 1, d.getNome());
				} catch (LimiteDeCreditosExcedidoException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	/**
	 * Obtem a dificuldade total de um periodo alocado.
	 * @param indicePeriodo O indice do periodo na lista.
	 * @return a dificuldade total do periodo.
	 */
	public int dificuldadeDoPeriodo (int indicePeriodo) {
		return getPeriodo(indicePeriodo).getDificuldadeDoPeriodo();
	}
	
	public List<Disciplina> disciplinasAlocadas() {
		List<Disciplina> alocadas = new ArrayList<Disciplina>();
		for(Periodo periodo : periodos) {
			alocadas.addAll(periodo.disciplinasAlocadas());
		}
		return alocadas;
	}
	
	public List<Disciplina> disciplinasDoCurso() {
		return grade.getDisciplinas();
	}
	
	/**
	 * Obtem um periodo da lista de periodos adicionados.
	 * @param i O indice do periodo (0 para o primeiro, 1 para o segundo e assim por diante).
	 * @return o periodo de indice i.
	 */
	public Periodo getPeriodo(int i) {
		return periodos.get(i);
	}
	
	/**
	 * Obtem uma lista das disciplinas de um periodo.
	 * @param indicePeriodo O indice do periodo.
	 * @return a lista das disciplinas do periodo.
	 */
	public List<Disciplina> getDisciplinasDoPeriodo(int indicePeriodo) {
		return getPeriodo(indicePeriodo).disciplinasAlocadas();
	}
	
	
	/**
	 * Obtem o numero de creditos de um periodo.
	 * @param indicePeriodo O indice do periodo.
	 * @return o numero de creditos do periodo.
	 */
	public int numeroDeCreditosDoPeriodo(int indicePeriodo) {
		return getPeriodo(indicePeriodo).getNumeroDeCreditosDoPeriodo(); 
	}
	
	
	/**
	 * Obtem uma lista de todos os periodos criados.
	 * @return a lista de todos os periodos criados.
	 */
	public List<Periodo> getPeriodos() {
		return this.periodos;
	}
	
	//CREATOR: classe SistemaDePlanejamentoDeCurso registra objetos do tipo Periodo
	/**
	 * Adiciona um novo periodo.
	 * @throws LimiteDePeriodosException 
	 */
	public void adicionaPeriodo() throws LimiteDePeriodosException {
		if(periodos.size() == MAXIMO_DE_PERIODOS)
			throw new LimiteDePeriodosException();
		periodos.add(new Periodo());
	}
	
	/**
	 * Adiciona uma disciplina a um periodo.
	 * @param indicePeriodo O indice do periodo.
	 * @param nome O nome da disciplina.
	 * @param numeroDeCreditos O numero de creditos da disciplina.
	 * @throws LimiteDeCreditosExcedidoException
	 */
	public void adicionaDisciplinaAoPeriodo(int indicePeriodo, String nome) 
			throws LimiteDeCreditosExcedidoException {
		if(getDisciplinaDaGrade(nome) != null) {
			getPeriodo(indicePeriodo).adicionaDisciplina(getDisciplinaDaGrade(nome));
			grade.retiraDisciplina(nome);
			grade.update();
		}
	}
	
	/**
	 * Obtem uma disciplina da grade curricular.
	 * @param nome O nome da disciplina.
	 * @return a disciplina, se for encontrada na grade. Se nao, retorna null.
	 */
	public Disciplina getDisciplinaDaGrade(String nome) {
		return grade.getDisciplina(nome);
	}
	
	/**
	 * Verifica se a soma dos creditos de um periodo esta abaixo do limite minimo de creditos.
	 * @param idPeriodo O indice do periodo.
	 * @return true se o periodo esta abaixo do limite minimo de cretidos, false caso contrario.
	 */
	public boolean periodoComCreditosAbaixoDoLimiteMinimo(int idPeriodo) {
		return getPeriodo(idPeriodo).abaixoDoLimiteMinimoDeCreditos();
	}

	/**
	 * Adiciona de volta a grade uma disciplina que foi alocada.
	 * @param nomeDaDisciplina O nome da disciplina.
	 */
	public void devolveDisciplinaParaGrade(String nomeDaDisciplina) {
		int indicePeriodo = indicePeriodoDeDisciplina(nomeDaDisciplina);
		Disciplina aRemover = getPeriodo(indicePeriodo).getDisciplina(nomeDaDisciplina);
		grade.adicionaDisciplina(nomeDaDisciplina);
		handlerDisciplinas.add(aRemover);
		for(int i = indicePeriodo+1; i < periodos.size(); i++) {
			for(Disciplina disciplina : getPeriodo(i).disciplinasAlocadas()) {
				if(disciplina.getPreRequisitos().contains(aRemover))
					devolveDisciplinaParaGrade(disciplina.getNome());
			}
		}
		grade.update();
	}
	
	/**
	 * Obtem o indice do periodo, na lista de periodos, ao qual uma disciplina de nome dado 
	 * foi alocada.
	 * @param nome O nome da disciplina.
	 * @return o indice do periodo em que a disciplina esta. Se a disciplina de nome dado
	 * nao esta alocada em periodo algum, retorna -1.
	 */
	public int indicePeriodoDeDisciplina(String nome) {
		for(int i = 0; i < periodos.size(); i++) {
			if(getPeriodo(i).getDisciplina(nome) != null)
				return i;
		}
		return -1;
	}
	
	/**
	 * Remove disciplinas desalocadas.
	 */
	public void removeDisciplinasDesalocadas() {
		for(Disciplina disciplina : handlerDisciplinas) {
			removeDisciplina(disciplina.getNome());
		}
	}
	
	/**
	 * Obtem as disciplinas alocadas a partir do segundo periodo.
	 * @return as disciplinas alocadas a partir do segundo periodo.
	 */
	public List<Disciplina> getDisciplinasAlocadas() {
		List<Disciplina> alocadas = new ArrayList<Disciplina>();
		for(int i = 1; i < periodos.size(); i++) {
			if(! getPeriodo(i).disciplinasAlocadas().isEmpty()) {
				alocadas.addAll(getPeriodo(i).disciplinasAlocadas());
			}
		}
		return alocadas;
	}
	
	private void removeDisciplina(String nomeDaDisciplina) {
		for(int i = 0; i < periodos.size(); i++)
			for(Disciplina disciplina : getPeriodo(i).disciplinasAlocadas())
				if(disciplina.getNome().equals(nomeDaDisciplina)) {
					getPeriodo(i).removeDisciplina(nomeDaDisciplina);
					return;
				}
		
	}
	
	/**
	 * Move uma disciplina de um periodo para outro.
	 * @param nome O nome da disciplina a ser remanejada.
	 * @param idOutroPeriodo O id do periodo
	 * @throws LimiteDeCreditosExcedidoException 
	 */
	public void moveDisciplina(String nome, int idOutroPeriodo) throws LimiteDeCreditosExcedidoException {
		int idPeriodoEmQueFoiAlocada = indicePeriodoDeDisciplina(nome);
		if(idPeriodoEmQueFoiAlocada >= 0) {
			Disciplina disciplina = pesquisaDisciplinaEmPeriodo(idPeriodoEmQueFoiAlocada, nome);
			getPeriodo(idOutroPeriodo).adicionaDisciplina(disciplina);
			desalocaDisciplinaDePeriodo(idPeriodoEmQueFoiAlocada, nome);
			disciplina.setPeriodoAtual(idOutroPeriodo);
			disciplina.update();
		}
	}
	
	/**
	 * Pesquisa uma disciplina pelo nome.
	 * @param nome O nome da disciplina.
	 * @return a disciplina se estiver alocada em periodo, null caso contrario.
	 */
	public Disciplina pesquisaDisciplina(String nome) {
		for(Disciplina disciplina : getDisciplinasAlocadas()) {
			if(disciplina.getNome().equals(nome)) {
				return disciplina;
			}
		}
		return null;
	}
	
	/**
	 * Pesquisa uma disciplina em determinado periodo.
	 * @param nome O nome da disciplina.
	 * @param idPeriodo O indice do periodo.
	 * @return a disciplina, se ela estiver alocada no periodo, ou null.
	 */
	public Disciplina pesquisaDisciplinaEmPeriodo(int idPeriodo, String nome) {
		for(Disciplina disciplina : getDisciplinasDoPeriodo(idPeriodo)) {
			if(disciplina.getNome().equals(nome)) {
				return disciplina;
			}
		}
		return null;
	}
	
	public boolean preRequisitosSatisfeitos(String nomeDaDisciplina) {
		int contPreRequisitos = 0;
		int periodoAlocada = indicePeriodoDeDisciplina(nomeDaDisciplina);
		if(periodoAlocada < 0)
			return false;
		Disciplina disciplina = getPeriodo(periodoAlocada).getDisciplina(nomeDaDisciplina);
		if (!disciplina.getPreRequisitos().isEmpty()) {
			for(int i = 0; i < periodoAlocada; i++) {
				for(Disciplina disciplinaPreRequisito : disciplina.getPreRequisitos()) {
					if(getPeriodo(i).disciplinasAlocadas().contains(disciplinaPreRequisito)) {
						contPreRequisitos ++;
					}
				}
			}
		} else {
			return true;
		}
		return contPreRequisitos == disciplina.getPreRequisitos().size();
	}
	
	private Disciplina desalocaDisciplinaDePeriodo(int idPeriodo, String nome) {
		Disciplina disciplina = pesquisaDisciplinaEmPeriodo(idPeriodo, nome);
		getDisciplinasDoPeriodo(idPeriodo).remove(disciplina);
		return disciplina;
	}
	
	public static void create(PlanejamentoDeCurso c) {
		c.save();
	}

	public static void delete(Long id) {
		find.ref(id).delete();
	}

	public static void atualizar(Long id) {
		PlanejamentoDeCurso p = find.ref(id);
		p.update();
	}
}
