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
		// TODO Auto-generated method stub
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
		ArrayList<Message> listMessage = (ArrayList<Message>) dao.findAll();
		ArrayList<Message> messagesOfProject = new ArrayList<Message>();
		for (Message m : listMessage) {
			if (m.getProject().getID() == ID) {
				messagesOfProject.add(m);
			}
		}
		Collections.reverse(messagesOfProject);
		model.addAttribute("messages", messagesOfProject);
		return messagesOfProject;
	}

}
