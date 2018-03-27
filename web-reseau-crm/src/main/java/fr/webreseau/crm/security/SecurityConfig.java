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
				.usersByUsernameQuery("select MailPerson, PasswordPerson , enable from person where MailPerson=?")
					.authoritiesByUsernameQuery("select MailPerson , rolePerson from person where MailPerson=?");
			
	}
	
	protected void configure(HttpSecurity http) throws Exception{
		http
		.authorizeRequests()
        .antMatchers(
        		"/css/**",
        		"/bootstrap/**",
        		"/img/**",
        		"/fonts/**"
        		).permitAll()
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