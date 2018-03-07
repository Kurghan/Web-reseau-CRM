package fr.webreseau.crm.service;

import java.util.ArrayList;
import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import fr.webreseau.crm.dao.IRepositoryMessage;
import fr.webreseau.crm.model.Message;

@Service
public class ServiceMessage implements IServiceMessage {
	
	@Autowired
	private IRepositoryMessage dao;

	@Override
	public ArrayList<Message> readMessage() {
		return (ArrayList<Message>) dao.findAll();
	}
	
	@Override
	public Message readOneMessage(Long ID) {
		return dao.findOne(ID);
	}

	@Override
	public void creatMessage(Message message) {
		dao.save(message);

	}

	@Override
	public void editMessage(Message message) {
		dao.save(message);

	}

	@Override
	public void deleteMessage(Long ID) {
		dao.delete(ID);

	}
	
	// recuperation de la liste des messages du projet pour le tableau des messages
	public ArrayList<Message> getMessageListOfProject(Long ID, Model model) {
		ArrayList<Message> listMessages = (ArrayList<Message>) dao.findAll();
		ArrayList<Message> messagesOfProject = new ArrayList<Message>();
		for (Message m : listMessages) {
			if (m.getProject().getID() == ID) {
				messagesOfProject.add(m);
			}
		}
		Collections.reverse(messagesOfProject);
		model.addAttribute("messages", messagesOfProject);
		
		return messagesOfProject;
	}

	//recuperation de la liste des reponses d'un message
	/*public ArrayList<Message> getReplyOfMessage(Long IDMessage ,Model model){
		ArrayList<Message> listMessages = (ArrayList<Message>) dao.findAll();
		ArrayList<Message> messagesReply = new ArrayList<Message>();
		for(Message m : listMessages) {
			if (m.getMessageSources() == IDMessage) {
				messagesReply.add(m);
			}
		}
		model.addAttribute("messagesReply", messagesReply);
		return messagesReply;
	}*/
}
