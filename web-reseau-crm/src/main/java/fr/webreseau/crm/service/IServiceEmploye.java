package fr.webreseau.crm.service;

import java.util.ArrayList;
import fr.webreseau.crm.model.Employe;

public interface IServiceEmploye {

	
	public ArrayList<Employe> readEmploye();
	
	public void creatEmploye(Employe employe);
	
	public void modifyEmploye(Employe employe);
	
	public void deleteEmploye (Long ID);
}
