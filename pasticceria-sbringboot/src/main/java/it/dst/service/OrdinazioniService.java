package it.dst.service;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

}
