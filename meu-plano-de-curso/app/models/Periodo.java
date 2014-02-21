package models;

import java.util.ArrayList;
import java.util.List;

import exceptions.LimiteDeCreditosExcedidoException;

/**
 * Classe que representa um periodo de curso.
 * 
 * 
 * @version 1.1
 */
public class Periodo {

	private List<Disciplina> disciplinas;
	
	/**
	 * Constante que representa o numero minimo de creditos que um periodo deve conter.
	 */
	public static final int MINIMO_DE_CREDITOS = 14;
	
	/**
	 * Constante que representa o numermo maximo de creditos que um periodo pode conter.
	 */
	public static final int MAXIMO_DE_CREDITOS = 28;
	
	/**
	 * Construtor de Periodo.
	 */
	public Periodo() {
		disciplinas = new ArrayList<Disciplina>();
	}
	
	/**
	 * Recupera a lista das disciplinas alocadas para o periodo.
	 * @return a lista de disciplinas do periodo.
	 */
	public List<Disciplina> disciplinasAlocadas() {
		return this.disciplinas;
	}

	/**
	 * Adiciona uma disciplina a este periodo.
	 * @param nome O nome da disciplina.
	 * @param numeroDeCreditos O numero de creditos da disciplina.
	 * @param preRequisitos A lista contendo as disciplinas pre-requisito da disciplina a ser adicionada.
	 * @return true se a disciplina foi adicionada, false caso contrario.
	 * @throws LimiteDeCreditosExcedidoException quando a tentativa de adicionar a disciplina ultrapassa 
	 * o limite maximo de creditos.
	 */
	public void adicionaDisciplina(Disciplina disciplina) 
			throws LimiteDeCreditosExcedidoException {
		if (!disciplinas.contains(disciplina)) {
			if ((getNumeroDeCreditosDoPeriodo() + disciplina.getNumeroDeCreditos()) > MAXIMO_DE_CREDITOS) {
				throw new LimiteDeCreditosExcedidoException();
			}
			disciplinas.add(disciplina);
		}
	}

	/**
	 * Obtem o numero de creditos do periodo.
	 * @return o numero de creditos do periodo.
	 */
	public int getNumeroDeCreditosDoPeriodo() {
	    //INFORMATION EXPERT: Casse Periodo contem disciplinas, que sao o atributo necessario para o calculo do total de creditos
		int total = 0;
		for(Disciplina disciplina : disciplinas)
			total += disciplina.getNumeroDeCreditos();
		return total;
	}

	/**
	 * Metodo que verifica se o periodo tem menos creditos do que o limite minimo.
	 * @return true se esta abaixo do limite minimo de creditos, false caso contrario.
	 */
	public boolean abaixoDoLimiteMinimoDeCreditos() {
		return getNumeroDeCreditosDoPeriodo() < Periodo.MINIMO_DE_CREDITOS;
	}

	/**
	 * Remove uma disciplina deste periodo, retornando-a.
	 * @param nomeDaDisciplina O nome da disciplina a ser removida.
	 * @return a disciplina removida.
	 */
	public Disciplina removeDisciplina(String nomeDaDisciplina) {
		for(Disciplina disciplina : disciplinas) {
			if(disciplina.getNome().equals(nomeDaDisciplina))
				return disciplinas.remove(disciplinas.indexOf(disciplina));
		}
		return null;
	}

	/**
	 * Obtem uma disciplina deste periodo, sem remove-la.
	 * @param nomeDaDisciplina O nome da disciplina que se quer obter.
	 * @return a disciplina, se ela estiver no periodo, null caso contrario.
	 */
	public Disciplina getDisciplina(String nomeDaDisciplina) {
		for(Disciplina disciplina : disciplinas) {
			if(disciplina.getNome().equals(nomeDaDisciplina))
				return disciplinas.get(disciplinas.indexOf(disciplina));
		}
		return null;
	}
	
	/**
	 * Obtem a dificuldade do periodo.
	 * @return a dificuldade do periodo.
	 */
	public int getDificuldadeDoPeriodo() {
		//INFORMATION EXPERT: Classe Periodo contem disciplinas, que sao o atributo necessario para o calculo da dificuldade total
		int dificuldade = 0;
		for(Disciplina disciplina : disciplinas) {
			dificuldade += disciplina.getDificuldade();
		}
		return dificuldade;
	}
}
