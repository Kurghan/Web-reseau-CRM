package fr.webreseau.crm.controller;

import java.util.ArrayList;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import fr.webreseau.crm.model.Project;
import fr.webreseau.crm.model.ProjectTask;
import fr.webreseau.crm.service.IServiceProject;
import fr.webreseau.crm.service.IServiceTask;

@Controller
public class ProjectController {

	@Autowired
	private IServiceProject service;
	
	@Autowired
	private IServiceTask serviceTask;

	@GetMapping("/projects")
	public String pageProject(Model model) {
		ArrayList<Project> listProjects = service.readProject();
		model.addAttribute("listProjects",listProjects);
		return "projects/projects";

	}

	@RequestMapping("/viewProject")
	public String pageViewProject(@RequestParam(value = "ID")Long ID , Model model) {
		ArrayList<Project> listProject = service.readProject();
		//System.out.println(ID);
		ArrayList<ProjectTask> listTask = serviceTask.readTasks();
		for (Project p : listProject) {
			if (ID == p.getID()) {
				model.addAttribute("project", p);
				//System.out.println(listTask);
			}
		}
		ArrayList<ProjectTask> listTasksThisProject = new ArrayList<ProjectTask>();
		for(ProjectTask t : listTask) {
			 
			if(ID==t.getProject().getID()) {
				listTasksThisProject.add(t);
				model.addAttribute("tasks",listTasksThisProject);
			}
		}
		return "projects/viewProject";

	}

	@RequestMapping("/projectAdd")
	public String pageProjectAdd(@Valid Project project) {
		service.creatProject(project);
		//System.out.println(project.getCustomer().getName());
		return "projects/viewProject";
		
	}
	
	@RequestMapping("/editProject")
	public String pageModifyProject(@RequestParam(value = "ID")Long ID,Model model) {
		ArrayList<Project> listProject = service.readProject();
		for(Project p : listProject) {
			if(ID == p.getID()) {
				model.addAttribute(p);
				System.out.println(p.getStartDate());
			}
		}
		return "projects/modifyProject";
	}
	
	@RequestMapping("/modifyProject")
	public String pageProjectModify(@Valid Project project , Model model) {
		service.modifyProject(project);
		ArrayList<Project> listProject = service.readProject();
		//retrouver tous les attributs du projet pour reafficher le nom correctement
		for(Project p : listProject) {
			if(p.getCustomer().getID() == project.getCustomer().getID()) {
				model.addAttribute(p);
				//System.out.println(project);
			}
		}
		return "projects/viewProject";
	}
	
}
