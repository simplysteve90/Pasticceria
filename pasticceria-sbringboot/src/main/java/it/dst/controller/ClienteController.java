package it.dst.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import it.dst.model.Cliente;
import it.dst.service.ClienteService;

@Controller
public class ClienteController {
	@Autowired
	ClienteService clienteService;
	
	@GetMapping("/")
	public ModelAndView homepage(Cliente cliente){
		ModelAndView model= new ModelAndView("homepage");
		return model;
	}
	@GetMapping("/accessoUtente")	
	public ModelAndView accessoUtente(String username) {
		if(clienteService.getCliente(username) == null) {
			return new ModelAndView("homepage");
		}
		return new ModelAndView("profiloUtente", "cliente", clienteService.getCliente(username));
	}
	
	
	
	
	

}
