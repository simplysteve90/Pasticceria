package it.dst.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import it.dst.model.Dolce;
import it.dst.model.Ingrediente;
import it.dst.model.Ricetta;
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
		System.out.println(nuovoIngrediente);
		view.addObject("listaIngredienti", ingredienteRepository.listaIngredienti());
		view.addObject("ingrediente", ingrediente);
		return view;
	}

	@GetMapping("/creaRicetta")
	public ModelAndView creaRicetta() {
		Ricetta ricetta = new Ricetta();
		ModelAndView view = new ModelAndView("creaRicetta");
		view.addObject("listaRicette", ricettaRepository.listaRicette());
		view.addObject("listaIngredienti", ingredienteRepository.listaIngredienti());
		view.addObject("ricetta", ricetta);
		return view;
	}


	@PostMapping("nuovaRicetta")
	public ModelAndView nuovaRicetta(Ricetta ricetta, @RequestParam(value = "ing" , required = false) List<Long> ing, @RequestParam(value = "quantita" , required = false) List<Long> quantita) {
		System.out.println(ing);
		System.out.println(quantita);
		List<Ingrediente> lista = new ArrayList<Ingrediente>();
		for (Long id : ing) {
			for (Long qta : quantita) {				
				Ingrediente ingrediente = ingredienteRepository.get(id);
				ingrediente.setQuantita(qta);
				lista.add(ingrediente);
			}
		}
		ricetta.setListaIngredienti(lista);
		ricettaRepository.save(ricetta);
//		Long quantita = ingrediente.getQuantita();
//		ingrediente.setQuantita(quantita - ingrediente.getQuantita());
		ModelAndView view = new ModelAndView("aggiungiIngredientiRicetta");
		view.addObject("listaIngredienti", ingredienteRepository.listaIngredienti());
		view.addObject("ricetta", ricetta);
		return view;
	}

	@GetMapping("/creaDolce")
	public ModelAndView creaDolce() {
		Dolce dolce = new Dolce();
		ModelAndView view = new ModelAndView("creaDolce");
		view.addObject("dolce", dolce);
		view.addObject("listaDolci", dolceRepository.listaDolci());
		return view;

	}

}
