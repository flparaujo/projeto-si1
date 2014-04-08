package models.exceptions;

public class LimiteUltrapassadoException extends Exception {

	private static final long serialVersionUID = 1L;
	
	public LimiteUltrapassadoException(){
		super("Limite de Créditos Ultrapassado!");
	}

	@Override
	public String toString(){
		return this.getMessage();
	}
}
