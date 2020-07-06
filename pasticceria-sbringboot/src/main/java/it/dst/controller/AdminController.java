package it.dst.controller;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import it.dst.model.Dolce;
import it.dst.model.Ingrediente;
import it.dst.model.Ordinazione;
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
	
	private static final Logger LOGGER = LoggerFactory.getLogger(AdminController.class);
	
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
		LOGGER.info(String.format("Il nome dell'ingrediente è: %s", nuovoIngrediente.getNome()));
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

	@PostMapping("/nuovaRicetta")
	public ModelAndView nuovaRicetta(Ricetta ricetta, @RequestParam(value = "ing", required = false) List<Long> ing) {
		List<Ingrediente> lista = new ArrayList<Ingrediente>();
		for (long id : ing) {
			lista.add(ingredienteRepository.get(id));
		}
		ricetta.setListaIngredienti(lista);
		ricetta.setCosto(ricettaRepository.costoRicetta(ricetta));

		ricettaRepository.save(ricetta);
		return creaRicetta();
	}

	@PostMapping("/nuovoDolce")
	public ModelAndView nuovoDolce(Dolce dolce,
			@RequestParam(value = "idRicetta", required = false) List<Long> listaRicette) {
		List<Ricetta> lista = new ArrayList<Ricetta>();
		for (long id : listaRicette) {
			lista.add(ricettaRepository.get(id));
		}
		dolce.setListaRicette(lista);
		if (!dolceRepository.controlloIngredienti(dolce)) {
			
			return creaDolce(true);
		}
		dolce.setCosto(dolceRepository.costoDolce(dolce));
		dolceRepository.save(dolce);
		return creaDolce(false);
	}

	@GetMapping("/creaDolce")
	public ModelAndView creaDolce(boolean flag) {
		Dolce dolce = new Dolce();
		ModelAndView view = new ModelAndView("creaDolce");
		view.addObject("listaRicette", ricettaRepository.listaRicette());
		view.addObject("dolce", dolce);
		view.addObject("listaDolci", dolceRepository.listaDolci());
		if(flag) {
			view.addObject("messaggio", "non ci sono abbastanza ingredienti");	
		}
		return view;
	}
	
	@GetMapping("/chiudiOrdine/{id}")
	public ModelAndView chiudiOrdine(@PathVariable("id") Long idOrdine) {
		Ordinazione ordine = ordinazioneRepository.get(idOrdine);
		ordine.setStato(true);
		ordinazioneRepository.save(ordine);
		return accessoAdmin();
	}

}
