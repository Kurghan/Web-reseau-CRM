package fr.webreseau.crm.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;
import java.util.Locale;

@Configuration
public class AppConfig extends WebMvcConfigurerAdapter {
 
   @Bean
   public LocaleResolver localeResolver() {
       SessionLocaleResolver sessionLocaleResolver = new SessionLocaleResolver();
       sessionLocaleResolver.setDefaultLocale(Locale.FRENCH);
       return sessionLocaleResolver;
   }
 
   @Bean
   public LocaleChangeInterceptor localeChangeInterceptor() {
       LocaleChangeInterceptor lci = new LocaleChangeInterceptor();
       lci.setParamName("lang");
       return lci;
   }
 
   @Override
   public void addInterceptors(InterceptorRegistry registry) {
       registry.addInterceptor(localeChangeInterceptor());
   }
   
   @Bean
   ReloadableResourceBundleMessageSource messageSource() {
       ReloadableResourceBundleMessageSource bundleMessageSource = new ReloadableResourceBundleMessageSource();
       bundleMessageSource.setBasename("classpath:i18n/messages");
       bundleMessageSource.setCacheSeconds(1800);
       bundleMessageSource.setAlwaysUseMessageFormat(true);
       bundleMessageSource.setUseCodeAsDefaultMessage(true);
       return bundleMessageSource;
   }
}
