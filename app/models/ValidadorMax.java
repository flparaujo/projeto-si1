package models;

/**
 * Essa classe representa o validador de créditos.
 */
public class ValidadorMax implements ValidadorDeCreditos{
	
	private int maximoCreditos;
	
	public ValidadorMax(int maximoCreditos) {
		this.maximoCreditos = maximoCreditos;
	}

	/** Valida adicionar disciplina ao período se o total de créditos do período mais o da disciplina não ultrapassa o máximo de créditos
	 * @param creditosPeriodo
	 * 		Total de créditos do período.
	 * @param creditoDisciplina
	 * 		Créditos da disciplina.
	 * @return verdadeiro se o total de créditos do período mais o da disciplina não ultrapassa o máximo de créditos
	 */
	@Override
	public boolean eValido(int creditosPeriodo, int creditoDisciplina) {
		boolean result = true;
		if (creditosPeriodo + creditoDisciplina > maximoCreditos) {
			result = false;
		}
		return result;
	}
}
