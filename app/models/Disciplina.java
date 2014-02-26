package models;

import java.util.List;

/**
 * Classe que representa uma disciplina.
 * 
 * 
 * @version 1.0
 */
public class Disciplina implements Comparable<Disciplina> {
	
	private int dificuldade;
	private String nome;
	private int numeroDeCreditos;
	private int periodoSugerido;

	// INFORMATION EXPERT: Disciplinas tem a responsabilidade de saber seus pre-requisitos.	
	private List<Disciplina> preRequisitos;

	/**
	 * Construtor de uma disciplina.
	 * @param nome O nome da disciplina.
	 * @param numeroDeCreditos O numero de creditos da disciplina.
	 * @param preRequisitos A lista de disciplinas pre-requisitos da disciplina.
	 * @param dificuldade A dificuldade da disciplina.
	 * @param periodo O periodo sugerido para alocar esta disciplina.
	 */
	public Disciplina(String nome, int numeroDeCreditos, List<Disciplina> preRequisitos, 
			int dificuldade, int periodo) {
		this.nome = nome;
		this.numeroDeCreditos = numeroDeCreditos;
		this.preRequisitos =  preRequisitos;
		this.dificuldade = dificuldade;
		this.periodoSugerido = periodo;
	}
	

	/**
	 * Recupera a dificuldade da disciplina.
	 * @return a dificuldade da disciplina.
	 */
	public int getDificuldade() {
		return this.dificuldade;
	}
	
	/**
	 * Recupera o nome da disciplina.
	 * @return o nome da disciplina.
	 */
	public String getNome() {
		return this.nome;
	}

	/**
	 * Recupera o numero de creditos da disciplina.
	 * @return o numero de creditos da disciplina.
	 */
	public int getNumeroDeCreditos() {
		return this.numeroDeCreditos;
	}
	
	/**
	 * Recupera a lista de disciplinas pre-requisito da disciplina.
	 * @return a lista contendo as disciplinas pre-requisito.
	 */
	public List<Disciplina> getPreRequisitos() {
		return this.preRequisitos;
	}
	
	/**
	 * Obtem o id do periodo sugerido para alocar a disciplina.
	 * @return o id do periodo.
	 */
	public int getPeriodoSugerido() {
		return periodoSugerido;
	}
	
	/**
	 * Verifica a igualdade entre esta disciplina e outra.
	 * Duas disciplinas sao iguais se possuem o mesmo nome e numero de creditos.
	 * @return true se as disciplinas sao identicas, false caso contrario.
	 */
	@Override
	public boolean equals(Object obj) {
		if(!(obj instanceof Disciplina)) {
			return false;
		}
		return ((Disciplina) obj).getNome().equals(nome) 
				&& ((Disciplina) obj).getNumeroDeCreditos() == numeroDeCreditos;
	}
	
	/**
	 * Formata a disciplina como string.
	 * @return a representacao da disciplina como string.
	 */
	@Override
	public String toString() {
		return this.getNome();
	}
    
	/**
	 * Compara esta disciplina com outra pelo nome.
	 * @param outraDisciplina A disciplina a ser comparada com esta.
	 */
	@Override
	public int compareTo(Disciplina outraDisciplina) {
		return this.getNome().compareTo(outraDisciplina.getNome());
	}

	
	/**
	 * Recupera uma string com a lista de disciplinas pre-requisito da disciplina.
	 * @return a lista contendo as disciplinas pre-requisito.
	 */
	public String getPreRequisitosToString() {
		String result = "";
		String[] aux = this.preRequisitos.toString().split("");
		for (int i = 0; i < aux.length; i++) {
			if (!aux[i].equals("]") && !aux[i].equals("[")) {
				result += aux[i];
			}
		}
		return result;
	}
}