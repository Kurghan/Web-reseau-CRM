package fr.webreseau.crm.controller;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Collections;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import fr.webreseau.crm.model.Project;
import fr.webreseau.crm.model.ProjectTask;
import fr.webreseau.crm.service.IServiceMessage;
import fr.webreseau.crm.service.IServiceProject;
import fr.webreseau.crm.service.IServiceSessionUser;
import fr.webreseau.crm.service.IServiceTask;

@Controller
public class ProjectController {

	@Autowired
	private IServiceProject service;

	@Autowired
	private IServiceTask serviceTask;

	@Autowired
	private IServiceMessage serviceMessage;
	
	@Autowired
	private IServiceSessionUser serviceSessionUser;

	@GetMapping("/projects")
	public String pageProject(Model model , Principal principal) {
		ArrayList<Project> listProjects = service.readProject();
		ArrayList<Project> listProjectsUser = new ArrayList<Project>();
		serviceSessionUser.getSessionUser(model);
		for(Project p : listProjects) {
			if(p.getCustomer().getMail().equals(principal.getName())) {
				listProjectsUser.add(p);
				Collections.reverse(listProjectsUser);
				model.addAttribute("listProjectsUser", listProjectsUser);
			}
					
		}
		Collections.reverse(listProjects);
		model.addAttribute("listProjects", listProjects);
		return "projects/projects";

	}
	
	@RequestMapping("/viewProjectID")
	public String pageViewProjectID(@Valid Long ID ,RedirectAttributes ra) {
		ra.addAttribute("ID",ID);
		return "redirect:/viewProject";
		
	}
	
	// page de vue d'un projet
	@RequestMapping(value ="/viewProject")
	public String pageViewProject(@Valid Long ID,Model model) {
		serviceSessionUser.getSessionUser(model);
		ArrayList<Project> listProject = service.readProject();
		ArrayList<ProjectTask> listTask = serviceTask.readTasks();
		for (Project p : listProject) {
			if (ID == p.getID() && ID != null) {
				model.addAttribute("project", p);
			}
		}
		// recuperation de la liste des taches du projet pour le tableau des taches
		ArrayList<ProjectTask> listTasksThisProject = new ArrayList<ProjectTask>();
		for (ProjectTask t : listTask) {

			if (ID == t.getProject().getID()) {
				listTasksThisProject.add(t);
				model.addAttribute("tasks", listTasksThisProject);
			}
		}
		serviceMessage.getMessageListOfProject(ID, model);
		service.dayRest(ID , model);
		serviceTask.percentTask(ID, model);
		return "projects/viewProject";
	}


	@RequestMapping("/projectAdd")
	public String pageProjectAdd(@Valid Project project) {
		service.creatProject(project);
		return "projects/viewProject";

	}

	@RequestMapping("/editProject")
	public String pageModifyProject(@Valid Project project, Model model) {
		serviceSessionUser.getSessionUser(model);
		service.modifyProject(project);
		return "forward:/viewProject";
	}

}
