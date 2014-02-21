package tdd;

import static org.junit.Assert.*;

import javax.naming.LimitExceededException;

import models.Periodo;

import org.junit.Before;
import org.junit.Test;

import controllers.SistemaDePlanejamentoDeCurso;
import exceptions.AlocacaoInvalidaException;
import exceptions.LimiteDeCreditosExcedidoException;
import exceptions.LimiteDePeriodosException;

/**
 * Testes para a classe SistemaDePlanejamentoDeCurso
 *
 */
public class SistemaDePlanejamentoDeCursoTest {

	private SistemaDePlanejamentoDeCurso sistema;

	@Before
	public void setUp() {
	   sistema = new SistemaDePlanejamentoDeCurso();
	}

	@Test
	public void deveMostrarDisciplinasDoPrimeiroPeriodo() {
		assertEquals(24, sistema.numeroDeCreditosDoPeriodo(0));
		
	}
	
	@Test
	public void deveAdicionarDisciplinaEmPeriodo() throws Exception {
		sistema.adicionaPeriodo();
		sistema.adicionaDisciplinaAoPeriodo(1, "Lab. de Programacao II");
		sistema.adicionaDisciplinaAoPeriodo(1, "Programacao II");
		sistema.adicionaDisciplinaAoPeriodo(1, "Calculo II");
	}

	@Test
	public void naoDeveAlocarDisciplinaAoPeriodo() throws Exception {
		sistema.adicionaPeriodo();
		try {
			sistema.adicionaDisciplinaAoPeriodo(1, "Banco de Dados I");
			fail("Esperava excecao de nao poder alocar disciplina que tem " +
					"pre-requisito(s) nao satisfeito(s)");
		} catch (AlocacaoInvalidaException e) {
			assertEquals("Nao pode alocar essa disciplina ao periodo. Ha " +
					"pre-requisito(s) nao satisfeito(s).", e.getMessage());
		} 
		try {
			sistema.adicionaDisciplinaAoPeriodo(1, "Calculo III");
			fail("Esperava excecao de nao poder alocar disciplina que tem " +
					"pre-requisito(s) nao satisfeito(s)");
		} catch (AlocacaoInvalidaException e) {
			assertEquals("Nao pode alocar essa disciplina ao periodo. Ha " +
					"pre-requisito(s) nao satisfeito(s).", e.getMessage());
		} 
	}
	
	@Test
	public void naoPodeAlocarDisciplinaEmPeriodoAnteriorAoDoPreRequisito() throws Exception {
		sistema.adicionaPeriodo();
		sistema.adicionaPeriodo();
		sistema.adicionaDisciplinaAoPeriodo(2, "Gerencia da Informacao");
		try {
			sistema.adicionaDisciplinaAoPeriodo(1, "Sistemas de Informacao I");
			fail("Esperava excecao de nao poder alocar disciplina que tem pre-requisito(s)" +
					" nao satisfeito(s)");
		} catch (AlocacaoInvalidaException e) {
			assertEquals("Nao pode alocar essa disciplina ao periodo. Ha " +
					"pre-requisito(s) nao satisfeito(s).", e.getMessage());
		} 
	}
	
	@Test
	public void naoPodeAlocarDisciplinaNoMesmoPeriodoDoPreRequisito() throws Exception {
		sistema.adicionaPeriodo();
		sistema.adicionaPeriodo();
		sistema.adicionaDisciplinaAoPeriodo(2, "Gerencia da Informacao");
		try {
			sistema.adicionaDisciplinaAoPeriodo(2, "Sistemas de Informacao I");
			fail("Esperava excecao de nao poder alocar disciplina que tem pre-requisito(s)" +
					" nao satisfeito(s)");
		} catch (AlocacaoInvalidaException e) {
			assertEquals("Nao pode alocar essa disciplina ao periodo. Ha " +
					"pre-requisito(s) nao satisfeito(s).", e.getMessage());
		} 
	}
	
