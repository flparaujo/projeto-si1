package exceptions;

import javax.naming.LimitExceededException;

/**
 * Essa excecao eh lancada quando se tenta adicionar mais periodos do que o maximo.
 * 
 * @author Franklin Wesley Bastos.
 *
 */
public class LimiteDePeriodosException extends LimitExceededException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Constroi uma nova LimiteDePeriodosException.
	 */
	public LimiteDePeriodosException() {
		super("Limite maximo de periodos atingido!");
	}

}
