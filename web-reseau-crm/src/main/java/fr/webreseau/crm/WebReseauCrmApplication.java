package fr.webreseau.crm;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.support.SpringBootServletInitializer;

@SpringBootApplication
public class WebReseauCrmApplication extends SpringBootServletInitializer{

	public static void main(String[] args) {
		SpringApplication.run(WebReseauCrmApplication.class, args);
	}
}
