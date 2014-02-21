package exceptions;

import javax.naming.LimitExceededException;

public class LimiteDePeriodosException extends LimitExceededException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public LimiteDePeriodosException() {
		super("Limite maximo de periodos atingindo.");
	}

}
