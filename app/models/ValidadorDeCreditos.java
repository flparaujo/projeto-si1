package models;

/**
 * Essa interface representa o compartamente de validar adicionar disciplina a períodos.
 */
public interface ValidadorDeCreditos {
	
	/**
	 * Valida adicionar disciplina ao período se o total de créditos do período mais o da disciplina não ultrapassa o máximo de créditos
	 * @param creditosPeriodo
	 * 		Total de créditos do período.
	 * @param creditoDisciplina
	 * 		Créditos da disciplina.
	 * @return verdadeiro se o total de créditos do período mais o da disciplina não ultrapassa o máximo de créditos
	 */
	public boolean eValido (int creditosPeriodo, int creditoDisciplina);
}
