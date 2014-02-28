package controllers;

import java.util.List;

import exceptions.LimiteDeCreditosExcedidoException;
import exceptions.LimiteDePeriodosException;
import models.Disciplina;
import models.Periodo;
import models.PlanejamentoDeCurso;

/**
 * Classe que representa o planejamento de curso.
 *
 */
public class SistemaDePlanejamentoDeCurso {

	private PlanejamentoDeCurso plano;
	
	public SistemaDePlanejamentoDeCurso() {
		if (!PlanejamentoDeCurso.find.all().isEmpty()){
			plano = PlanejamentoDeCurso.find.all().get(0);
		} else {
			plano = new PlanejamentoDeCurso();
			plano.save();
		}
	}
	
	public void adicionaPeriodo () throws LimiteDePeriodosException {
		plano.adicionaPeriodo();
		plano.update();
	}

	public void moveDisciplina (String inputNameDisciplina, int idPeriodo) throws LimiteDeCreditosExcedidoException {
		plano.moveDisciplina(inputNameDisciplina, idPeriodo);
		plano.update();
	}
	
	public List<Periodo> getPeriodos () {
		return plano.getPeriodos();
	}
	
	public List<Disciplina> getDisciplinasDoPeriodo (int indicePeriodo) {
		return plano.getDisciplinasDoPeriodo(indicePeriodo);
	}
	
	public boolean preRequisitosSatisfeitos (String nomeDaDisciplina) {
		return plano.preRequisitosSatisfeitos(nomeDaDisciplina);
	}
	
	public int numeroDeCreditosDoPeriodo (int indicePeriodo) {
		return plano.numeroDeCreditosDoPeriodo(indicePeriodo);
	}
	
	public int dificuldadeDoPeriodo (int indicePeriodo) {
		return plano.dificuldadeDoPeriodo(indicePeriodo);
	}
	
	public boolean periodoComCreditosAbaixoDoLimiteMinimo (int idPeriodo) {
		return plano.periodoComCreditosAbaixoDoLimiteMinimo(idPeriodo);
	}
	
	public List<Disciplina> disciplinasAlocadas () {
		return plano.disciplinasAlocadas();
	}
	
	public List<Disciplina> disciplinasDoCurso () {
		return plano.disciplinasDoCurso();
	}
}
