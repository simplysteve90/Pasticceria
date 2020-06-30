package it.dst.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import it.dst.model.Ricetta;

@Repository
public interface RicettaRepository extends CrudRepository<Ricetta, Long> {

}
