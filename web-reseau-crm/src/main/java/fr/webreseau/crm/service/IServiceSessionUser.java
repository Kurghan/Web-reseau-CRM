package fr.webreseau.crm.service;

import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

@Service
public interface IServiceSessionUser {

	public void getSessionUser(Model model);
}
