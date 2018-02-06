package fr.webreseau.crm.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import fr.webreseau.crm.model.Project;
import fr.webreseau.crm.service.IServiceProject;

@Controller
public class ProjectController {

	@Autowired
	private IServiceProject service;
	
	@GetMapping("/projects")
	public String pageProject(Model model) {
		ArrayList<Project> listProject = service.readProject();
		model.addAttribute("listProject",listProject);
		//System.out.println(listProject);
		return "projects/projects";
		
	}
	
	@GetMapping("/addProject{id}")
    public String pageAddProject(@RequestParam("id") Long ID){
		System.out.println(ID);
		return "projects/addProject";
	}
	
	@RequestMapping("/viewProject")
	public String pageViewProject(@RequestParam(value = "ID") Long ID, Model model) {
		ArrayList<Project> listProject = service.readProject();
		for (Project p : listProject) {
			if (ID == p.getID()) {
				model.addAttribute("project", p);
			}
			//System.out.println(p.getName());
		}
		return "projects/viewProject";
		
	}
	
}
