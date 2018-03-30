package fr.webreseau.crm.controller;

import java.security.Principal;
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
import fr.webreseau.crm.service.IServiceProject;
import fr.webreseau.crm.service.IServiceSessionUser;
import fr.webreseau.crm.service.IServiceTask;

@Controller
public class MessageController {

	@Autowired
	private IServiceMessage serviceMessage;

	@Autowired
	private IServiceTask serviceTask;
	
	@Autowired
	private IServiceProject serviceProject;
	
	
	@Autowired
	private IServiceSessionUser serviceSessionUser;

	
	@RequestMapping("/listMail")
	public String pageMail(Model model , Principal principal) {
		serviceSessionUser.getSessionUser(model);
		ArrayList<Message> listMessages = serviceMessage.readMessage();
		ArrayList<Message> listMessagesSource = new ArrayList<Message>();
		ArrayList<Message> listMessagesSourceUser = new ArrayList<Message>();
		for(Message m :listMessages) {
			if(m.getMessageSources() == null) {
			listMessagesSource.add(m);
			}
		}
		for(Message m : listMessagesSource) {
			if(m.getProject().getCustomer().getMail().equals(principal.getName())){
				listMessagesSourceUser.add(m);
				Collections.reverse(listMessagesSourceUser);
				model.addAttribute("messagesUser", listMessagesSourceUser);
			}

		}
		Collections.reverse(listMessagesSource);
		model.addAttribute("messages",listMessagesSource);
		return "mail/listMail";
	}
	
	@RequestMapping("/messageAdd")
	public String newMessage(@Valid Message message, Model model) {
		serviceSessionUser.getSessionUser(model);
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
			if (m.getProject().getCustomer() == message.getProject().getCustomer() && m.getProject() == message.getProject()) {
				messagesOfProject.add(m);
			}
		}
		Collections.reverse(messagesOfProject);
		model.addAttribute("messages", messagesOfProject);
		return "forward:/viewProject";
	}
	
	
	@RequestMapping("/openMessage")
	public String openMessage(@RequestParam(value = "ID") Long ID,Model model)  {
		serviceSessionUser.getSessionUser(model);
		Message message = serviceMessage.readOneMessage(ID);
		message.setRead(true);
		serviceMessage.creatMessage(message);
		model.addAttribute("message",message);
		serviceMessage.getAllMessageByIDSources(ID,model);
		return "mail/mail";
	}
	
	@RequestMapping(value ="/openMessagesProject",params="action=Old messages")
	public String openMessagesProject(@RequestParam(value = "IDProject") Long ID,Model model) {
		serviceSessionUser.getSessionUser(model);
		ArrayList<Message> listMessage = serviceMessage.readMessage();
		ArrayList<Message> messagesOfProject = new ArrayList<Message>();
		for(Message m :listMessage) {
			if(m.getProject().getID() == ID && m.getMessageSources() == null) {
				messagesOfProject.add(m);
			}
		}
		Collections.reverse(messagesOfProject);
		model.addAttribute("messages",messagesOfProject);
		return "mail/listMailProject";
	}
	
	@RequestMapping(value ="/openMessagesProject",params="action=new messages")
	public String creatNewMessageProject(@RequestParam(value = "IDProject") Long ID,Model model) {
		serviceSessionUser.getSessionUser(model);
		Project project = serviceProject.readOneProject(ID);
		model.addAttribute("project",project);
		return "mail/newMailProject";
	}
	
	@RequestMapping(value ="/newMessage")
	public String creatMessage(@Valid Message message, Model model) {
		serviceSessionUser.getSessionUser(model);
		message.setDate(new Date());
		message.setRead(false);
		if(message.getMessageSources()!= null) {
			Message messageSource = serviceMessage.readOneMessage(message.getMessageSources());
			if(messageSource.getMessageSources()!=null) {
				message.setMessageSources(messageSource.getMessageSources());
			}
			Message messageOrigin = serviceMessage.readOneMessage(message.getMessageSources());
			messageOrigin.setNbReply(messageOrigin.getNbReply()+1);
			serviceMessage.creatMessage(message);
			}
		else serviceMessage.creatMessage(message);
		return "mail/mail";
		
	}
	
	//edition d'un message
	@RequestMapping("/editMessage")
	public String editMessage(@RequestParam("IDMessage") Long ID,Model model) {
		serviceSessionUser.getSessionUser(model);
		Message message = serviceMessage.readOneMessage(ID);
		model.addAttribute("message",message);
		return "mail/editMail";
	}
	
	@RequestMapping("/messageEdit")
	public String messageEdited(@RequestParam("messageContent") String messageContent ,@RequestParam("idMessage")Long ID, Model model) {
		Message message = serviceMessage.readOneMessage(ID);
		message.setMessageContent(messageContent);
		serviceMessage.creatMessage(message);
		return "forward:listMail";
	}
}
