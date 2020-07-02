package it.dst.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

@Entity
public class Ricetta {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String nome;
	private String tempo;
	private Long difficolta;
	@ManyToMany
	private List<Ingrediente> listaIngredienti;
	private String descrizione;
	private Double costo;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getTempo() {
		return tempo;
	}

	public void setTempo(String tempo) {
		this.tempo = tempo;
	}

	public Long getDifficolta() {
		return difficolta;
	}

	public void setDifficolta(Long difficolta) {
		this.difficolta = difficolta;
	}

	public List<Ingrediente> getListaIngredienti() {
		return listaIngredienti;
	}

	public void setListaIngredienti(List<Ingrediente> listaIngredienti) {
		this.listaIngredienti = listaIngredienti;
	}

	public String getDescrizione() {
		return descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	public Double getCosto() {
		return costo;
	}

	public void setCosto(Double costo) {
		this.costo = costo;
	}

	@Override
	public String toString() {
		return "Ricetta [id=" + id + ", nome=" + nome + ", tempo=" + tempo + ", difficolta=" + difficolta
				+ ", listaIngredienti=" + listaIngredienti + ", descrizione=" + descrizione + ", costo=" + costo + "]";
	}

}
