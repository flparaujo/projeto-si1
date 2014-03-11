package models.exceptions;

/**
 * Essa excecao eh lancada quando se tenta adicionar mais periodos do que o maximo.
 *
 */
public class LimiteDePeriodosException extends Exception {
	
	private static final long serialVersionUID = 1L;

	/**
	 * Constroi uma nova LimiteDePeriodosException.
	 */
	public LimiteDePeriodosException() {
		super("Limite maximo de periodos atingido!");
	}

}
