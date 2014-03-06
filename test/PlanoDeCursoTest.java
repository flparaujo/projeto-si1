import models.Disciplina;
import models.PlanoDeCurso;
import models.exceptions.LimiteUltrapassadoException;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import MOCK.GerenciadorDeCadeiras;

public class PlanoDeCursoTest {
	
	PlanoDeCurso plano;
	
	@Before
	public void setUp() {
		plano = new PlanoDeCurso();
		plano.distribuiCaderas(GerenciadorDeCadeiras.getListaDeCadeiras());
	}

	@Test
	public void testaDadosPlano(){
		Assert.assertEquals(8, plano.getPeriodos().size());
		Assert.assertEquals(55, plano.getDisciplinasAlocadas().size());
		Assert.assertEquals(0, plano.getDisciplinaDispniveisOrdenadas().size());	
	}
	
	@Test
	public void testaDisciplinasESeusRequisitos(){
		Disciplina p1 = plano.getMapaDisciplina().get("Programação I");
		Disciplina p2 = plano.getMapaDisciplina().get("Programação II");
		Disciplina c1 = plano.getMapaDisciplina().get("Cálculo I");
		Disciplina c2 = plano.getMapaDisciplina().get("Cálculo II");
		Assert.assertEquals(true, c2.isPreRequisito(c1));
		Assert.assertEquals(true, p2.isPreRequisito(p1));
		Assert.assertEquals(false, p2.isPreRequisito(c1));
	}
	
	@Test
	public void testaListarPrimeiroPeriodo() {
		Disciplina p1 = plano.getMapaDisciplina().get("Programação I");
		Disciplina lp1 = plano.getMapaDisciplina().get("Lab. de Programação I");
		Disciplina ic = plano.getMapaDisciplina().get("Int. à Computacação");
		Disciplina lpt = plano.getMapaDisciplina().get("Leitura e Prod. de Textos");
		Disciplina c1 = plano.getMapaDisciplina().get("Cálculo I");
		Disciplina vet = plano.getMapaDisciplina().get("Algebra Vetorial");
		Assert.assertEquals(6, plano.getPeriodo(1).getDisciplinas().size());
		Assert.assertEquals(p1, plano.getPeriodo(1).getDisciplina(p1.getNome()));
		Assert.assertEquals(lp1, plano.getPeriodo(1).getDisciplina(lp1.getNome()));
		Assert.assertEquals(ic, plano.getPeriodo(1).getDisciplina(ic.getNome()));
		Assert.assertEquals(lpt, plano.getPeriodo(1).getDisciplina(lpt.getNome()));
		Assert.assertEquals(c1, plano.getPeriodo(1).getDisciplina(c1.getNome()));
		Assert.assertEquals(vet, plano.getPeriodo(1).getDisciplina(vet.getNome()));
		Assert.assertEquals(24, plano.getPeriodo(1).getCreditos());
	}
	
	@Test
	public void testaAdicionarDisciplina() {
		Disciplina p1 = plano.getMapaDisciplina().get("Programação I");

		try {
			plano.adicionaDisciplina("Programação I", 2);
		} catch (LimiteUltrapassadoException e) {
			Assert.assertEquals("Limite de Créditos Ultrapassado!", e.getMessage());
		}
		try {
			plano.adicionaDisciplina("Programação I", 8);
			Assert.assertEquals(p1, plano.getPeriodo(8).getDisciplina(p1.getNome()));
		} catch (LimiteUltrapassadoException e) {
			Assert.fail("nao devia ter lançado exceptio");
		}
		Assert.assertEquals(5, plano.getPeriodo(1).getDisciplinas().size());
		Assert.assertEquals(7, plano.getPeriodo(8).getDisciplinas().size());
	}

	@Test
	public void testaCreditosDoPeriodo() throws LimiteUltrapassadoException {
		Assert.assertEquals(24, plano.getPeriodo(1).getCreditos());
		Assert.assertEquals(26, plano.getPeriodo(2).getCreditos());
		Assert.assertEquals(28, plano.getPeriodo(3).getCreditos());
		Assert.assertEquals(26, plano.getPeriodo(4).getCreditos());
		Assert.assertEquals(24, plano.getPeriodo(5).getCreditos());
		Assert.assertEquals(28, plano.getPeriodo(6).getCreditos());
		Assert.assertEquals(28, plano.getPeriodo(7).getCreditos());
		Assert.assertEquals(24, plano.getPeriodo(8).getCreditos());
		Disciplina p1 = plano.getMapaDisciplina().get("Programação I");
		try {
			plano.adicionaDisciplina("Programação I", 8);
			Assert.assertEquals(p1, plano.getPeriodo(8).getDisciplina(p1.getNome()));
		} catch (LimiteUltrapassadoException e) {
			Assert.fail("nao devia ter lançado exceptio");
		}
		Assert.assertEquals(20, plano.getPeriodo(1).getCreditos());
		Assert.assertEquals(28, plano.getPeriodo(8).getCreditos());
	}

	@Test
	public void testaUltrapassarLimiteDeCreditos() throws LimiteUltrapassadoException {
		try {
			plano.adicionaDisciplina("Programação I", 2);
			Assert.fail("nao devia ter passado");
		} catch (LimiteUltrapassadoException e) {
			Assert.assertEquals("Limite de Créditos Ultrapassado!", 
					e.getMessage());
		}
	}

	@Test
	public void testaDificuldade() {
		Assert.assertEquals(25, plano.getPeriodo(1).getDificuldadeTotal());
		Assert.assertEquals(37, plano.getPeriodo(2).getDificuldadeTotal());
		Assert.assertEquals(50, plano.getPeriodo(3).getDificuldadeTotal());
		Assert.assertEquals(35, plano.getPeriodo(4).getDificuldadeTotal());
		Assert.assertEquals(43, plano.getPeriodo(5).getDificuldadeTotal());
		Assert.assertEquals(36, plano.getPeriodo(6).getDificuldadeTotal());
		Assert.assertEquals(27, plano.getPeriodo(7).getDificuldadeTotal());
		Assert.assertEquals(20, plano.getPeriodo(8).getDificuldadeTotal());
	}

	@Test
	public void testaAdicionaDisciplinaComPreRequisitoEmPeriodoPosterior() {
		Disciplina p1 = plano.getMapaDisciplina().get("Programação I");
		Disciplina p2 = plano.getMapaDisciplina().get("Programação II");
		Disciplina lpt = plano.getMapaDisciplina().get("Leitura e Prod. de Textos");
		try {
			plano.adicionaDisciplina(p1.getNome(), 8);
		} catch (Exception e) {
			Assert.fail("Nao devia ter falhado.");
		}
		Assert.assertEquals(true, plano.isPreRequisito(p1.getNome()));
		Assert.assertEquals(true, plano.verificaPrerequisito(p2.getNome()));
		Assert.assertEquals(false, plano.verificaPrerequisito(p1.getNome()));
		Assert.assertEquals(false, plano.verificaPrerequisito(lpt.getNome()));
	}
}