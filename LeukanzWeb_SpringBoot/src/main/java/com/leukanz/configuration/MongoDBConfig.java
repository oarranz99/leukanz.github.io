package com.leukanz.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractMongoClientConfiguration;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;

@Configuration
@EnableMongoRepositories
@EnableTransactionManagement
public class MongoDBConfig extends AbstractMongoClientConfiguration {

	@Override
	public MongoClient mongoClient() {
		ConnectionString connectionString = new ConnectionString(
				"mongodb+srv://LK01Admin:vRXvy5A0qypg23aEm7Lz@lk01.jgr5d.mongodb.net/LK01?retryWrites=true&w=majority");
		MongoClientSettings settings = MongoClientSettings.builder().applyConnectionString(connectionString).build();
		MongoClient mongoClient = MongoClients.create(settings);
		return mongoClient;

	}

	@Bean
	public MongoTemplate mongoTemplate() {
		MongoTemplate mt = new MongoTemplate(mongoClient(), getDatabaseName());
		return mt;
	}

	@Override
	protected String getDatabaseName() {
		// TODO Auto-generated method stub
		return "LK01";
	}

	

	
}
