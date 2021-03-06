package fr.webreseau.crm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import fr.webreseau.crm.service.IServiceSessionUser;

@Controller
public class DefaultPageController {

	@Autowired
	private IServiceSessionUser serviceUser;

	/* affiche la page d'accueil */
	@GetMapping("/")
	public String pageWelcome(Model model) {
		serviceUser.getSessionUser(model);
		return "redirect:/index";
	}
	
	@GetMapping("/index")
	public String PageIndex(Model model) {
		serviceUser.getSessionUser(model);
		return "index";
	}

	@GetMapping("/login")
	public String pageLogin() {
		return "login";
	}

	@GetMapping("/logout")
	public String pageLogout() {
		return "login";
	}
}
