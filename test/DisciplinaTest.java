import static org.junit.Assert.*;

import java.util.ArrayList;

import models.Disciplina;

import org.junit.Before;
import org.junit.Test;
import static play.test.Helpers.*;

/**
 * Testes para a classe Disciplina
 *
 */
public class DisciplinaTest {
	
	private Disciplina disciplina;
	
	@Before
	public void setUp() {
		disciplina = new Disciplina("Programacao 1", 4, new ArrayList<Disciplina>(), 5, 1);
		start(fakeApplication(inMemoryDatabase()));
//		disciplina.save();
	}

	@Test
	public void testaConstrutor() {
		assertEquals("Programacao 1", disciplina.getNome());
		assertEquals(4, disciplina.getCreditos());
		assertEquals(5, disciplina.getDificuldade());
//		assertEquals(disciplina, Disciplina.find.all().get(0));
	}
	
}
