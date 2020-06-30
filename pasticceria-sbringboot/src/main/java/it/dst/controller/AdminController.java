package it.dst.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import it.dst.model.Ingrediente;
import it.dst.model.Ricetta;
import it.dst.repositories.DolceRepository;
import it.dst.repositories.IngredienteRepository;
import it.dst.repositories.OrdinazioneRepository;
import it.dst.repositories.RicettaRepository;

@Controller
public class AdminController {
	@Autowired
	DolceRepository dolceRepository;
	@Autowired
	IngredienteRepository ingredienteRepository;
	@Autowired
	RicettaRepository ricettaRepository;
	@Autowired
	OrdinazioneRepository ordinazioneRepository;

	@GetMapping("/accessoAdmin")
	public ModelAndView accessoAdmin() {
		ModelAndView view = new ModelAndView("accessoAdmin");
		view.addObject("listaOrdinazioni", ordinazioneRepository.findAll());
		return view;
	}

	@GetMapping("/aggiuntaIngrediente")
	public ModelAndView aggiuntaIngrediente() {
		Ingrediente ingrediente = new Ingrediente();
		ModelAndView view = new ModelAndView("aggiuntaIngrediente");
		view.addObject("listaIngredienti", ingredienteRepository.findAll());
		view.addObject("ingrediente", ingrediente);
		return view;
	}

	@PostMapping("/nuovoIngrediente")
	public ModelAndView nuovoIngrediente(Ingrediente nuovoIngrediente) {
		Ingrediente ingrediente = new Ingrediente();
		ModelAndView view = new ModelAndView("aggiuntaIngrediente");
		ingredienteRepository.save(nuovoIngrediente);
		view.addObject("listaIngredienti", ingredienteRepository.findAll());
		view.addObject("ingrediente", ingrediente);
		return view;
	}

	@GetMapping("/creaRicetta")
	public ModelAndView creaRicetta() {
		Ricetta ricetta = new Ricetta();
		ModelAndView view = new ModelAndView("creaRicetta");
		view.addObject("listaRicette", ricettaRepository.findAll());
		view.addObject("ricetta", ricetta);
		return view;
	}

	@PostMapping("nuovaRicetta")
	public ModelAndView nuovaRicetta(Ricetta ricetta) {
		ricettaRepository.save(ricetta);
		ModelAndView view = new ModelAndView("aggiungiIngredientiRicetta");
		view.addObject("listaIngredienti", ingredienteRepository.findAll());
		view.addObject("ricetta", ricetta);
		return view;
	}

	@GetMapping("/aggiungiIngredienteRicetta/ingrediente/{ingredienteId}/ricetta/{ricettaId}")
	public ModelAndView aggiungiIngredienteRicetta(@PathVariable("ingredienteId") long ingredienteId,
			@PathVariable("ricettaId") long ricettaId) {
		Ricetta ricetta = ricettaRepository.findById(ricettaId).get();
		ricetta.getListaIngredienti().add(ingredienteRepository.findById(ingredienteId).get());
		ricettaRepository.save(ricetta);
		ModelAndView view = new ModelAndView("aggiungiIngredientiRicetta");
		view.addObject("listaRicette", ricettaRepository.findAll());
		view.addObject("listaIngredienti", ingredienteRepository.findAll());
		view.addObject("ricetta", ricetta);
		System.out.println("Ricetta" + ricetta);
		return view;
	}

	@PostMapping("/salvaRicetta")
	public ModelAndView salvaRicetta(Ricetta ricetta) {
		ricettaRepository.save(ricettaRepository.findById(ricetta.getId()).get());
		return creaRicetta();
	}
	
	
}
