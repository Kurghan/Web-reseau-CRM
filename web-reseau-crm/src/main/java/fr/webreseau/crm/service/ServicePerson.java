package fr.webreseau.crm.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import fr.webreseau.crm.dao.IRepositoryPerson;
import fr.webreseau.crm.model.Person;


@Service
public class ServicePerson implements IServicePerson{
	
	@Autowired
	private IRepositoryPerson<Person> dao;

	@Override
	public ArrayList<Person> readPersons() {
		return (ArrayList<Person>) dao.findAll();
	}

	@Override
	public Person readOnePerson(Long ID) {
		return dao.findOne(ID);
	}


}
