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
import org.springframework.web.bind.annotation.RequestParam;

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

	// page de vue d'un projet
	@RequestMapping("/viewProject")
	public String pageViewProject(@RequestParam(value = "ID") Long ID, Model model) {
		serviceSessionUser.getSessionUser(model);
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

	/*public void progressBarProject(Long ID , Model model) {
		Project project = service.readOneProject(ID);
		Date startDate = project.getStartDate();
		Date deadLine = project.getDeadLine();
		final long MILLISECONDS_PER_DAY = 1000 * 60 * 60 * 24;
		Long delta =deadLine.getTime() - startDate.getTime();
		Long rest = ((delta / MILLISECONDS_PER_DAY));
		System.out.println(rest);
		Long dayD =new Date().getTime();
		Long dayRest = dayD - rest;
		System.out.println(dayRest);
	}*/
	
	
/*	//recuperation du nombre de jour restant avant la fin du projet
	public void dayRest(Long ID , Model model) {
		Project project = service.readOneProject(ID);
		final long MILLISECONDS_PER_DAY = 1000 * 60 * 60 * 24; 
		Long delta =  project.getDeadLine().getTime() - project.getStartDate().getTime();
		Long nbDaysTotal = delta / (MILLISECONDS_PER_DAY);
		long dDay = new Date().getTime(); 
		Long delatNbRest = project.getDeadLine().getTime() - dDay;
		Long nbRest = delatNbRest / (MILLISECONDS_PER_DAY);
		Long percent = (nbRest*100)/nbDaysTotal;
		Long percentRest = 100 - percent;
		model.addAttribute("percentRest",percentRest);
		if(nbRest > 0) {
		model.addAttribute("nbRest",nbRest);}
		else {
			nbRest = (long) 0;
			model.addAttribute("nbRest",nbRest);
		}
	}
	
	//avancement des taches '%'
	public void percentTask(Long ID , Model model) {
		ArrayList<ProjectTask> listTasks = serviceTask.listProjectTasksById(ID);
		int compteur = 0;
		int percentTask = 0;
		for (ProjectTask p : listTasks) {
			percentTask = percentTask + p.getProgress();
			compteur ++;
		}
		double decPercent = ((double)percentTask/100)/compteur;
		//System.out.println(decPercent);
		String nbPercent = String.valueOf(decPercent);
		model.addAttribute("nbPercent",nbPercent);
	}*/
}
