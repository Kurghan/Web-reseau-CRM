package fr.webreseau.crm.controller;

import java.util.ArrayList;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import fr.webreseau.crm.model.Project;
import fr.webreseau.crm.model.ProjectTask;
import fr.webreseau.crm.service.IServiceMessage;
import fr.webreseau.crm.service.IServiceProject;
import fr.webreseau.crm.service.IServiceTask;

@Controller
public class ProjectController {

	@Autowired
	private IServiceProject service;

	@Autowired
	private IServiceTask serviceTask;

	@Autowired
	private IServiceMessage serviceMessage;

	@GetMapping("/projects")
	public String pageProject(Model model) {
		ArrayList<Project> listProjects = service.readProject();
		model.addAttribute("listProjects", listProjects);
		return "projects/projects";

	}

	// page de vue d'un projet
	@RequestMapping("/viewProject")
	public String pageViewProject(@RequestParam(value = "ID") Long ID, Model model) {
		ArrayList<Project> listProject = service.readProject();
		ArrayList<ProjectTask> listTask = serviceTask.readTasks();
		for (Project p : listProject) {
			if (ID == p.getID()) {
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
		serviceMessage.getReplyOfMessage(ID, model);
		
		return "projects/viewProject";
	}

	@RequestMapping("/projectAdd")
	public String pageProjectAdd(@Valid Project project) {
		service.creatProject(project);
		return "projects/viewProject";

	}

	@RequestMapping("/editProject")
	public String pageModifyProject(@Valid Project project, Model model) {
		service.modifyProject(project);
		return "forward:/viewProject";
	}

	
}
