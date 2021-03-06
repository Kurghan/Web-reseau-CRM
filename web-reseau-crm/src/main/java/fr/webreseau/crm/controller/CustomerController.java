package fr.webreseau.crm.controller;

import java.util.ArrayList;

import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import fr.webreseau.crm.model.Customer;
import fr.webreseau.crm.model.Project;
import fr.webreseau.crm.service.IServiceCustomer;
import fr.webreseau.crm.service.IServiceProject;
import fr.webreseau.crm.service.IServiceSessionUser;

@Controller
public class CustomerController {

	@Autowired
	private IServiceCustomer service;
	
	@Autowired
	private IServiceProject serviceProject;
	
	@Autowired
	private IServiceSessionUser serviceSessionUser;
	
	
	//affichage de la page d'ajout client
	@GetMapping("/addCustomer")
	public String pageAddCustomer(Model model ) {
		serviceSessionUser.getSessionUser(model);
		return "customers/addCustomer";

	}

	//affichage de la page d'un client
	@RequestMapping("/viewCustomer")
	public String pageViewCustomer(@RequestParam(value = "ID") Long ID, Model model) {
		serviceSessionUser.getSessionUser(model);
		ArrayList<Customer> listCustomers = service.readCustomers();
		ArrayList<Project> listProject = serviceProject.readProject();
		ArrayList<Project> listCustomerProjects = new ArrayList<Project>() ;
		for (Customer c : listCustomers) {
			if (ID == c.getID()) {
				model.addAttribute("customer", c);
				}
		}
		for(Project p : listProject) {
			if(p.getCustomer().getID()==ID) {
				listCustomerProjects.add(p);
				model.addAttribute("listCustomerProjects", listCustomerProjects);

			}
		}
		return "customers/viewCustomer";
	}

	//affichage de la liste des clients
	@GetMapping("/customers")
	public String pageCustomer(Model model) {
		serviceSessionUser.getSessionUser(model);
		ArrayList<Customer> listCustomers = service.readCustomers();
		model.addAttribute("listCustomers", listCustomers);
		return "customers/customers";

	}

	/* Ajout d'un cleint */
	@PostMapping("/customerAdd")
	public String customerAdd(@Valid Customer customer) {
		customer.setRole("USER");
		service.creatCustomer(customer);

		return "redirect:/customers";
	}

	//affichage de la page edition d'un client
	@RequestMapping("/editCustomer")
	public String editCustomer(@RequestParam(value = "ID") Long ID, Model model) {
		serviceSessionUser.getSessionUser(model);
		ArrayList<Customer> listCustomers = service.readCustomers();
		for (Customer c : listCustomers) {
			if (ID == c.getID()) {
				model.addAttribute(c);
			}
		}
		return "customers/modifyCustomer";

	}

	//enregistrement de l'edition d'un client
	@RequestMapping("/customerModify")
	public String modifyCustomer(@Valid Customer customer,Model model) {
		serviceSessionUser.getSessionUser(model);
		customer.setRole("USER");
		service.modifyCustomer(customer);
		return "customers/viewCustomer";
	}

	
	//recuperation de l'id du client pour creer un projet lié au client
	@GetMapping("/addProject{id}")
	public String addProjectCustomer(@RequestParam("id") Long ID, Model model) {
		serviceSessionUser.getSessionUser(model);
		ArrayList<Customer> listCustomers = service.readCustomers();
		for (Customer c : listCustomers) {
			if (ID == c.getID()) {
				model.addAttribute("customer",c);
			}
		}
		return "projects/addProject";
	}

}
