package fr.webreseau.crm.controller;

import java.util.ArrayList;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import fr.webreseau.crm.model.Customer;
import fr.webreseau.crm.model.Project;
import fr.webreseau.crm.service.IServiceCustomer;
import fr.webreseau.crm.service.IServiceProject;

@Controller
public class ProjectController {

	@Autowired
	private IServiceProject service;

	@Autowired
	private IServiceCustomer serviceCustomer;

	@GetMapping("/projects")
	public String pageProject(Model model) {
		ArrayList<Project> listProjects = service.readProject();
		ArrayList<Customer> listCustomers = serviceCustomer.readCustomers();
		model.addAttribute("listCustomers",listCustomers);
		model.addAttribute("listProjects",listProjects);
		return "projects/projects";

	}

	@RequestMapping("/viewProject")
	public String pageViewProject(@RequestParam(value = "ID") Long ID, Model model) {
		ArrayList<Project> listProject = service.readProject();
		for (Project p : listProject) {
			if (ID == p.getID()) {
				model.addAttribute("project", p);
			}
		}
		return "projects/viewProject";

	}

	@RequestMapping("/projectAdd")
	public String pageProjectAdd(@Valid Project project) {
		service.creatProject(project);
		return "projects/viewProject";
	}
}
