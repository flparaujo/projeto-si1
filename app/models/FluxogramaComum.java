package models;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FluxogramaComum implements Fluxograma {

	private Map<String, Disciplina> disciplinas;
	private int numerDePeriodos;
	
	public FluxogramaComum() {
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
		organiza2Periodo();
		organiza4Periodo();
		organiza5Periodo();
		organiza6Periodo();
		organiza7Periodo();
		organiza8Periodo();
		organiza9Periodo();
	}
	
	private void organiza9Periodo() {
		disciplinas.get("Projeto em Computação II").setPeriodo(9);
		disciplinas.get("Optativa 7").setPeriodo(9);
		disciplinas.get("Optativa 8").setPeriodo(9);
		disciplinas.get("Optativa 9").setPeriodo(9);
		disciplinas.get("Optativa 10").setPeriodo(9);
		disciplinas.get("Optativa 11").setPeriodo(9);
	}

	private void organiza8Periodo() {
		disciplinas.get("Projeto em Computação I").setPeriodo(8);
		disciplinas.get("Métodos e Software Númericos").setPeriodo(8);
		disciplinas.get("Compiladores").setPeriodo(8);
		disciplinas.get("Inteligência Artificial I").setPeriodo(8);
		disciplinas.get("Aval. de Desempenho de Sist. Discretos").setPeriodo(8);
	}

	private void organiza7Periodo() {
		disciplinas.get("Lab. de Interconexão de Redes de Computadores").setPeriodo(7);
		disciplinas.get("Interconexão de Redes de Computadores").setPeriodo(7);
		disciplinas.get("Sistemas Operacionais").setPeriodo(7);
		disciplinas.get("Banco de Dados II").setPeriodo(7);
		disciplinas.get("Optativa 5").setPeriodo(7);
		disciplinas.get("Optativa 6").setPeriodo(7);
	}

	private void organiza6Periodo() {
		disciplinas.get("Metodologia Científica").setPeriodo(6);
		disciplinas.get("Laboratório de Engenharia de Software").setPeriodo(6);
		disciplinas.get("Redes de Computadores").setPeriodo(6);
		disciplinas.get("Sistemas de Informação II").setPeriodo(6);
		disciplinas.get("Optativa 3").setPeriodo(6);
		disciplinas.get("Optativa 4").setPeriodo(6);
	}

	private void organiza5Periodo() {
		disciplinas.get("Métodos Estatísticos").setPeriodo(5);
		disciplinas.get("Engenharia de Software I").setPeriodo(5);
		disciplinas.get("Optativa 1").setPeriodo(5);
		disciplinas.get("Optativa 2").setPeriodo(5);
	}

	private void organiza4Periodo() {
		disciplinas.get("Direito e Cidadania").setPeriodo(4);
		
	}

	private void organiza2Periodo() {
		disciplinas.get("Informática e Sociedade").setPeriodo(2);
		
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
