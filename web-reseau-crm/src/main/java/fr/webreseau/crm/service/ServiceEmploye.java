package fr.webreseau.crm.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.webreseau.crm.dao.IRepositoryEmploye;
import fr.webreseau.crm.model.Employe;


@Service
public class ServiceEmploye implements IServiceEmploye{

	@Autowired
	private IRepositoryEmploye dao;
	
	@Override
	public ArrayList<Employe> readEmploye() {
		
		return (ArrayList<Employe>) dao.findAll();
	}

	@Override
	public void creatEmploye(Employe employe) {
		dao.save(employe);
		
	}

	@Override
	public void modifyEmploye(Employe employe) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteEmploye(Long ID) {
		dao.delete(ID);
		
	}
	

	

}
