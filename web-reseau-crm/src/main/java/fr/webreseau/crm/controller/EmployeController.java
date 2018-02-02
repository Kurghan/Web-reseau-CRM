package fr.webreseau.crm.controller;

import java.util.ArrayList;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import fr.webreseau.crm.model.Employe;
import fr.webreseau.crm.service.IServiceEmploye;

@Controller
public class EmployeController {
	
	@Autowired
	private IServiceEmploye service;
	
	@GetMapping("/employe")
	public String pageEmploye(Model model) {
		ArrayList<Employe> listEmploye = service.readEmploye();
		model.addAttribute("listEmploye",listEmploye);
		//System.out.println(listCustomers);
		return "employes/employe";
	}
	
	@GetMapping("/addEmploye")
	public String pageAddCustomer() {
		return "employes/addEmploye";
		
	}
	
	@PostMapping("/employeAdd")
	public String employeAdd(@Valid Employe employe) {
	service.creatEmploye(employe);
	System.out.println(employe);
		
		return "redirect:/employe";
}
	
	@PostMapping("/deleteEmploye")
	public String customerDelete(Employe employe) {
		System.out.println(employe);
		ArrayList<Employe> listEmploye = service.readEmploye();
		for(Employe e : listEmploye){
			if (e.getID().equals(employe.getID())) {
				Long ID = e.getID();
				//System.out.println(ID);
				service.deleteEmploye(ID);
			}
		}
		return "redirect:/employe";
	}

}
