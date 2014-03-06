package models;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

import play.data.validation.Constraints;
import play.db.ebean.Model;

import com.google.common.base.Objects;
/**
 * Essa classe representa uma disciplina.
 */
@Entity
public class Disciplina extends Model implements Comparable<Disciplina>{

	private static final long serialVersionUID = 1L;

	@Id
	Long id;
	
	@Constraints.Required
	@Column(unique = true, nullable=false)
	private String nome;
	
	private int creditos;

	@ManyToMany(cascade=CascadeType.ALL, fetch=FetchType.EAGER)
    @JoinTable(name = "disciplina_requisito", 
    joinColumns = {@JoinColumn (name = "fk_disciplina")}, inverseJoinColumns = {@JoinColumn(name = "fk_requisito")})
	private List<Disciplina> preRequisitos;
	
	private int dificuldade;
	
	@Column(name="periodo_original")
	private int periodo;

	public Disciplina() {
		setPreRequisitos(new ArrayList<Disciplina>());
	}

	public Disciplina(String nome, int dificuldade) {
		this.setNome(nome);	
		this.creditos = 4;
		this.dificuldade = dificuldade;
		setPreRequisitos(new ArrayList<Disciplina>());
	}

	public Disciplina(String nome, int dificuldade, int creditos) {
		this(nome, dificuldade);
		this.creditos = creditos;
	}
	
	/**
	 * Retorna verdadeiro caso a disciplina seja pre-requisito.
	 */
	public boolean isPreRequisito(Disciplina d) {
		return this.getPreRequisitos().contains(d);
	}

	public void addPreRequisito(Disciplina... d) {
		Disciplina[] lista = d;
		for (Disciplina disciplina : lista) {
			getPreRequisitos().add(disciplina);
		}
	}
	
	public void setCreditos(int creditos) {
		this.creditos = creditos;
	}

	public int getCreditos() {
		return this.creditos;
	}

	public String getNome() {
		return this.nome;
	}

	public void setDificuldade(int dificuldade) {
		this.dificuldade = dificuldade;
	}

	public int getDificuldade() {
		return dificuldade;
	}

	public List<Disciplina> getPreRequisitos() {
		return preRequisitos;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public void setPreRequisitos(List<Disciplina> preRequisitos) {
		this.preRequisitos = preRequisitos;
	}

	public int getPeriodo(){
		return periodo;
	}
	
	public void setPeriodo(int periodo){
		this.periodo = periodo;
	}
	
	public static Finder<Long,Disciplina> find = new Finder<Long,Disciplina>(
		    Long.class, Disciplina.class
	); 
	
	public static void create(Disciplina c) {
		c.save();
	}

	public static void delete(Long id) {
		find.ref(id).delete();
	}
	
	public static void atualizar(Long id) {
		Disciplina p = find.ref(id);
		p.update();
	}
	
	@Override
	public int compareTo(Disciplina c) {
		return getNome().compareTo(c.getNome());
	}

	@Override
	public int hashCode() {
		return Objects.hashCode(getNome(), creditos);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Disciplina other = (Disciplina) obj;
		return Objects.equal(this.getCreditos(), other.getCreditos())
				&& Objects.equal(this.getNome(), other.getNome());
	}

	@Override
	public String toString() {
		return "Disciplina [id=" + id + ", nome=" + nome + ", periodo=" + periodo
				+ "]";
	}
}