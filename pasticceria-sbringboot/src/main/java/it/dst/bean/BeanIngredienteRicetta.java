package it.dst.bean;

import java.util.List;

import it.dst.model.Ingrediente;
import it.dst.model.Ricetta;

public class BeanIngredienteRicetta {
	private Long quantita;
	private List<Ingrediente> ingrediente;


	public Long getIdRicetta() {
		return quantita;
	}

	public void setIdRicetta(Long idRicetta) {
		this.quantita = idRicetta;
	}

	public List<Ingrediente> getIngrediente() {
		return ingrediente;
	}

	public void setIngrediente(List<Ingrediente> ingrediente) {
		this.ingrediente = ingrediente;
	}


}
