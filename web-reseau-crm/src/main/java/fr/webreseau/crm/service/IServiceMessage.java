package fr.webreseau.crm.service;

import java.util.ArrayList;

import org.springframework.ui.Model;

import fr.webreseau.crm.model.Message;

public interface IServiceMessage {

	public ArrayList<Message> readMessage();
	
	public Message readOneMessage(Long ID);
	
	public void creatMessage(Message message);
	
	public void editMessage(Message message);
	
	public void deleteMessage(Long ID);
	
	public ArrayList<Message> getMessageListOfProject(Long ID , Model model);
	
	public void getAllMessageByIDSources(Long ID , Model model);
	
}
