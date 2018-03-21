package fr.webreseau.crm.controller;

import java.util.ArrayList;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import fr.webreseau.crm.model.Project;
import fr.webreseau.crm.model.ProjectTask;
import fr.webreseau.crm.service.IServiceMessage;
import fr.webreseau.crm.service.IServiceProject;
import fr.webreseau.crm.service.IServiceSessionUser;
import fr.webreseau.crm.service.IServiceTask;

@Controller
public class TaskController {

	@Autowired
	private IServiceTask serviceTask;

	@Autowired
	private IServiceProject serviceProject;
	
	@Autowired
	private IServiceMessage serviceMessage;
	
	@Autowired
	private IServiceSessionUser serviceSessionUser;

	@RequestMapping("/taskAdd")
	public String AddTaskToProject(@Valid ProjectTask projectTask, Model model) {
		serviceSessionUser.getSessionUser(model);
		Long ID = projectTask.getProject().getID();
		Project project = serviceProject.readOneProject(ID);
		model.addAttribute("project", project);
		serviceTask.creatTask(projectTask);
		serviceMessage.getMessageListOfProject(ID, model);
		serviceTask.percentTask(ID, model);
		serviceProject.dayRest(ID, model);
		return taskOfProject(model, ID);
	}

	@RequestMapping("/taskEdit")
	public String editTaskToProject(@Valid ProjectTask projectTask, Model model) {
		serviceSessionUser.getSessionUser(model);
		ArrayList<ProjectTask> taskList = serviceTask.readTasks();
		for (ProjectTask p : taskList) {
			if (p.getID() == projectTask.getID()) {
				projectTask.setProject(p.getProject());
			}
		}
		serviceTask.modifyTask(projectTask);
		Long ID = projectTask.getProject().getID();
		Project project = serviceProject.readOneProject(ID);
		model.addAttribute("project", project);
		serviceMessage.getMessageListOfProject(ID, model);
		taskOfProject(model, ID);
		serviceTask.percentTask(ID, model);
		serviceProject.dayRest(ID, model);
		return "projects/viewProject";
	}

	// recuperation des tasks d'un projet
	private String taskOfProject(Model model, Long ID) {
		ArrayList<ProjectTask> listTask = serviceTask.readTasks();
		ArrayList<ProjectTask> listTasksThisProject = new ArrayList<ProjectTask>();
		for (ProjectTask t : listTask) {
			if (ID == t.getProject().getID()) {
				listTasksThisProject.add(t);
				model.addAttribute("tasks", listTasksThisProject);
			}
		}
		return "projects/viewProject";

	}

	// suppression d'un tache dans le projet
	@RequestMapping("/taskDelete")
	private String taskToDelete(@RequestParam(value = "ID") Long IDTask, Model model) {
		serviceSessionUser.getSessionUser(model);
		ProjectTask task = serviceTask.readOneTask(IDTask);
		Long ID = task.getProject().getID();
		Project project = serviceProject.readOneProject(ID);
		model.addAttribute("project", project);
		serviceTask.deleteTask(IDTask);
		serviceMessage.getMessageListOfProject(ID, model);
		serviceTask.percentTask(ID, model);
		serviceProject.dayRest(ID, model);
		return taskOfProject(model, ID);

	}

}
