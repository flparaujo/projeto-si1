package models;

/**
 * Classe que representa um periodo sem limite maximo de creditos.
 * @author Franklin Bastos
 *
 */
public class PeriodoSemMaximoCreditos extends Periodo {

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
