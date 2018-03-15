package fr.webreseau.crm.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import fr.webreseau.crm.dao.IRepositoryCustomer;
import fr.webreseau.crm.model.Customer;


@Service
public class ServiceCustomer implements IServiceCustomer{
	
	@Autowired
	private IRepositoryCustomer dao;

	@Override
	public ArrayList<Customer> readCustomers() {
		return (ArrayList<Customer>) dao.findAll();
	}

	@Override
	public void creatCustomer(Customer customer) {
		dao.save(customer);
		
	}

	@Override
	public void modifyCustomer(Customer customer) {
		dao.save(customer);
		
	}

	@Override
	public void deleteCustomer(Long ID) {
		dao.delete(ID);
		
	}

	@Override
	public Customer readOneCustomer(Long ID) {
		return dao.findOne(ID);
		
	}

}
