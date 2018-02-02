package fr.webreseau.crm.dao;

import org.springframework.transaction.annotation.Transactional;

import fr.webreseau.crm.model.Customer;

@Transactional
public interface IRepositoryCustomer extends IRepositoryPerson<Customer> {

}
