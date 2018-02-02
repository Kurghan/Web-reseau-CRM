package fr.webreseau.crm.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;


@Entity
@Table(name="employe")
public class Employe extends Person{
	

	private String statue;

	public Employe(Long iD, String name, String firstName, String mail, String password, String phone, String address,
			String city, String country, String statue) {
		super(iD, name, firstName, mail, password, phone, address, city, country);
		this.statue = statue;
	}


	public Employe() {}


	@Column(name="StatueEmploye")
	public String getStatue() {
		return statue;
	}

	public void setStatue(String statue) {
		this.statue = statue;
	}

}
