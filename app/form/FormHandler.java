package form;

import play.data.validation.Constraints.*;

/**
 * Essa classe representa um formulario para pegar informações da interface.
 */
public class FormHandler {

	@Required
	private int idPeriodo;
	
	private String search;

	public String getSearch() {
		return search != null ? search : "";
	}

	public void setSearch(String search) {
		this.search = search;
	}

	public void setIdPeriodo(int idPeriodo) {
		this.idPeriodo = idPeriodo;
	}

	public int getIdPeriodo() {
		return idPeriodo;
	}
}