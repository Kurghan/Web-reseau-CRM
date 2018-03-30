package fr.webreseau.crm.dao;

import org.springframework.data.repository.CrudRepository;
import fr.webreseau.crm.model.Person;

public interface IRepositoryPerson<P extends Person> extends CrudRepository<P, Long> {

}
