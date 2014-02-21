package tdd;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import models.Disciplina;
import models.GradeCurricular;
import org.junit.Before;
import org.junit.Test;

/**
 * Testes para a classe GradeCurricular
 */
public class GradeCurricularTest {

	private GradeCurricular gradeCurricular;
	
	@Before
	public void setUp() {
		gradeCurricular = new GradeCurricular();
	}

	@Test
	public void deveConterDisciplina() {
		List<Disciplina> requisito1 = new ArrayList<Disciplina>();
		Disciplina p1 = new Disciplina("Programação I", 4, requisito1, 4);
		Disciplina lp1 = new Disciplina("Lab. de Programação I", 4, requisito1, 4);
		Disciplina ic = new Disciplina("Int. à Computação", 4, requisito1, 5);
		List<Disciplina> requisito2 = new ArrayList<Disciplina>();
		requisito2.add(lp1);
		requisito2.add(p1);
		List<Disciplina> requisito3 = new ArrayList<Disciplina>();
		requisito3.add(lp1);
		requisito3.add(p1);
		requisito3.add(ic);
		Disciplina tg = new Disciplina("Teoria dos Grafos", 2, requisito2, 3);
		Disciplina p2 = new Disciplina("Programação II", 4, requisito3, 5);
		Disciplina lp2 = new Disciplina("Lab. de Programação II", 4, requisito3, 5);
		List<Disciplina> requisito4 = new ArrayList<Disciplina>();
		requisito4.add(lp2);
		requisito4.add(p2);
		requisito4.add(tg);
		Disciplina eda = new Disciplina("Estruturas" +
				" de Dados", 4, requisito4,7);
		assertTrue(gradeCurricular.getDisciplinas().contains(eda));
		Disciplina gi = new Disciplina("Gerência da Informação", 4, requisito1, 5);
		List<Disciplina> requisito5 = new ArrayList<Disciplina>();
		requisito5.add(gi);
		assertTrue(gradeCurricular.getDisciplinas().contains(new Disciplina("Sistemas de " +
				"Informacao I", 4, requisito5,5)));
		List<Disciplina> requisito6 = new ArrayList<Disciplina>();
		requisito6.add(eda);
		Disciplina leda = new Disciplina("Lab. de Estrutura de Dados", 4, requisito4, 7);
		requisito6.add(leda);
		List<Disciplina> requisito7 = new ArrayList<Disciplina>();
		Disciplina cal1 = new Disciplina("Cálculo I", 4, requisito1, 7);
		List<Disciplina> requisito8 = new ArrayList<Disciplina>();
		requisito8.add(cal1);
		Disciplina cal2 = new Disciplina("Cálculo II", 4, requisito8, 7);
		Disciplina vetorial = new Disciplina("Algebra Vetorial", 4, requisito1, 7);
		requisito7.add(vetorial);
		requisito7.add(cal2);
		Disciplina fisicaC = new Disciplina("Fund. de Física Clássica", 4, requisito7, 8);
		List<Disciplina> requisito9 = new ArrayList<Disciplina>();
		requisito9.add(cal2);
		requisito9.add(fisicaC);
		Disciplina fisicaM = new Disciplina("Fund. de Física Moderna", 4, requisito9, 7);
		requisito6.add(fisicaM);
		assertTrue(gradeCurricular.getDisciplinas().contains(new Disciplina("Lab. de Org. " +
				"e Arquitetura de Computadores", 4, requisito6, 5)));
	}
	
	@Test
	public void deveFornecerDisciplina() {
		List<Disciplina> requisitosTc = new ArrayList<Disciplina>();
		List<Disciplina> requisitosP1 = new ArrayList<Disciplina>();
		Disciplina ic = new Disciplina("Int. à Computação", 4, requisitosP1, 5);
		requisitosTc.add(ic);
		Disciplina md = new Disciplina("Matemática Discreta", 4, requisitosP1, 5);
		requisitosTc.add(md);
		Disciplina p1 = new Disciplina("Programação I", 4, requisitosP1, 4);
		Disciplina lp1 = new Disciplina("Lab. de Programação I", 4, requisitosP1, 4);
		List<Disciplina> requisitosTg = new ArrayList<Disciplina>();
		requisitosTg.add(lp1);
		requisitosTg.add(p1);
		Disciplina tg = new Disciplina("Teoria dos Grafos", 2, requisitosTg, 3);
		requisitosTc.add(tg);
		assertEquals(new Disciplina("Teoria da Computacao", 4, requisitosTc, 6), gradeCurricular.
				getDisciplina("Teoria da Computacao"));
	}

}
