package tdd;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import models.Disciplina;
import models.LeitorDeDisciplinas;

import org.junit.Before;
import org.junit.Test;

/**
 * Testes para a classe LeitorDeDisciplinas
 *
 */
public class LeitorDeDisciplinasTest {

	private LeitorDeDisciplinas leitorDeDisciplinas;

	@Before
	public void setUp() {
		leitorDeDisciplinas = LeitorDeDisciplinas.getInstance();
	}
	
	@Test
	public void deveMostrarDisciplina () {
		List<Disciplina> requisitos = new ArrayList<Disciplina>();
		Disciplina p1 = new Disciplina(1L, "Programacao I", 4, requisitos, 5, 1);
		List<Disciplina> disciplinas = leitorDeDisciplinas.getDisciplinas();
		assertEquals(p1, disciplinas.get(0));
		List<Disciplina> requisitos1 = new ArrayList<Disciplina>();
		requisitos1.add(p1);
		requisitos1.add(new Disciplina (2L, "Int. a Computacao", 4, requisitos, 5, 1));
		requisitos1.add(new Disciplina (3L, "Lab. de Programacao I", 4, requisitos, 5, 1));
		Disciplina p2 = new Disciplina(4L, "Programacao II", 4, requisitos1, 5, 2);
		assertEquals(p2, disciplinas.get(6));
	}

}
