package it.dst.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Cliente {
	@Id
	@GeneratedValue(strategy =GenerationType.AUTO)
	private Long id;
	@Column(unique=true)
	private String username;
	
	private String nome;
	private String Cognome;
	@OneToMany
	private List<Ordinazione> listaOrdinazioni;
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
	public String getCognome() {
		return Cognome;
	}
	public void setCognome(String cognome) {
		Cognome = cognome;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public List<Ordinazione> getListaOrdinazioni() {
		return listaOrdinazioni;
	}
	public void setListaOrdinazioni(List<Ordinazione> listaOrdinazioni) {
		this.listaOrdinazioni = listaOrdinazioni;
	}
	@Override
	public String toString() {
		return  username ;
	}
	
	
	

}
