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

import fr.webreseau.crm.model.Employe;
import fr.webreseau.crm.service.IServiceEmploye;

@Controller
public class EmployeController {
	
	@Autowired
	private IServiceEmploye service;
	
	@GetMapping("/employes")
	public String pageEmploye(Model model) {
		ArrayList<Employe> listEmploye = service.readEmploye();
		model.addAttribute("listEmploye",listEmploye);
		return "employes/employes";
	}
	
	@GetMapping("/addEmploye")
	public String pageAddCustomer() {
		return "employes/addEmploye";
		
	}
	
	@PostMapping("/employeAdd")
	public String employeAdd(@Valid Employe employe) {
	service.creatEmploye(employe);
	System.out.println(employe);
		
		return "redirect:/employes";
}
	
	@PostMapping("/deleteEmploye")
	public String customerDelete(Employe employe) {
		System.out.println(employe);
		ArrayList<Employe> listEmploye = service.readEmploye();
		for(Employe e : listEmploye){
			if (e.getID().equals(employe.getID())) {
				Long ID = e.getID();
				service.deleteEmploye(ID);
			}
		}
		return "redirect:/employes";
	}
	
	@RequestMapping("/viewEmploye")
	public String pageViewCustomer(@RequestParam(value = "ID") Long ID, Model model) {
		ArrayList<Employe> listEmploye = service.readEmploye();
		System.out.println(ID);
		for (Employe e : listEmploye) {
			if (ID == e.getID()) {
				model.addAttribute("employe", e);
			}
		}
		System.out.println(ID);
		return "employes/viewEmploye";

	}

	@RequestMapping("/editEmploye")
	public String editEmploye(@RequestParam(value = "ID") Long ID, Model model) {
		ArrayList<Employe> listEmploye = service.readEmploye();
		for (Employe e : listEmploye) {
			if (ID == e.getID()) {
				model.addAttribute(e);
			}
		}
		return "employes/modifyEmploye";

	}
	
	@RequestMapping("/employeModify")
	public String modifyEmploye(@Valid Employe employe) {
		service.modifyEmploye(employe);
		return "employes/viewEmploye";
	}

}
