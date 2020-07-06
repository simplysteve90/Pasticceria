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
		double sconto =0;
		if(ordinazione.getCosto() >= 15 && ordinazione.getCosto() < 30) {
			System.out.println("siamo nel primo if");
			
			sconto=ordinazione.getCosto()*0.9;
			System.out.println(sconto);
		}else if(ordinazione.getCosto() >= 30){
			System.out.println("||||||||||||||||||");
			
		sconto=ordinazione.getCosto()*0.8;
		System.out.println(sconto);

		}
		
		return sconto;
	}

}
