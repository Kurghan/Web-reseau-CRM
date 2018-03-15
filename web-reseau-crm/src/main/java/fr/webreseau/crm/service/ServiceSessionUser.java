package fr.webreseau.crm.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import fr.webreseau.crm.model.Customer;
import fr.webreseau.crm.model.Employe;


@Service
public class ServiceSessionUser implements IServiceSessionUser{
	
	@Autowired
	private IServiceEmploye serviceEmploye;
	
	@Autowired
	private IServiceCustomer serviceCustomer;
	
	
	public void getSessionUser(Model model) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String mail = authentication.getName();
		ArrayList<Employe> listEmploye = serviceEmploye.readEmploye();
		ArrayList<Customer> listCustomers = serviceCustomer.readCustomers();
		for(Employe e : listEmploye) {
			if(e.getMail().equals(mail)) {
				model.addAttribute("sessionUser" , e);
			}
		}
		
		for(Customer c : listCustomers) {
			if(c.getMail().equals(mail)) {
				model.addAttribute("sessionUser", c);
			}
		}
	}

}
