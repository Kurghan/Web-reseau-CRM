package fr.webreseau.crm.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.webreseau.crm.dao.IRepositoryProject;
import fr.webreseau.crm.model.Project;


@Service
public class ServiceProject implements IServiceProject {
	
	@Autowired
	private IRepositoryProject dao;

	@Override
	public ArrayList<Project> readProject() {
		return (ArrayList<Project>) dao.findAll();
	}

	@Override
	public void creatProject(Project project) {
		dao.save(project);
		System.out.println(project);
		
	}

	@Override
	public void modifyProject(Project project) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteProject(Long ID) {
		// TODO Auto-generated method stub
		
	}

}
