package fr.webreseau.crm.dao;

import org.springframework.data.repository.CrudRepository;

import fr.webreseau.crm.model.Message;

public interface IRepositoryMessage extends CrudRepository<Message , Long>{

}
