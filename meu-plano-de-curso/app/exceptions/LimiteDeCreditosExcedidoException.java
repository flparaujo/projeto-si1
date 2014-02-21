package exceptions;

import javax.naming.LimitExceededException;

import models.Periodo;

/**
 * Essa excecao eh lancada quando se tenta adcionar uma disciplina ultrapassando o limite 
 * maximo de creditos por periodo.
 * 
 * 
 *
 */
public class LimiteDeCreditosExcedidoException extends LimitExceededException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4956923551416438669L;

	/**
	 * Construtor de LimiteDeCreditosExcedido
	 */
	public LimiteDeCreditosExcedidoException() {
		super("Nao foi possivel alocar a disciplina. Limite maximo eh " +
				"de "+ Periodo.MAXIMO_DE_CREDITOS +" creditos!");
	}

}
