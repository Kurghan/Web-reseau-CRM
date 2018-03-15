package fr.webreseau.crm.security;


import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled =true)
public class SecurityConfig extends WebSecurityConfigurerAdapter{

	@Autowired
	public void globalConfig(AuthenticationManagerBuilder auth , DataSource datasource) throws Exception {
		/*auth.inMemoryAuthentication().withUser("admin").password("123").roles("ADMIN");
		auth.inMemoryAuthentication().withUser("user").password("456").roles("USER");*/
		auth.jdbcAuthentication()
			.dataSource(datasource)
				.usersByUsernameQuery("select MAilPerson, PasswordPerson , enable from Person where MAilPerson=?")
					.authoritiesByUsernameQuery("select MailPerson , rolePerson from Person where MailPerson=?");
			
	}
	
	protected void configure(HttpSecurity http) throws Exception{
		http
		.authorizeRequests()
        .antMatchers(
                "/bootstrap/**",
                "/css/**",
                "/img/**",
                "/fonts/**",
                "/js/**").permitAll()
					        .anyRequest()
					        		.authenticated()
					        			.and()
		.csrf().disable()
			.authorizeRequests()
				.anyRequest()
					.authenticated()
						.and()
				.formLogin()
					.loginPage("/login")
						.permitAll()
							.defaultSuccessUrl("/" ,true);
	}
}