package it.dst.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.dst.model.Cliente;
import it.dst.repositories.ClienteRepository;

@Service
@Transactional
public class ClienteService {
	@Autowired
	ClienteRepository clienteRepository;
	public void save(Cliente cliente) {
		clienteRepository.save(cliente);
		
	}
	public List<Cliente>listaCliente(){
		return (List<Cliente>) clienteRepository.findAll();
	}
	public Cliente get(Long id) {
		return clienteRepository.findById(id).get();
		
	}
	public void delete(Long id) {
		clienteRepository.deleteById(id);
	}

}
