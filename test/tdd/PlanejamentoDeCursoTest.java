package tdd;

import static org.junit.Assert.*;

import models.Disciplina;
import models.PlanejamentoDeCurso;

import org.junit.Before;
import org.junit.Test;

import exceptions.LimiteDeCreditosExcedidoException;
import exceptions.LimiteDePeriodosException;

/**
 * Testes para a classe PlanejamentoDeCurso
 *
 */
public class PlanejamentoDeCursoTest {

	private PlanejamentoDeCurso sistema;

	@Before
	public void setUp() {
	   sistema = new PlanejamentoDeCurso();
	}
	
	@Test
	public void naoDeveAdicionarPeriodoAcimaDoLimite() throws LimiteDePeriodosException {
		for(int i = PlanejamentoDeCurso.MINIMO_DE_PERIODOS; i < 
				PlanejamentoDeCurso.MAXIMO_DE_PERIODOS; i++) {
			sistema.adicionaPeriodo();
		}
		try {
			sistema.adicionaPeriodo();
		}
		catch(LimiteDePeriodosException e) {
			assertEquals("Limite maximo de periodos atingido!", e.getMessage());
		}
	}
	
	@Test
	public void deveMostrarBlocagemPadrao() {
		assertEquals(PlanejamentoDeCurso.MINIMO_DE_PERIODOS, sistema.getPeriodos().size());
		
		String[] alocacao = new String[sistema.getPeriodos().size()];
		for(int i = 0; i < alocacao.length; i++) {
			alocacao[i] = "";
			for(Disciplina disciplina : sistema.getDisciplinasDoPeriodo(i)) {
				alocacao[i] += disciplina.getNome();
			}
		}
		assertEquals("Algebra Vetorial" +
					"Calculo I" +
					"Int. a Computacao" +
					"Lab. de Programacao I" +
					"Leitura e Prod. de Textos" +
					"Programacao I", alocacao[0]);
		assertEquals(24, sistema.numeroDeCreditosDoPeriodo(0));
		
		assertEquals("Calculo II" +
				    "Fund. de Fisica Classica" +
				    "Lab. de Programacao II" +
				    "Matematica Discreta" +
				    "Metodologia Cientifica" +
					"Programacao II" +
					"Teoria dos Grafos", alocacao[1]);
		assertEquals(26, sistema.numeroDeCreditosDoPeriodo(1));
		
		assertEquals("Algebra Linear" +
					 "Estruturas de Dados" +
					 "Fund. de Fisica Moderna" +
					 "Gerencia da Informacao" +
					 "Lab. de Estruturas de Dados" +
					 "Probabilidade e Estatistica" +
					 "Teoria da Computacao", alocacao[2]);
		assertEquals(28, sistema.numeroDeCreditosDoPeriodo(2));
		
		assertEquals("Engenharia de Software I" +
				 	 "Lab. de Org. e Arquitetura de Computadores" +
				 	 "Logica Matematica" +
				 	 "Metodos Estatisticos" +
				 	 "Org. e Arquitetura de Computadores I" +
				 	 "Paradigmas de Linguagens de Programacao" +
				 	 "Sistemas de Informacao I", alocacao[3]);
		assertEquals(26, sistema.numeroDeCreditosDoPeriodo(3));
		
		assertEquals("Analise e Tecnicas de Algoritmos" +
			 	     "Banco de Dados I" +
			 	     "Compiladores" +
			 	     "Informatica e Sociedade" +
			 	     "Laboratorio de Engenharia de Software" +
			 	     "Redes de Computadores" +
			 	     "Sistemas de Informacao II", alocacao[4]);
		assertEquals(26, sistema.numeroDeCreditosDoPeriodo(4));
		
		assertEquals("Banco de Dados II" +
		 	     	 "Direito e Cidadania" +
		 	         "Inteligencia Artificial I" +
		 	         "Interconexao de Redes de Computadores" +
		 	         "Lab. de Interconexao de Redes de Computadores" +
		 	         "Sistemas Operacionais", alocacao[5]);
		assertEquals(24, sistema.numeroDeCreditosDoPeriodo(5));
		
		assertEquals("Administracao" +
					 "Aval. de Desempenho de Sist. Discretos" +
					 "Economia" +
					 "Ingles" +
				  	 "Metodos e Software Numericos" +
				  	 "Projeto em Computacao I" +
				  	 "Sociologia Industrial", alocacao[6]);
		assertEquals(27, sistema.numeroDeCreditosDoPeriodo(6));
		
		assertEquals("Projeto em Computacao II", alocacao[7]);
		assertEquals(6, sistema.numeroDeCreditosDoPeriodo(7));
				
	}
	
