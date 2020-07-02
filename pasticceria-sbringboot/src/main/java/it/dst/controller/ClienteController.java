package it.dst.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

import it.dst.model.Cliente;
import it.dst.model.Ordinazione;
import it.dst.repositories.ClienteRepository;
import it.dst.service.ClienteService;
import it.dst.service.DolceService;

@Controller
public class ClienteController {
	@Autowired
	ClienteService clienteService;
	
	@Autowired
	DolceService dolceService;
	
	@GetMapping("/")
	public ModelAndView homepage(Cliente cliente){
		ModelAndView model= new ModelAndView("homepage");
		return model;
	}
	
	@GetMapping("/registraUtente")	
	public ModelAndView registraUtente() {
		Cliente cliente = new Cliente();
		ModelAndView model= new ModelAndView("registraUtente");
		model.addObject("cliente", cliente);
		model.addObject("listaClienti", clienteService.listaCliente());
			
		return model;
	}
	
	@GetMapping("/nuovoUtente")
	public ModelAndView nuovoUtente(Cliente cliente) {
		clienteService.save(cliente);
			
		return accessoUtente(cliente.getUsername());
	}
	
	
	@GetMapping("/accessoUtente")	
	public ModelAndView accessoUtente(String username) {
		if(clienteService.getCliente(username) == null) {
			return new ModelAndView("homepage");
		}
		return new ModelAndView("profiloUtente", "cliente", clienteService.getCliente(username));
	}
	
	
	@GetMapping("/nuovoOrdine/{id}")	
	public ModelAndView nuovoOrdine(@PathVariable("id") Long idCliente) {
		ModelAndView model= new ModelAndView("nuovoOrdine");
		Ordinazione ordine = new Ordinazione();
		model.addObject("listaDolci", dolceService.listaDolci());
		model.addObject("ordine", ordine);
		model.addObject("idCliente", idCliente);
		return model;
		
	}
	
	

}
