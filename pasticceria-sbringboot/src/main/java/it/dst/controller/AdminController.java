package it.dst.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import it.dst.model.Dolce;
import it.dst.model.Ingrediente;
import it.dst.model.Ricetta;
import it.dst.repositories.DolceRepository;
import it.dst.repositories.IngredienteRepository;
import it.dst.repositories.OrdinazioneRepository;
import it.dst.repositories.RicettaRepository;
import it.dst.service.DolceService;
import it.dst.service.IngredienteService;
import it.dst.service.OrdinazioniService;
import it.dst.service.RicettaService;

@Controller
public class AdminController {
	@Autowired
	DolceService dolceRepository;
	@Autowired
	IngredienteService ingredienteRepository;
	@Autowired
	RicettaService ricettaRepository;
	@Autowired
	OrdinazioniService ordinazioneRepository;

	@GetMapping("/accessoAdmin")
	public ModelAndView accessoAdmin() {
		ModelAndView view = new ModelAndView("accessoAdmin");
		view.addObject("listaOrdinazioni", ordinazioneRepository.listaOrdinazioni());
		return view;
	}

	@GetMapping("/aggiuntaIngrediente")
	public ModelAndView aggiuntaIngrediente() {
		Ingrediente ingrediente = new Ingrediente();
		ModelAndView view = new ModelAndView("aggiuntaIngrediente");
		view.addObject("listaIngredienti", ingredienteRepository.listaIngredienti());
		view.addObject("ingrediente", ingrediente);
		return view;
	}

	@PostMapping("/nuovoIngrediente")
	public ModelAndView nuovoIngrediente(Ingrediente nuovoIngrediente) {
		Ingrediente ingrediente = new Ingrediente();
		ModelAndView view = new ModelAndView("aggiuntaIngrediente");
		ingredienteRepository.save(nuovoIngrediente);
		view.addObject("listaIngredienti", ingredienteRepository.listaIngredienti());
		view.addObject("ingrediente", ingrediente);
		return view;
	}

	@GetMapping("/creaRicetta")
	public ModelAndView creaRicetta() {
		Ricetta ricetta = new Ricetta();
		ModelAndView view = new ModelAndView("creaRicetta");
		view.addObject("listaRicette", ricettaRepository.listaRicette());
		view.addObject("ricetta", ricetta);
		return view;
	}

	@PostMapping("nuovaRicetta")
	public ModelAndView nuovaRicetta(Ricetta ricetta) {
		ricettaRepository.save(ricetta);
		ModelAndView view = new ModelAndView("aggiungiIngredientiRicetta");
		view.addObject("listaIngredienti", ingredienteRepository.listaIngredienti());
		view.addObject("ricetta", ricetta);
		return view;
	}

	@GetMapping("/aggiungiIngredienteRicetta/ingrediente/{ingredienteId}/ricetta/{ricettaId}")
	public ModelAndView aggiungiIngredienteRicetta(@PathVariable("ingredienteId") long ingredienteId,
			@PathVariable("ricettaId") long ricettaId) {
		Ricetta ricetta = ricettaRepository.get(ricettaId);
		ricetta.getListaIngredienti().add(ingredienteRepository.get(ingredienteId));
		ricettaRepository.save(ricetta);
		ModelAndView view = new ModelAndView("aggiungiIngredientiRicetta");
		view.addObject("listaRicette", ricettaRepository.listaRicette());
		view.addObject("listaIngredienti", ingredienteRepository.listaIngredienti());
		view.addObject("listaIngredientiRicetta", ricetta.getListaIngredienti());
		view.addObject("ricetta", ricetta);
		System.out.println("Ricetta" + ricetta);
		return view;
	}

	@PostMapping("/salvaRicetta")
	public ModelAndView salvaRicetta(Ricetta idricetta) {
	Ricetta ricetta = ricettaRepository.get(idricetta.getId());
	ricetta.setCosto(ricettaRepository.costoRictta(ricetta));
		ricettaRepository.save(ricetta);
		
		return creaRicetta();
	}
	
	
	
	  @GetMapping("/creaDolce") public ModelAndView creaDolce() {
	  Dolce dolce = new Dolce();
	  ModelAndView view = new ModelAndView("creaDolce");
	  view.addObject("dolce",dolce);
	  view.addObject("listaDolci",dolceRepository.listaDolci());
	  return view;
	  
	  }
	 
	
}
