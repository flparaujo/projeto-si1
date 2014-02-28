package tdd;

import static org.junit.Assert.*;

import java.util.ArrayList;

import models.Disciplina;

import org.junit.Before;
import org.junit.Test;

/**
 * Testes para a classe Disciplina
 *
 */
public class DisciplinaTest {
	
	private Disciplina disciplina;

	@Before
	public void setUp() {
		disciplina = new Disciplina(1L,"Programacao 1", 4, new ArrayList<Disciplina>(), 5, 1);
	}

	@Test
	public void testaConstrutor() {
		assertEquals("Programacao 1", disciplina.getNome());
		assertEquals(4, disciplina.getNumeroDeCreditos());
		assertEquals(5, disciplina.getDificuldade());
	}
	
}
