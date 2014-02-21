package exceptions;

/**
 * Essa excecao eh lancada quando se tenta fazer uma alocacao nao permitida.
 * 
 * Exemplo: alocar disciplina sem todos os pre-requisitos satisfeitos.
 * 
 * 
 *
 */
public class AlocacaoInvalidaException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * Cria uma nova AlocacaoInvalidaException.
	 * 
	 */
	public AlocacaoInvalidaException() {
		super("Nao pode alocar essa disciplina ao periodo. Ha pre-requisito(s) " +
				"nao satisfeito(s).");
	}
	

}
