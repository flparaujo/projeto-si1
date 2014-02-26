package models;

import java.util.*;

/**
 * Classe que representa a grade curricular do curso.
 * 
 * 
 * @version 1.2
 */
public class GradeCurricular {
	
	private LeitorDeDisciplinas leitorDeDisciplinas;
	private List<Disciplina> disciplinas;
	
	/**
	 * Construtor da grade curricular.
	 */
	public GradeCurricular() {
		leitorDeDisciplinas = LeitorDeDisciplinas.getInstance();
		disciplinas = leitorDeDisciplinas.getDisciplinas();
	}
		
	/**
	 * Obtem a lista contendo todas as disciplinas desta grade curricular.
	 * @return uma lista com as disciplinas da grade.
	 */
	public List<Disciplina> getDisciplinas() {
		Collections.sort(disciplinas);
		return disciplinas;
	}
	
	/**
	 * Obtem uma disciplina qualquer da grade curricular.
	 * @param nome O nome da disciplina a ser obtida.
	 * @return A disciplina, se estiver na grade. Caso contrário, retorna null.
	 */
	public Disciplina getDisciplina(String nome) {
		for(Disciplina disciplina: disciplinas) {
			if(disciplina.getNome().equals(nome))
				return disciplina;
		}
		return null;
	}
	
	/**
	 * Obtem uma disciplina qualquer da grade curricular pelo indice.
	 * @param indice O indice da disciplina a ser obtida.
	 * @return A disciplina, se estiver na grade.
	 */
	public Disciplina getDisciplina(int indice) {
		return disciplinas.get(indice);
	}
	
	/**
	 * Obtém uma disciplina removendo-a da grade curricular.
	 * @param nome O nome da disciplina.
	 * @return a disciplina.
	 */
	public Disciplina retiraDisciplina(String nome) {
		for(Disciplina disciplina : disciplinas) {
			if(disciplina.getNome().equals(nome)) {
				return disciplinas.remove(disciplinas.indexOf(disciplina));
			}
		}
		return null;
	}
	
	/**
	 * Obtém uma disciplina removendo-a da grade curricular.
	 * @param disciplina A disciplina a ser removida.
	 * @return a disciplina.
	 */
	public Disciplina retiraDisciplina(Disciplina disciplina) {
		return disciplinas.remove(disciplinas.indexOf(disciplina));
	}
	
	/**
	 * Adiciona uma disciplina a Grade.
	 * @param nome O nome da disciplina a ser adicionada.
	 */
	public void adicionaDisciplina(String nome) {
		for (Disciplina disciplina: leitorDeDisciplinas.getDisciplinas()) {
			if (disciplina.getNome().equals(nome)) {
				disciplinas.add(disciplina);
			}
		}
	}
	
}
