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
import fr.webreseau.crm.service.IServiceCustomer;

@Controller
public class CustomerController {

	@Autowired
	private IServiceCustomer service;

	/* affiche la page d'accueil */
	@GetMapping("/")
	public String pageWelcome() {
		return "welcome";

	}

	@GetMapping("/addCustomer")
	public String pageAddCustomer() {
		return "customers/addCustomer";

	}

	@RequestMapping("/viewCustomer")
	public String pageViewCustomer(@RequestParam(value = "ID") Long ID, Model model) {
		ArrayList<Customer> listCustomers = service.readCustomers();
		for (Customer c : listCustomers) {
			if (ID == c.getID()) {
				model.addAttribute("customer", c);
			}
		}
		return "customers/viewCustomer";

	}

	@GetMapping("/customers")
	public String pageCustomer(Model model) {
		ArrayList<Customer> listCustomers = service.readCustomers();
		model.addAttribute("listCustomers", listCustomers);
		return "customers/customers";

	}

	/* Ajout d'un Customer */
	@PostMapping("/customerAdd")
	public String customerAdd(@Valid Customer customer) {
		service.creatCustomer(customer);

		return "redirect:/customers";
	}

	@RequestMapping("/editCustomer")
	public String editCustomer(@RequestParam(value = "ID") Long ID, Model model) {
		ArrayList<Customer> listCustomers = service.readCustomers();
		for (Customer c : listCustomers) {
			if (ID == c.getID()) {
				model.addAttribute(c);
			}
		}
		return "customers/modifyCustomer";

	}

	@RequestMapping("/customerModify")
	public String modifyCustomer(@Valid Customer customer) {
		service.modifyCustomer(customer);
		return "customers/viewCustomer";
	}

}
