package it.dst.service;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.dst.model.Dolce;
import it.dst.repositories.DolceRepository;

@Service
@Transactional
public class DolceService {
	@Autowired
	DolceRepository dolceRepository;
	public void save(Dolce dolce) {
		dolceRepository.save(dolce);
		
	}
	public List<Dolce>listaDolci(){
		return (List<Dolce>) dolceRepository.findAll();
	}
	public Dolce get(Long id) {
		return dolceRepository.findById(id).get();
		
	}
	public void delete(Long id) {
		dolceRepository.deleteById(id);
	}


}
