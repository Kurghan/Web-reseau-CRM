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
			String city, String country, String role, String nSiret, String companyName,boolean enable) {
		super(iD, name, firstName, mail, password, phone, address, city, country, role, enable);
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

	@Column(name="CompanyNameCustomer")
	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	@Override
	public String toString() {
		return "Customer [nSiret=" + nSiret + ", companyName=" + companyName + "]";
	}

	
}
