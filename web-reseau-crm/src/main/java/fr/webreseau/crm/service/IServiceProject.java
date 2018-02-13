package fr.webreseau.crm.service;

import java.util.ArrayList;

import fr.webreseau.crm.model.Project;

public interface IServiceProject {

	public ArrayList<Project> readProject();
	
	public Project readOneProject(Long ID);
	
	public void creatProject(Project project);
	
	public void modifyProject(Project project);
	
	public void deleteProject(Long ID);
	
}
