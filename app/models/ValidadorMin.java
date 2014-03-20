package models;

/**
 * Essa classe representa o validador de créditos.
 */
public class ValidadorMin implements ValidadorDeCreditos{

	public ValidadorMin() {
	}

	/**
	 * Retorna sempre verdadeiro, pois apenas indica que não existe um máximo de créditos para o período.
	 */
	@Override
	public boolean eValido(int creditosPeriodo, int creditoDisciplina) {
		return true;
	}

}
