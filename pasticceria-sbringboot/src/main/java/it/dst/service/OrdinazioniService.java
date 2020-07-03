package it.dst.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.dst.model.Dolce;
import it.dst.model.Ordinazione;
import it.dst.repositories.OrdinazioneRepository;

@Service
@Transactional
public class OrdinazioniService {
	@Autowired
	OrdinazioneRepository ordinazioniRepository;

	public void save(Ordinazione ordinazioni) {
		ordinazioniRepository.save(ordinazioni);

	}

	public List<Ordinazione> listaOrdinazioni() {
		return (List<Ordinazione>) ordinazioniRepository.findAll();
	}

	public Ordinazione get(Long id) {
		return ordinazioniRepository.findById(id).get();

	}

	public void delete(Long id) {
		ordinazioniRepository.deleteById(id);
	}

	public double costo(Ordinazione ordinazione) {
		double costo = 0;
		for (Dolce dolce : ordinazione.getListaDolci()) {
			costo += dolce.getCosto();
		}return costo;
	}
	
	public double sconto(Ordinazione ordinazione) {
		double costo = ordinazione.getCosto();
		if(costo >= 15 || costo < 30) {
			ordinazione.setCosto(costo * 0.9);
			return ordinazione.getCosto();
		}else if(costo >= 30){
			ordinazione.setCosto(costo * 0.8);
			return ordinazione.getCosto();
		}return ordinazione.getCosto();
	}

}
