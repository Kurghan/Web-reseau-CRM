package fr.webreseau.crm.service;

import java.util.ArrayList;

import fr.webreseau.crm.model.ProjectTask;

public interface IServiceTask {

	public ArrayList<ProjectTask> readTasks();
	
	public ProjectTask readOneTask(Long ID);
	
	public void creatTask(ProjectTask task);
	
	public void modifyTask(ProjectTask task);
	
	public void deleteTask(Long ID);
}
