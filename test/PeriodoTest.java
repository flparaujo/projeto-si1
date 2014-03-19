
import static play.test.Helpers.fakeApplication;
import static play.test.Helpers.inMemoryDatabase;
import static play.test.Helpers.start;

import java.util.ArrayList;
import java.util.List;

import models.Disciplina;
import models.Periodo;
import models.ValidadorMax;
import models.exceptions.LimiteUltrapassadoException;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;


public class PeriodoTest {
    
	Periodo periodo;
	
	@Before
	public void setUp() {
		periodo = new Periodo();
		periodo.setEValido(new ValidadorMax(28));
		start(fakeApplication(inMemoryDatabase()));
//		periodo.save();
	}
	
	@Test
	public void testaDisciplinasESeusRequisitos() {
		List<Disciplina> t1 = new ArrayList<Disciplina>();
		Disciplina p1 = new Disciplina("Programacao I", 4, t1, 4, 1);
		List<Disciplina> t2 = new ArrayList<Disciplina>();
		t2.add(p1);
		Disciplina p2 = new Disciplina("Programacao II", 4, t2, 4, 1);
		Disciplina c1 = new Disciplina("Calculo I", 4, t1, 4, 1);
		List<Disciplina> t3 = new ArrayList<Disciplina>();
		t3.add(c1);
		Disciplina c2 = new Disciplina("Calculo II", 4, t3, 4, 1);
		Assert.assertEquals(true, c2.isPreRequisito(c1));
		Assert.assertEquals(true, p2.isPreRequisito(p1));
		Assert.assertEquals(false, p2.isPreRequisito(c1));
//		assertEquals(periodo, Periodo.find.all().get(0));
	}

	@Test
	public void test() {
		List<Disciplina> t1 = new ArrayList<Disciplina>();
		Disciplina p1 = new Disciplina("Programacao I", 5, t1, 4, 1);
		try {
			periodo.adicionarDisciplina(p1);
			Assert.assertEquals(p1, periodo.getDisciplina(p1.getNome()));
		} catch (LimiteUltrapassadoException e) {
			Assert.fail("nao devia ter lançado exceptio");
		}
		Assert.assertEquals(1, periodo.getDisciplinas().size());
		Assert.assertEquals(5, periodo.getCreditos());
		Assert.assertEquals(4, periodo.getDificuldadeTotal());
		
		Disciplina p2 = new Disciplina("Programacao II", 5, t1, 4, 1);
		Disciplina c1 = new Disciplina("Calculo I", 5, t1, 4, 1);
		Disciplina c2 = new Disciplina("Calculo II", 5, t1, 4, 1);
		Disciplina c3 = new Disciplina("Calculo II", 5, t1, 4, 1);
		Disciplina p3 = new Disciplina("Programacao II", 5, t1, 4, 1);
		try {
			periodo.adicionarDisciplina(p2);
		} catch (LimiteUltrapassadoException e1) {
			e1.printStackTrace();
		}
		Assert.assertEquals(10, periodo.getCreditos());
		Assert.assertEquals(8, periodo.getDificuldadeTotal());
		try {
			periodo.adicionarDisciplina(p3);
		} catch (LimiteUltrapassadoException e1) {
			e1.printStackTrace();
		}
		Assert.assertEquals(15, periodo.getCreditos());
		Assert.assertEquals(12, periodo.getDificuldadeTotal());
		try {
			periodo.adicionarDisciplina(c1);
		} catch (LimiteUltrapassadoException e1) {
			e1.printStackTrace();
		}
		Assert.assertEquals(20, periodo.getCreditos());
		Assert.assertEquals(16, periodo.getDificuldadeTotal());
		try {
			periodo.adicionarDisciplina(c2);
		} catch (LimiteUltrapassadoException e1) {
			e1.printStackTrace();
		}
		Assert.assertEquals(25, periodo.getCreditos());
		Assert.assertEquals(20, periodo.getDificuldadeTotal());
		try {
			periodo.adicionarDisciplina(c3);
			Assert.fail("Limite de Créditos Ultrapassado!");
		} catch (LimiteUltrapassadoException e) {
			Assert.assertEquals("Limite de Créditos Ultrapassado!", 
					e.getMessage());
		}
		Assert.assertEquals(5, periodo.getDisciplinas().size());
		Assert.assertEquals(25, periodo.getCreditos());
		Assert.assertEquals(20, periodo.getDificuldadeTotal());
	}
}
