package fr.webreseau.crm.service;

import java.util.ArrayList;

import org.springframework.ui.Model;

import fr.webreseau.crm.model.ProjectTask;

public interface IServiceTask {

	public ArrayList<ProjectTask> readTasks();
	
	public ProjectTask readOneTask(Long ID);
	
	public void creatTask(ProjectTask task);
	
	public void modifyTask(ProjectTask task);
	
	public void deleteTask(Long ID);
	
	public ArrayList<ProjectTask> listProjectTasksById(Long ID);
	
	public void percentTask(Long ID , Model model);
}
