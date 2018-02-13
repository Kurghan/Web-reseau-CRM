package fr.webreseau.crm.controller;

import java.util.ArrayList;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import fr.webreseau.crm.model.Project;
import fr.webreseau.crm.model.ProjectTask;
import fr.webreseau.crm.service.IServiceProject;
import fr.webreseau.crm.service.IServiceTask;

@Controller
public class taskController {
	
	
	@Autowired
	private IServiceTask serviceTask;
	
	@Autowired
	private IServiceProject serviceProject;
	
	@RequestMapping("/taskAdd")
		public String AddTaskToProject(@Valid ProjectTask projectTask , Model model) {	
		Long ID = projectTask.getProject().getID();
		Project project = serviceProject.readOneProject(ID);
		model.addAttribute("project",project);
		serviceTask.creatTask(projectTask);
		//System.out.println(projectTask);
		ArrayList<ProjectTask> listTask = serviceTask.readTasks();
		ArrayList<ProjectTask> listTasksThisProject = new ArrayList<ProjectTask>();
		for(ProjectTask t : listTask) {
			 
			if(ID==t.getProject().getID()) {
				listTasksThisProject.add(t);
				model.addAttribute("tasks",listTasksThisProject);
			}}
		return "projects/viewProject";
		//return "projects/viewProject";
	
	}

}

