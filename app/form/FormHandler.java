package form;

import play.data.validation.Constraints.*;

/**
 * Essa classe representa um formulario para pegar informações da interface.
 */
public class FormHandler {

	@Required
	private int idPeriodo;

	public void setIdPeriodo(int idPeriodo) {
		this.idPeriodo = idPeriodo;
	}

	public int getIdPeriodo() {
		return idPeriodo;
	}
}