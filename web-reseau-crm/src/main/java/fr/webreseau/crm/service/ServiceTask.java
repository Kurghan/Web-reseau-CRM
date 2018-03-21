package fr.webreseau.crm.service;

import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

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
	
	public ArrayList<ProjectTask> listProjectTasksById(Long ID){
		ArrayList<ProjectTask> listTasks = readTasks();
		ArrayList<ProjectTask> listProjectTasks = new ArrayList<ProjectTask>();
		for(ProjectTask p : listTasks) {
			if(p.getProject().getID().equals(ID)) {
				listProjectTasks.add(p);
			}
		}
		return listProjectTasks;
	}
	
	//avancement des taches '%'
	public void percentTask(Long ID , Model model) {
		ArrayList<ProjectTask> listTasks = listProjectTasksById(ID);
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
	}

}
