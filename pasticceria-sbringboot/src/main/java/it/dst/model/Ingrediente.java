package it.dst.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

@Entity
public class Ingrediente {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String nome;
	private Double costo;
	private boolean presente;
    @ManyToMany
    private List<Ricetta> listaRicette;
    
	public List<Ricetta> getListaRicette() {
		return listaRicette;
	}

	public void setListaRicette(List<Ricetta> listaRicette) {
		this.listaRicette = listaRicette;
	}


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

	public Double getCosto() {
		return costo;
	}

	public void setCosto(Double costo) {
		this.costo = costo;
	}

	
	public boolean isPresente() {
		return presente;
	}

	public void setPresente(boolean presente) {
		this.presente = presente;
	}

	@Override
	public String toString() {
		return nome ;
	}

	

	
	

	/*
	 * @Override public String toString() { return quantita +" "+ nome + ", "; }
	 */
	
	
	

}
