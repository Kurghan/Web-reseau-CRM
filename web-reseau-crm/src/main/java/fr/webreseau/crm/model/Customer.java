package fr.webreseau.crm.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;


@Entity
@Table(name="customer")
public class Customer extends Person {
	

	private String nSiret;
	private String companyName;


	
	public Customer(Long iD, String name, String firstName, String mail, String password, String phone, String address,
			String city, String country, String nSiret, String companyName) {
		super(iD, name, firstName, mail, password, phone, address, city, country);
		this.nSiret = nSiret;
		this.companyName = companyName;
	}

	public Customer() {}
	
	@Column(name="NsiretCustomer")
	public String getnSiret() {
		return nSiret;
	}


	public void setnSiret(String nSiret) {
		this.nSiret = nSiret;
	}

	@Column(name="CompanyName")
	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	@Override
	public String toString() {
		return "N°Siret=" + nSiret + ", companyName=" + companyName + ", Name= " + super.getName() + ", FirstName= " + super.getFirstName() + ", Address= " + super.getAddress() + ", City= " + super.getCity() + ", Country= " + super.getCountry() + ", Phone= " + super.getPhone() + ", Mail= " + super.getMail();
	}
	

	
}
