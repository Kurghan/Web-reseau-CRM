package fr.webreseau.crm.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;


@Entity
@Table(name="employe")
public class Employe extends Person{
	

	private String fonction;

	public Employe(Long iD, String name, String firstName, String mail, String password, String phone, String address,
			String city, String country, String fonction) {
		super(iD, name, firstName, mail, password, phone, address, city, country);
		this.fonction = fonction;
	}


	public Employe() {}


	@Column(name="FonctionEmploye")
	public String getFonction() {
		return fonction;
	}

	public void setFonction(String fonction) {
		this.fonction = fonction;
	}
	
	@Override
	public String toString() {
		return "Employe [fonction=" + fonction + "]";
	}

}
