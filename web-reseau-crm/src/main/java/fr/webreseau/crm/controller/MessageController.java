package fr.webreseau.crm.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import fr.webreseau.crm.model.Message;
import fr.webreseau.crm.model.Project;
import fr.webreseau.crm.model.ProjectTask;
import fr.webreseau.crm.service.IServiceMessage;
import fr.webreseau.crm.service.IServiceTask;

@Controller
public class MessageController {

	@Autowired
	private IServiceMessage serviceMessage;

	@Autowired
	private IServiceTask serviceTask;

	@RequestMapping("/messageAdd")
	public String newMessage(@Valid Message message, Model model) {
		message.setRead(false);
		Date date = new Date();
		message.setDate(date);
		serviceMessage.creatMessage(message);
		Project project = message.getProject();
		model.addAttribute("project", project);
		ArrayList<ProjectTask> listTask = serviceTask.readTasks();
		ArrayList<ProjectTask> listTasksThisProject = new ArrayList<ProjectTask>();
		Long ID = message.getProject().getID();
		for (ProjectTask t : listTask) {

			if (ID == t.getProject().getID()) {
				listTasksThisProject.add(t);
				model.addAttribute("tasks", listTasksThisProject);
			}
		}

		ArrayList<Message> listMessage = serviceMessage.readMessage();
		ArrayList<Message> messagesOfProject = new ArrayList<Message>();
		for (Message m : listMessage) {
			if (m.getCustomer() == message.getCustomer() && m.getProject() == message.getProject()) {
				messagesOfProject.add(m);
			}
		}
		Collections.reverse(messagesOfProject);
		model.addAttribute("messages", messagesOfProject);
		return "forward:/viewProject";
	}

	@RequestMapping("/messageRead")
	public String messageAsRead(@RequestParam(value = "IDmessageRead") Long ID) {
		//System.out.println(ID);
		Message message = serviceMessage.readOneMessage(ID);
		message.setRead(true);
		//System.out.println(message);
		serviceMessage.editMessage(message);
		return "forward:/viewProject";
	}
	
	@RequestMapping("/messageReply")
	public String messageReply(@RequestParam(value = "IDmessageReply") Long ID) {
		System.out.println(ID);
		return "forward:/viewProject";
	}
}
