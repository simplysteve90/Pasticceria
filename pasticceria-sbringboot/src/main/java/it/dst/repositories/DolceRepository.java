package it.dst.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import it.dst.model.Dolce;

@Repository
public interface DolceRepository extends CrudRepository<Dolce, Long>{

}
