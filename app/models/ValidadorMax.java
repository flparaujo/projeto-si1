package models;

public class ValidadorMax implements ValidadorDeCreditos{

	private int maximoCreditos;
	
	public ValidadorMax(int maximoCreditos) {
		this.maximoCreditos = maximoCreditos;
	}

	@Override
	public boolean eValido(int creditosPeriodo, int creditoDisciplina) {
		boolean result = true;
		if (creditosPeriodo + creditoDisciplina > maximoCreditos) {
			result = false;
		}
		return result;
	}
}
