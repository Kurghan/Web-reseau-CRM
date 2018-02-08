package fr.webreseau.crm.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;


@Entity
@Table(name="project")
public class Project {

	private Long ID;
	private String name;
	private String description;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date startDate;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date deadLine;
	private Long IDCustomer;
	
	public Project(Long iD, String name, String description, Date startDate,
			Date deadLine) {
		super();
		ID = iD;
		this.name = name;
		this.description = description;
		this.startDate = startDate;
		this.deadLine = deadLine;
		
	}
	
	public Project() {}
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="IDProject")
	public Long getID() {
		return ID;
	}
	public void setID(Long iD) {
		ID = iD;
	}
	
	@Column(name="NameProject")
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	@Column(name="DescriptionProject")
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	@Column(name="StartDateProject")
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	
	@Column(name="DeadLineProject")
	public Date getDeadLine() {
		return deadLine;
	}
	public void setDeadLine(Date deadLine) {
		this.deadLine = deadLine;
	}
	
	public Long getIDCustomer() {
		return IDCustomer;
	}

	public void setIDCustomer(Long iDCustomer) {
		IDCustomer = iDCustomer;
	}

	@Override
	public String toString() {
		return "Project [ID=" + ID + ", name=" + name + ", description=" + description + ", startDate=" + startDate
				+ ", deadLine=" + deadLine + ", IDCustomer=" + IDCustomer + "]";
	}

	
	
}
