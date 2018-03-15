package fr.webreseau.crm.service;

import java.util.ArrayList;

import fr.webreseau.crm.model.Customer;

public interface IServiceCustomer {

	public ArrayList<Customer> readCustomers();
	
	public Customer readOneCustomer(Long ID);
	
	public void creatCustomer(Customer customer);
	
	public void modifyCustomer(Customer customer);
	
	public void deleteCustomer (Long ID);
	

}
