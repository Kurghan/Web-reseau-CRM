package fr.webreseau.crm.service;

import java.util.ArrayList;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

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
		
	}

	@Override
	public void modifyProject(Project project) {
		dao.save(project);
		
	}

	@Override
	public void deleteProject(Long ID) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Project readOneProject(Long ID) {
		return dao.findOne(ID);
		
	}
	
	//recuperation du nombre de jour restant avant la fin du projet
	public void dayRest(Long ID , Model model) {
		Project project = readOneProject(ID);
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

}
