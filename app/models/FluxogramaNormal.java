package models;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FluxogramaNormal implements Fluxograma {

	private Map<String, Disciplina> disciplinas;
	private int numerDePeriodos;
	
	public FluxogramaNormal() {
		disciplinas = atualizaMapaCadeira(Disciplina.find.all());
		organizaDisciplinas();
		numerDePeriodos = 8;
	}

	public Map<String, Disciplina> atualizaMapaCadeira(List<Disciplina> disc){
		Map<String, Disciplina> mapa = new HashMap<String, Disciplina>();
		for(Disciplina d: disc){
			mapa.put(d.getNome(), d);
		}
		return mapa;
	}
	
	private void organizaDisciplinas() {
		for (Disciplina d: disciplinas.values()) {
			if (d.getPeriodo() == -1) {
				disciplinas.remove(d);
			}
		}
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
