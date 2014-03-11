package models;

public class ValidadorMin implements ValidadorDeCreditos{

	public ValidadorMin() {
	}

	@Override
	public boolean eValido(int creditosPeriodo, int creditoDisciplina) {
		return true;
	}

}