	@Test
	public void deveMoverDisciplina() throws LimiteDeCreditosExcedidoException {
		assertTrue(sistema.pesquisaDisciplinaEmPeriodo(0, "Matematica Discreta") == null);
		assertFalse(sistema.pesquisaDisciplinaEmPeriodo(1, "Matematica Discreta") == null);
		assertEquals(24, sistema.numeroDeCreditosDoPeriodo(0));
		assertEquals(26, sistema.numeroDeCreditosDoPeriodo(1));
		
		sistema.moveDisciplina("Matematica Discreta", 0);
		
		assertTrue(sistema.pesquisaDisciplinaEmPeriodo(1, "Matematica Discreta") == null);
		assertFalse(sistema.pesquisaDisciplinaEmPeriodo(0, "Matematica Discreta") == null);
		assertEquals(28, sistema.numeroDeCreditosDoPeriodo(0));
		assertEquals(22, sistema.numeroDeCreditosDoPeriodo(1));
	}
	
	@Test
	public void naoDeveMoverDisciplinaSeLimiteDeCreditosAtingido() {
		assertTrue(sistema.pesquisaDisciplinaEmPeriodo(2, "Calculo II") == null);
		assertFalse(sistema.pesquisaDisciplinaEmPeriodo(1, "Calculo II") == null);
		assertEquals(26, sistema.numeroDeCreditosDoPeriodo(1));
		assertEquals(28, sistema.numeroDeCreditosDoPeriodo(2));
		
		try {
			sistema.moveDisciplina("Calculo II", 2);
		} catch (LimiteDeCreditosExcedidoException e) {
			assertEquals("Nao foi possivel alocar a disciplina. Limite maximo de " +
					"creditos foi atingido!", e.getMessage());
		}
	}
	
	@Test
	public void deveAdicionarDisciplinaEmPeriodo() throws Exception {
		sistema.adicionaPeriodo();
		sistema.adicionaDisciplinaAoPeriodo(1, "Lab. de Programacao II");
		sistema.adicionaDisciplinaAoPeriodo(1, "Programacao II");
		sistema.adicionaDisciplinaAoPeriodo(1, "Calculo II");
	}
	
	@Test
	public void naoDeveUltrapassarLimiteMaximoDeCreditosEmPeriodoQueNaoSejaUltimo() throws Exception {
		try {
			sistema.adicionaDisciplinaAoPeriodo(1, "Administracao Financeira");
			fail("Esperava excecao de nao poder alocar disciplina ultrapassando o " +
					"limite de maximo de creditos atingido");
		}
		catch(LimiteDeCreditosExcedidoException e) {
			assertEquals("Nao foi possivel alocar a disciplina. Limite maximo de " +
					"creditos foi atingido!", e.getMessage());
		} 
		
		sistema.adicionaDisciplinaAoPeriodo(0, "Administracao Financeira");
		assertEquals(28, sistema.numeroDeCreditosDoPeriodo(0));
		try {
			sistema.adicionaDisciplinaAoPeriodo(0, "Expressao Grafica");
			fail("Esperava excecao de nao poder alocar disciplina ultrapassando o " +
					"limite de maximo de creditos atingido");
		}
		catch(LimiteDeCreditosExcedidoException e) {
			assertEquals("Nao foi possivel alocar a disciplina. Limite maximo de " +
					"creditos foi atingido!", e.getMessage());
		} 
	}
	
	@Test
	public void ultimoPeriodoNaoDeveTerLimiteDeCreditos() throws Exception {
		sistema.adicionaDisciplinaAoPeriodo(PlanejamentoDeCurso.MINIMO_DE_PERIODOS-1, 
				"Administracao Financeira");
		sistema.adicionaDisciplinaAoPeriodo(PlanejamentoDeCurso.MINIMO_DE_PERIODOS-1, 
				"Analise de dados I");
		sistema.adicionaDisciplinaAoPeriodo(PlanejamentoDeCurso.MINIMO_DE_PERIODOS-1, 
				"Desenvolvimento de Aplicacoes Corporativas Avancadas");
		sistema.adicionaDisciplinaAoPeriodo(PlanejamentoDeCurso.MINIMO_DE_PERIODOS-1, 
				"Empreendedorismo I");
		sistema.adicionaDisciplinaAoPeriodo(PlanejamentoDeCurso.MINIMO_DE_PERIODOS-1, 
				"Calculo III");
		sistema.adicionaDisciplinaAoPeriodo(PlanejamentoDeCurso.MINIMO_DE_PERIODOS-1, 
				"Equacoes Diferenciais");
		sistema.adicionaDisciplinaAoPeriodo(PlanejamentoDeCurso.MINIMO_DE_PERIODOS-1, 
				"Expressao Grafica");
		assertEquals(34, sistema.numeroDeCreditosDoPeriodo(PlanejamentoDeCurso.MINIMO_DE_PERIODOS-1));
	}
	
	@Test
	public void deveRemoverDisciplina() throws Exception {
		//TODO Fazer na proxima US
	}
	
	@Test
	public void deveMostrarDificuldadeTotalAlocadaEmPeriodo() throws Exception {
		assertEquals(25, sistema.dificuldadeDoPeriodo(0));
	}
	
	
}
