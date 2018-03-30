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
import fr.webreseau.crm.service.IServiceSessionUser;

@Controller
public class EmployeController {
	
	@Autowired
	private IServiceEmploye service;
	
	@Autowired
	private IServiceSessionUser serviceSessionUser;
	
	@GetMapping("/employes")
	public String pageEmploye(Model model) {
		ArrayList<Employe> listEmploye = service.readEmploye();
		serviceSessionUser.getSessionUser(model);
		model.addAttribute("listEmploye",listEmploye);
		return "employes/employes";
	}
	
	@GetMapping("/addEmploye")
	public String pageAddCustomer(Model model) {
		serviceSessionUser.getSessionUser(model);
		return "employes/addEmploye";
		
	}
	
	@PostMapping("/employeAdd")
	public String employeAdd(@Valid Employe employe) {
		employe.setRole("ADMIN");
	service.creatEmploye(employe);
		
		return "redirect:/employes";
}
	
	@PostMapping("/deleteEmploye")
	public String customerDelete(Employe employe) {
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
		serviceSessionUser.getSessionUser(model);
		ArrayList<Employe> listEmploye = service.readEmploye();
		for (Employe e : listEmploye) {
			if (ID == e.getID()) {
				model.addAttribute("employe", e);
			}
		}
		return "employes/viewEmploye";

	}

	@RequestMapping("/editEmploye")
	public String editEmploye(@RequestParam(value = "ID") Long ID, Model model) {
		serviceSessionUser.getSessionUser(model);
		ArrayList<Employe> listEmploye = service.readEmploye();
		for (Employe e : listEmploye) {
			if (ID == e.getID()) {
				model.addAttribute(e);
			}
		}
		return "employes/modifyEmploye";

	}
	
	@RequestMapping("/employeModify")
	public String modifyEmploye(@Valid Employe employe,Model model) {
		serviceSessionUser.getSessionUser(model);
		service.modifyEmploye(employe);
		return "employes/viewEmploye";
	}
	

}
