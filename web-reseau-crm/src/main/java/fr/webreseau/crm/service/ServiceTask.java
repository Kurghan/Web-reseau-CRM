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
		return dao.findOne(ID);
	}

	@Override
	public void creatTask(ProjectTask task) {
		dao.save(task);

	}

	@Override
	public void modifyTask(ProjectTask task) {
		dao.save(task);

	}

	@Override
	public void deleteTask(Long ID) {
		dao.delete(ID);

	}

}
