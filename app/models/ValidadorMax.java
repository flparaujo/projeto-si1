package models;

public class ValidadorMax implements ValidadorDeCreditos{

	private int maximoCreditos;
	
	public ValidadorMax(int maximoCreditos) {
		this.maximoCreditos = maximoCreditos;
	}

	@Override
	public boolean eValido(int creditosPeriodo, int creditoDisciplina) {
		if (creditosPeriodo + creditoDisciplina > maximoCreditos) {
			return false;
		}
		return true;
	}
}
