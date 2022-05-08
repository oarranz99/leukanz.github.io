package com.leukanz;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = {"com.leukanz.jpa.repository"} )
@EnableMongoRepositories(basePackages = {"com.leukanz.mongo.repository"} )
@EntityScan(basePackages = {"com.leukanz.domain"})
public class LeukanzWebSpringBootApplication {

	public static void main(String[] args) throws Exception {
		SpringApplication.run(LeukanzWebSpringBootApplication.class, args);
	}
}
 