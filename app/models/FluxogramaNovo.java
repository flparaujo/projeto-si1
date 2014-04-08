package models;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FluxogramaNovo implements Fluxograma {

	private Map<String, Disciplina> disciplinas;
	private int numerDePeriodos;
	
	public FluxogramaNovo() {
		disciplinas = atualizaMapaCadeira(Disciplina.find.all());
		organizaDisciplinas();
		numerDePeriodos = 9;
	}

	public Map<String, Disciplina> atualizaMapaCadeira(List<Disciplina> disc){
		Map<String, Disciplina> mapa = new HashMap<String, Disciplina>();
		for(Disciplina d: disc){
			mapa.put(d.getNome(), d);
		}
		return mapa;
	}
	
	private void organizaDisciplinas() {
		organiza1Periodo();
		organiza2Periodo();
		organiza3Periodo();
		organiza4Periodo();
		organiza5Periodo();
		organiza6Periodo();
		organiza7Periodo();
		organiza8Periodo();
		organiza9Periodo();
		deletaDisciplinas();
	}
	
	private void deletaDisciplinas() {
		disciplinas.get("Matemática Discreta").setPeriodo(-1);
		disciplinas.get("Sistemas de Informação I").setPeriodo(-1);
		disciplinas.get("Sistemas de Informação II").setPeriodo(-1);
		disciplinas.get("Engenharia de Software I").setPeriodo(-1);
		disciplinas.get("Inteligência Artificial I").setPeriodo(-1);
		disciplinas.get("Leitura e Prod. de Textos").setPeriodo(-1);
		disciplinas.get("Fund. de Física Clássica").setPeriodo(-1);
		disciplinas.get("Fund. de Física Moderna").setPeriodo(-1);
		disciplinas.get("Probabilidade e Est.").setPeriodo(-1);
		disciplinas.get("Gerência da Informação").setPeriodo(-1);
		disciplinas.get("Métodos Estatísticos").setPeriodo(-1);
		disciplinas.get("Laboratório de Engenharia de Software").setPeriodo(-1);
		disciplinas.get("Lab. de Interconexão de Redes de Computadores").setPeriodo(-1);
		disciplinas.get("Algebra Vetorial").setPeriodo(0);
		disciplinas.get("Informática e Sociedade").setPeriodo(0);
		disciplinas.get("Banco de Dados II").setPeriodo(0);
		disciplinas.get("Direito e Cidadania").setPeriodo(0);
		disciplinas.get("Interconexão de Redes de Computadores").setPeriodo(0);
		disciplinas.get("Aval. de Desempenho de Sist. Discretos").setPeriodo(0);
		disciplinas.get("Métodos e Software Númericos").setPeriodo(0);
	}

	private void organiza1Periodo() {
		disciplinas.get("Matemática Discreta I").setPeriodo(1);
		disciplinas.get("Optativa 1").setPeriodo(1);
	}

	private void organiza2Periodo() {
		disciplinas.get("Matemática Discreta II").setPeriodo(2);
		disciplinas.get("Cálculo I").setPeriodo(2);
		disciplinas.get("Optativa 2").setPeriodo(2);
		List<Disciplina> requisitos2 = new ArrayList<Disciplina>();
		requisitos2.add(disciplinas.get("Programação I"));
		requisitos2.add(disciplinas.get("Lab. de Programação I"));
		disciplinas.get("Programação II").setPreRequisitos(requisitos2);
		disciplinas.get("Programação II").update();
		disciplinas.get("Lab. de Programação II").setPreRequisitos(requisitos2);
		disciplinas.get("Lab. de Programação II").update();
	}

	private void organiza3Periodo() {
		List<Disciplina> requisitos1 = new ArrayList<Disciplina>();
		requisitos1.add(disciplinas.get("Matemática Discreta I"));
		disciplinas.get("Algebra Linear").setPeriodo(3);
		disciplinas.get("Algebra Linear").setPreRequisitos(requisitos1);
		disciplinas.get("Algebra Linear").update();
		disciplinas.get("Teoria dos Grafos").setPeriodo(3);
		disciplinas.get("Teoria dos Grafos").setCreditos(4);
		disciplinas.get("Teoria dos Grafos").setPreRequisitos(new ArrayList<Disciplina>());
		disciplinas.get("Cálculo II").setPeriodo(3);
		disciplinas.get("Estrutura de Dados").getPreRequisitos().remove(disciplinas.get("Teoria dos Grafos"));
		disciplinas.get("Lab. de Estrutura de Dados").getPreRequisitos().remove(disciplinas.get("Teoria dos Grafos"));
		disciplinas.get("Estrutura de Dados").update();
		disciplinas.get("Lab. de Estrutura de Dados").update();
		disciplinas.get("Lógica Matemática").setPeriodo(3);
		disciplinas.get("Lógica Matemática").setPreRequisitos(requisitos1);
		disciplinas.get("Lógica Matemática").update();
	}

	private void organiza4Periodo() {
		disciplinas.get("Introdução à Probabilidade").setPeriodo(4);
		disciplinas.get("Projeto de Software").setPeriodo(4);
		disciplinas.get("Paradigmas de Linguagens de Programação").setPreRequisitos(new ArrayList<Disciplina>());
		disciplinas.get("Paradigmas de Linguagens de Programação").setPeriodo(4);
		disciplinas.get("Paradigmas de Linguagens de Programação").setCreditos(4);
		List<Disciplina> requisitos5 = new ArrayList<Disciplina>();
		requisitos5.add(disciplinas.get("Estrutura de Dados"));
		disciplinas.get("Banco de Dados I").setPreRequisitos(requisitos5);
		disciplinas.get("Banco de Dados I").setPeriodo(4);
		disciplinas.get("Banco de Dados I").update();
		disciplinas.get("Org. e Arquitetura de Computadores I").setPreRequisitos(new ArrayList<Disciplina>());
		disciplinas.get("Lab. de Org. e Arquitetura de Computadores").setPreRequisitos(new ArrayList<Disciplina>());
	}

	private void organiza5Periodo() {
		disciplinas.get("Estatístia Aplicada").setPeriodo(5);
		disciplinas.get("Análise de Sistemas").setPeriodo(5);
		disciplinas.get("Engenharia de Software").setPeriodo(5);
		disciplinas.get("Redes de Computadores").setPreRequisitos(new ArrayList<Disciplina>());
		disciplinas.get("Sistemas Operacionais").setPreRequisitos(new ArrayList<Disciplina>());
		disciplinas.get("Sistemas Operacionais").setPeriodo(5);
		List<Disciplina> requisitos7 = new ArrayList<Disciplina>();
		requisitos7.add(disciplinas.get("Paradigmas de Linguagens de Programação"));
		disciplinas.get("Teoria da Computação").setPeriodo(5);
		disciplinas.get("Teoria da Computação").setPreRequisitos(requisitos7);
		disciplinas.get("Teoria da Computação").update();
	}

	private void organiza6Periodo() {
		disciplinas.get("Metodologia Científica").setPeriodo(6);
		disciplinas.get("Programação Concorrente").setPeriodo(6);
		disciplinas.get("Inteligência Artificial").setPeriodo(6);
		disciplinas.get("Optativa 3").setPeriodo(6);
		disciplinas.get("Optativa 4").setPeriodo(6);
	}

	private void organiza7Periodo() {
		disciplinas.get("Análise e Técnicas de Algoritmos").setPeriodo(7);
		disciplinas.get("Análise e Técnicas de Algoritmos").setPreRequisitos(new ArrayList<Disciplina>());
		disciplinas.get("Compiladores").setPeriodo(7);
		disciplinas.get("Compiladores").setPreRequisitos(new ArrayList<Disciplina>());
		disciplinas.get("Optativa 5").setPeriodo(7);
		disciplinas.get("Optativa 6").setPeriodo(7);
		disciplinas.get("Optativa 7").setPeriodo(7);
		disciplinas.get("Compiladores").setPeriodo(7);
		disciplinas.get("Compiladores").setPreRequisitos(new ArrayList<Disciplina>());
	}

	private void organiza8Periodo() {
		List<Disciplina> requisitos10 = new ArrayList<Disciplina>();
		requisitos10.add(disciplinas.get("Engenharia de Software"));
		disciplinas.get("Projeto em Computação I").setPreRequisitos(requisitos10);
		disciplinas.get("Projeto em Computação I").update();
		disciplinas.get("Projeto em Computação I").setPeriodo(8);
		disciplinas.get("Optativa 8").setPeriodo(8);
		disciplinas.get("Optativa 9").setPeriodo(8);
		disciplinas.get("Optativa 10").setPeriodo(8);
		disciplinas.get("Trabalho de Conclusão de Curso I").setPeriodo(8);
	}

	private void organiza9Periodo() {
		List<Disciplina> requisitos11 = new ArrayList<Disciplina>();
		requisitos11.add(disciplinas.get("Projeto em Computação I"));
		disciplinas.get("Projeto em Computação II").setPreRequisitos(requisitos11);
		disciplinas.get("Projeto em Computação II").update();
		disciplinas.get("Projeto em Computação II").setPeriodo(9);
		disciplinas.get("Projeto em Computação II").setCreditos(4);
		disciplinas.get("Optativa 11").setPeriodo(9);
		disciplinas.get("Optativa 11").setCreditos(4);
		disciplinas.get("Optativa 12").setPeriodo(9);
		disciplinas.get("Optativa 13").setPeriodo(9);
		disciplinas.get("Optativa 14").setPeriodo(9);
		disciplinas.get("Trabalho de Conclusão de Curso II").setPeriodo(9);
	}

	@Override
	public List<Disciplina> getDisciplinas() {
		List<Disciplina> disc = new ArrayList<Disciplina>();
		for (Disciplina d: disciplinas.values()) {
			disc.add(d);
		}
		return disc;
	}

	@Override
	public int getNumeroDePeriodos() {
		return numerDePeriodos;
	}
}