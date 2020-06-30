package it.dst.service;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.dst.model.Ricetta;
import it.dst.repositories.RicettaRepository;

@Service
@Transactional
public class RicettaService {
@Autowired
RicettaRepository ricettaRepository;
public void save(Ricetta ricetta) {
	ricettaRepository.save(ricetta);
	
}
public List<Ricetta>listaRicette(){
	return (List<Ricetta>) ricettaRepository.findAll();
}
public Ricetta get(Long id) {
	return ricettaRepository.findById(id).get();
	
}
public void delete(Long id) {
	ricettaRepository.deleteById(id);
}

}