	@Test
	public void naoPodeAdicionarDisciplinaAcimaDoLimiteMaximoDeCreditos() throws Exception {
		sistema.adicionaPeriodo();
		sistema.adicionaDisciplinaAoPeriodo(1, "Algebra Linear");
		sistema.adicionaDisciplinaAoPeriodo(1, "Administracao");
		sistema.adicionaDisciplinaAoPeriodo(1, "Gerencia da Informacao");
		sistema.adicionaDisciplinaAoPeriodo(1, "Futsal");
		sistema.adicionaDisciplinaAoPeriodo(1, "Ingles");
		sistema.adicionaDisciplinaAoPeriodo(1, "Fund. de Fisica Classica");
		sistema.adicionaDisciplinaAoPeriodo(1, "Informatica e Sociedade");
		sistema.adicionaDisciplinaAoPeriodo(1, "Empreendedorismo I");
		try {
			sistema.adicionaDisciplinaAoPeriodo(1, "Expressao Grafica");
			fail("Esperava excecao de nao poder alocar disciplina ultrapassando o " +
					"limite de " + Periodo.MAXIMO_DE_CREDITOS + " creditos");
		}
		catch(LimiteDeCreditosExcedidoException e) {
			assertEquals("Nao foi possivel alocar a disciplina. Limite maximo eh " +
					"de " + Periodo.MAXIMO_DE_CREDITOS + " creditos!", e.getMessage());
		} 
	}
	
	@Test
	public void deveRemoverDisciplina() throws Exception {
		sistema.adicionaPeriodo();
		sistema.adicionaPeriodo();
		sistema.adicionaPeriodo();
		sistema.adicionaDisciplinaAoPeriodo(1, "Matematica Discreta");
		sistema.adicionaDisciplinaAoPeriodo(1, "Teoria dos Grafos");
		sistema.adicionaDisciplinaAoPeriodo(2, "Teoria da Computacao");
		sistema.adicionaDisciplinaAoPeriodo(3, "Logica Matematica");
		
		sistema.devolveDisciplinaParaGrade("Teoria dos Grafos");
		sistema.removeDisciplinasDesalocadas();
		
		assertEquals(null, sistema.getPeriodo(1).getDisciplina("Teoria dos Grafos"));
		assertEquals(null, sistema.getPeriodo(2).getDisciplina("Teoria da Computacao"));
		assertEquals(null, sistema.getPeriodo(3).getDisciplina("Logica Matematica"));
		
		assertNotEquals(null, sistema.getDisciplinaDaGrade("Teoria dos Grafos"));
		assertNotEquals(null, sistema.getDisciplinaDaGrade("Teoria da Computacao"));
		assertNotEquals(null, sistema.getDisciplinaDaGrade("Logica Matematica")); 
	}
	
	@Test
	public void deveMostrarDificuldadeTotalAlocadaEmPeriodo() throws Exception {
		assertEquals(25, sistema.dificuldadeDoPeriodo(0));
		
		sistema.adicionaPeriodo();
		assertEquals(0, sistema.dificuldadeDoPeriodo(1));
		sistema.adicionaDisciplinaAoPeriodo(1, "Calculo II");
		assertEquals(7, sistema.dificuldadeDoPeriodo(1));
		sistema.adicionaDisciplinaAoPeriodo(1, "Programacao II");
		assertEquals(12, sistema.dificuldadeDoPeriodo(1));
		sistema.adicionaDisciplinaAoPeriodo(1, "Lab. de Programacao II");
		assertEquals(17, sistema.dificuldadeDoPeriodo(1));
		sistema.adicionaDisciplinaAoPeriodo(1, "Teoria dos Grafos");
		assertEquals(20, sistema.dificuldadeDoPeriodo(1));
		
		sistema.devolveDisciplinaParaGrade("Calculo II");
		sistema.removeDisciplinasDesalocadas();
		assertEquals(13, sistema.dificuldadeDoPeriodo(1));
	}
	
	@Test
	public void deveMostrarCreditosDeDisciplinaDeQualquerPeriodo() {
		assertEquals(2, sistema.getDisciplinaDaGrade("Teoria dos Grafos").getNumeroDeCreditos());
	}
	
	@Test
	public void naoPodeAdicionarPeriodoAcimaDoLimite() throws LimiteDePeriodosException {
		sistema.adicionaPeriodo();
		sistema.adicionaPeriodo();
		sistema.adicionaPeriodo();
		sistema.adicionaPeriodo();
		sistema.adicionaPeriodo();
		sistema.adicionaPeriodo();
		sistema.adicionaPeriodo();
		sistema.adicionaPeriodo();
		sistema.adicionaPeriodo();
		sistema.adicionaPeriodo();
		sistema.adicionaPeriodo();
		sistema.adicionaPeriodo();
		sistema.adicionaPeriodo();
		try{
			sistema.adicionaPeriodo();
			fail("deveria lancar excecao");
		} catch (LimiteDePeriodosException e) {
			assertEquals("Limite maximo de periodos atingindo.", e.getMessage());
		}
	}
}
