package models;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

import models.exceptions.LimiteUltrapassadoException;
import play.db.ebean.Model;

/**
 * Essa classe representa um período
 */
@Entity
public class Periodo extends Model{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	Long id;
	
	private ValidadorDeCreditos validador;
	
	private int numero;

	@ManyToMany(cascade=CascadeType.ALL, fetch=FetchType.EAGER)
    @JoinTable(name = "periodo_disciplina", 
    joinColumns = {@JoinColumn (name = "fk_periodo")}, inverseJoinColumns = {@JoinColumn(name = "fk_disciplina")})
	private List<Disciplina> disciplinas;

	public Periodo (){
		this(1,28);
	}
	
	public Periodo (int numeroDoPeriodo, int maximoCreditos) {
		this.numero = numeroDoPeriodo;
		disciplinas = new ArrayList<Disciplina>();
		validador = new ValidadorMax(maximoCreditos);
	}
	
	public Long getId(){
		return id;
	}	

	public static Finder<Long,Periodo> find = new Finder<Long,Periodo>(
		    Long.class, Periodo.class
	);
	
	public static void create(Periodo p) {
		p.save();
	}

	public static void delete(Long id) {
		find.ref(id).delete();
	}
	
	public static void atualizar(Long id) {
		Periodo p = find.ref(id);
		p.update();
	}

	public void setEValido (ValidadorDeCreditos validador){
		this.validador = validador;
	}
	
	public void adicionarDisciplina(Disciplina disciplina) throws LimiteUltrapassadoException{
		if (!validador.eValido(getCreditos(), disciplina.getCreditos())) {
			throw new LimiteUltrapassadoException();
		}
		disciplinas.add(disciplina);
	}

	public void removerDisciplina(Disciplina disciplina) {
		disciplinas.remove(disciplina);
	}

	public int getDificuldadeTotal() {
		int difi = 0;
		for (Disciplina d : getDisciplinas()) {
			difi += d.getDificuldade();
		}
		return difi;
	}

	/**
	 * Calcula o total de Créditos do Periodo.
	 */
	public int getCreditos() {
		int sum = 0;
		for (Disciplina d : getDisciplinas()) {
			sum += d.getCreditos();
		}
		return sum;
	}

	public List<Disciplina> getDisciplinas() {
		Collections.sort(disciplinas);
		return disciplinas;
	}
	
	public void setDisciplinas(List<Disciplina> disciplinas){
		this.disciplinas = disciplinas;
	}
	
	public int getNumero() {
		return numero;
	}
	
	public void setNumero(int numero){
		this.numero = numero;
	}
	
	public List<Disciplina> getListaDisciplinas(){
		return disciplinas;
	}

	public Disciplina getDisciplina(String disciplina) {
		for(Disciplina d: disciplinas){
			if(d.getNome().equals(disciplina)){
				return d;
			}
		}
		return null;
	}

	@Override
	public String toString() {
		return "Periodo [id=" + id + ", numero=" + numero + ", disciplinas="
				+ disciplinas + "]";
	}
}