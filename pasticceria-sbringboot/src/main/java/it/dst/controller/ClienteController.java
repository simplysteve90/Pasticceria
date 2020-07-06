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

import it.dst.bean.BeanCliente;
import it.dst.model.Cliente;
import it.dst.model.Dolce;
import it.dst.model.Ordinazione;
import it.dst.service.ClienteService;
import it.dst.service.DolceService;
import it.dst.service.OrdinazioniService;

@Controller
public class ClienteController {
	@Autowired
	ClienteService clienteService;
	@Autowired
	DolceService dolceService;
	@Autowired
	OrdinazioniService ordinazioneService;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ClienteController.class);
	
	@GetMapping("/")
	public ModelAndView homepage() {
		ModelAndView model = new ModelAndView("homepage");
		return model;
	}

	@PostMapping("/profiloUtente")
	public ModelAndView profiloUtente(@RequestParam(value = "idCliente") Long idCliente) {
		return accessoUtente(clienteService.usernameUtente(idCliente));
	}

	@GetMapping("/registraUtente")
	public ModelAndView registraUtente() {
		Cliente cliente = new Cliente();
		ModelAndView model = new ModelAndView("registraUtente");
		model.addObject("cliente", cliente);
		model.addObject("listaClienti", clienteService.listaCliente());
		return model;
	}

	@PostMapping("/nuovoUtente")
	public ModelAndView nuovoUtente(Cliente cliente) {
		clienteService.save(cliente);
		LOGGER.info(String.format("L'username del cliente è: %s", cliente.getUsername()));
		return accessoUtente(cliente.getUsername());
	}

	@GetMapping("/accessoUtente")
	public ModelAndView accessoUtente(String username) {
		Cliente cliente = clienteService.getCliente(username);
		LOGGER.info(String.format("L'username del cliente è: %s", username));
		if (cliente == null) {
			return new ModelAndView("homepage");
		}
		ModelAndView view = new ModelAndView("profiloUtente");
		view.addObject("cliente", cliente);
		view.addObject("listaOrdinazioni", cliente.getListaOrdinazioni());
		return view;
	}

	@GetMapping("/nuovoOrdine/{id}")
	public ModelAndView nuovoOrdine(@PathVariable("id") Long idCliente) {
		LOGGER.info(String.format("L'id del cliente è: %s", idCliente));
		ModelAndView model = new ModelAndView("nuovoOrdine");
		Ordinazione ordine = new Ordinazione();
		ordinazioneService.save(ordine);
		BeanCliente bean = new BeanCliente();
		bean.setIdCliente(idCliente);
		bean.setOrdine(ordine);
		bean.setIdOrdine(ordine.getId());
		model.addObject("bean", bean);
		model.addObject("ordine", ordine);
		model.addObject("listaDolci", dolceService.listaDolci());
		return model;

	}

	@PostMapping("/creaOrdine")
	public ModelAndView creaOrdine(BeanCliente bean, @RequestParam(value = "idOrdine") Long idOrdine, @RequestParam(value = "idCliente") Long idCliente,
			@RequestParam(value = "idDolce", required = false) List<Long> idDolce) {
		LOGGER.info(String.format("L'id del cliente è: %s", idCliente));
		LOGGER.info(String.format("L'id dell'ordine è: %s", idOrdine));
		Ordinazione ordinazione = ordinazioneService.get(idOrdine);
		bean.setDataConsegna(bean.getDataConsegna().toString());
		ordinazione.setDataConsegna(bean.getDataConsegna());
		List<Dolce> listaDolci = new ArrayList<Dolce>();
		for (Long id : idDolce) {
			listaDolci.add(dolceService.get(id));
		}
		Cliente cliente = clienteService.get(idCliente);
		ordinazione.setListaDolci(listaDolci);
		ordinazione.setCosto(ordinazioneService.costo(ordinazione));
		ordinazione.setCliente(cliente);
		ordinazione.setSconto(ordinazioneService.sconto(ordinazione));
		cliente.getListaOrdinazioni().add(ordinazione);
		ordinazioneService.save(ordinazione);
		clienteService.save(cliente);
		return profiloUtente(idCliente);
	}

}
