package it.dst.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import it.dst.model.Cliente;
@Repository
public interface ClienteRepository extends CrudRepository<Cliente, Long>{
	Cliente findByUsername(String username);
}
