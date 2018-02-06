package fr.webreseau.crm.dao;

import org.springframework.data.repository.CrudRepository;

import fr.webreseau.crm.model.Project;


public interface IRepositoryProject extends CrudRepository<Project, Long>{

}
