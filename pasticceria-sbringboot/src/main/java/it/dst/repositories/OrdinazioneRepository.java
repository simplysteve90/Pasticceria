package it.dst.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import it.dst.model.Ordinazione;

@Repository
public interface OrdinazioneRepository extends CrudRepository<Ordinazione, Long>{

}
