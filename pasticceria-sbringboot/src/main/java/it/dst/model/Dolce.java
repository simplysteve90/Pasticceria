package it.dst.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

@Entity
public class Dolce {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@ManyToMany
	private List<Ricetta> listaRicette;
	private String nome;
	@ManyToMany
	private List<Ordinazione> listaOrdinazioni;
	private Long quantita;
	private Double costo;
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	public List<Ricetta> getListaRicette() {
		return listaRicette;
	}
	public void setListaRicette(List<Ricetta> listaRicette) {
		this.listaRicette = listaRicette;
	}
	public Long getQuantita() {
		return quantita;
	}
	public void setQuantita(Long quantita) {
		this.quantita = quantita;
	}
	public Double getCosto() {
		return costo;
	}
	public void setCosto(Double costo) {
		this.costo = costo;
	}
	public List<Ordinazione> getListaOrdinazioni() {
		return listaOrdinazioni;
	}
	public void setListaOrdinazioni(List<Ordinazione> listaOrdinazioni) {
		this.listaOrdinazioni = listaOrdinazioni;
	}
	
	
	

}
