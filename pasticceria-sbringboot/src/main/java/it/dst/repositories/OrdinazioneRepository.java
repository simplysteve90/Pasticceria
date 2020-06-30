package it.dst.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import it.dst.model.Ordinazioni;

@Repository
public interface OrdinazioneRepository extends CrudRepository<Ordinazioni, Long>{

}
