package fr.webreseau.crm.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="ProjectTasks")
public class ProjectTask {

	private Long ID;
	private String name;
	private int progress;
	private String comment;
	private Project project;
	
	
	public ProjectTask() {
		super();
	}

	public ProjectTask(Long iD, String name, int progress, String comment, Project project) {
		super();
		ID = iD;
		this.name = name;
		this.progress = progress;
		this.comment = comment;
		this.project = project;
	}
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="IDTask")
	public Long getID() {
		return ID;
	}
	public void setID(Long iD) {
		ID = iD;
	}
	
	@Column(name="NameTask")
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	@Column(name="ProgressTask")
	public int getProgress() {
		return progress;
	}
	public void setProgress(int progress) {
		this.progress = progress;
	}
	
	@Column(name="CommentTask")
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
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
		return "Task [ID=" + ID + ", name=" + name + ", progress=" + progress + ", description=" + comment
				+ ", project=" + project + "]";
	}
	
}
