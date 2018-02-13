package fr.webreseau.crm.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.webreseau.crm.dao.IRepositoryTask;
import fr.webreseau.crm.model.ProjectTask;


@Service
public class ServiceTask implements IServiceTask {

	
	@Autowired
	private IRepositoryTask dao;
	
	@Override
	public ArrayList<ProjectTask> readTasks() {
		// TODO Auto-generated method stub
		return (ArrayList<ProjectTask>) dao.findAll();
	}

	@Override
	public ProjectTask readOneTask(Long ID) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void creatTask(ProjectTask task) {
		dao.save(task);

	}

	@Override
	public void modifyTask(ProjectTask task) {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteTask(Long ID) {
		// TODO Auto-generated method stub

	}

}
