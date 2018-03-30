package fr.webreseau.crm.service;

import java.util.ArrayList;

import fr.webreseau.crm.model.Person;

public interface IServicePerson {

	public ArrayList<Person> readPersons();
	
	public Person readOnePerson(Long ID);
	
}
