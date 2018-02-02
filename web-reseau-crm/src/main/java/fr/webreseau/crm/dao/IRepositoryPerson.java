package fr.webreseau.crm.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.NoRepositoryBean;
import fr.webreseau.crm.model.Person;

@NoRepositoryBean
public interface IRepositoryPerson<P extends Person> extends CrudRepository<P, Long> {

}
