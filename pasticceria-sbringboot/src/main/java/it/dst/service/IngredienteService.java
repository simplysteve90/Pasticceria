package it.dst.service;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.dst.model.Ingrediente;
import it.dst.repositories.IngredienteRepository;

@Service
@Transactional
public class IngredienteService {
	@Autowired
	IngredienteRepository ingredienteRepository;
	public void save(Ingrediente ingrediente) {
		ingredienteRepository.save(ingrediente);
	}
	public List<Ingrediente>listaIngredienti(){
		return (List<Ingrediente>) ingredienteRepository.findAll();
	}
	public Ingrediente get(Long id) {
		return ingredienteRepository.findById(id).get();
		
	}
	public void delete(Long id) {
		ingredienteRepository.deleteById(id);
	}


}
