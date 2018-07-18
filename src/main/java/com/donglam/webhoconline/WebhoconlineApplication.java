package com.donglam.webhoconline;

import javax.persistence.EntityManagerFactory;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.orm.jpa.vendor.HibernateJpaSessionFactoryBean;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableAutoConfiguration
@SpringBootApplication
@EnableScheduling
public class WebhoconlineApplication {
	// auto config .properties
	/*
	 * @Bean public SessionFactory sessionFactory(HibernateEntityManagerFactory
	 * hemf){ return hemf.getSessionFactory(); }
	 */
	
	@Bean
	public HibernateJpaSessionFactoryBean sessionFactory(EntityManagerFactory emf) {
		HibernateJpaSessionFactoryBean fact = new HibernateJpaSessionFactoryBean();
		fact.setEntityManagerFactory(emf);
		return fact;
	}
	public static void main(String[] args) {
		SpringApplication.run(WebhoconlineApplication.class, args);
	}
}
