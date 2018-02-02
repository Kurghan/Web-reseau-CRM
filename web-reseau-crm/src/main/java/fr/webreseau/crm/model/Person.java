package fr.webreseau.crm.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.Table;
import javax.persistence.InheritanceType;

@Entity
@Table(name="person")
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Person {

	private Long ID;
	private String name;
	private String firstName;
	private String mail;
	private String password;
	private String phone;
	private String address;
	private String city;
	private String country;
	
	
	public Person(Long iD, String name, String firstName, String mail, String password, String phone, String address, String city, String country) {
		super();
		ID = iD;
		this.name = name;
		this.firstName = firstName;
		this.mail = mail;
		this.password = password;
		this.phone = phone;
		this.address = address;
		this.city = city;
		this.country = country;
	}
	
	public Person() {}

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="IDPerson")
	public Long getID() {
		return ID;
	}

	
	public void setID(Long iD) {
		ID = iD;
	}

	@Column(name="NamePerson")
	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}

	@Column(name="FirstNamePerson")
	public String getFirstName() {
		return firstName;
	}


	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	@Column(name="MailPerson")
	public String getMail() {
		return mail;
	}


	public void setMail(String mail) {
		this.mail = mail;
	}

	@Column(name="PasswordPerson")
	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}

	@Column(name="PhonePerson")
	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	@Column(name="AddressPerson")
	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Column(name="CityPerson")
	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	@Column(name="CountryPerson")
	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	@Override
	public String toString() {
		return "Person [ID=" + ID + ", name=" + name + ", firstName=" + firstName + ", mail=" + mail + ", password="
				+ password + ", phone=" + phone + ", address=" + address + ", city=" + city + ", country=" + country
				+ "]";
	}
	
	
	
}
