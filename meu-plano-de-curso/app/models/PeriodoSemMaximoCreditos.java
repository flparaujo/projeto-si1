package models;

/**
 * Classe que representa um periodo sem limite maximo de creditos.
 *
 */
public class PeriodoSemMaximoCreditos extends Periodo {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Adiciona uma disciplina.
	 * @param disciplina A disciplina a ser adicionada.
	 */
	@Override
	public void adicionaDisciplina(Disciplina disciplina) {
		if(! disciplinasAlocadas().contains(disciplina)) 
			 disciplinasAlocadas().add(disciplina);
	}
	

}
