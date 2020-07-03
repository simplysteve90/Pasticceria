package it.dst.bean;

import java.time.LocalDateTime;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

import it.dst.model.Ordinazione;

public class BeanCliente {
	private Long idCliente;
	private Long idOrdine;
	private Ordinazione ordine;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy@HH:mm")
	@DateTimeFormat(iso = DateTimeFormat.ISO.TIME)
	private LocalDateTime dataConsegna;
	
	public LocalDateTime getDataConsegna() {
		return dataConsegna;
	}
	public void setDataConsegna(String dataConsegna) {
		this.dataConsegna = LocalDateTime.parse(dataConsegna);
	}

	public Long getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(Long idCliente) {
		this.idCliente = idCliente;
	}

	public Ordinazione getOrdine() {
		return ordine;
	}

	public void setOrdine(Ordinazione ordine) {
		this.ordine = ordine;
	}
	public Long getIdOrdine() {
		return idOrdine;
	}
	public void setIdOrdine(Long idOrdine) {
		this.idOrdine = idOrdine;
	}
	
}
