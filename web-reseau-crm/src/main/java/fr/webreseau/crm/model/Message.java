package fr.webreseau.crm.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name="message")
public class Message {
	
	private Long IDMessage;
	private String title;
	private String messageContent;
	private Boolean read;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date date;
	private Customer customer;
	private Project project;
	
	
	public Message(Long iDMessage, String title, String messageContent, Boolean read, Date date, Customer customer,
			Project project) {
		super();
		IDMessage = iDMessage;
		this.title = title;
		this.messageContent = messageContent;
		this.read = read;
		this.date = date;
		this.customer = customer;
		this.project = project;
	}

	public Message() {}

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="IDMessage")
	public Long getIDMessage() {
		return IDMessage;
	}


	public void setIDMessage(Long iDMessage) {
		IDMessage = iDMessage;
	}

	@Column(name="titleMessage")
	public String getTitle() {
		return title;
	}


	public void setTitle(String title) {
		this.title = title;
	}

	@Column(name="textMessage")
	public String getMessageContent() {
		return messageContent;
	}


	public void setMessageContent(String messageContent) {
		this.messageContent = messageContent;
	}

	@Column(name="notRead")
	public Boolean getRead() {
		return read;
	}

	
	public void setRead(Boolean read) {
		this.read = read;
	}

	@Column(name="dateMessage")
	public Date getDate() {
		return date;
	}

	
	public void setDate(Date date) {
		this.date = date;
	}

	@ManyToOne
    @JoinColumn(name="IDPerson")
	public Customer getCustomer() {
		return customer;
	}


	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	@ManyToOne
    @JoinColumn(name="IDProject")
	public Project getProject() {
		return project;
	}


	public void setProject(Project project) {
		this.project = project;
	}


	@Override
	public String toString() {
		return "Message [IDMessage=" + IDMessage + ", title=" + title + ", messageContent=" + messageContent + ", read="
				+ read + ", date=" + date + ", customer=" + customer + ", project=" + project + "]";
	}

	
	
}
