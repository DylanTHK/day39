package com.workshop39.servermarvel.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;

@Configuration
public class MongoConfig {
    
    @Value("${spring.data.mongodb.url}")
    private String connectionString;

    @Value("${spring.data.mongodb.database}")
    private String DB_MARVEL;

    @Bean("mongodb")
    public MongoTemplate mongoTemplate() {
        MongoClient client = MongoClients.create(connectionString);
        return new MongoTemplate(client, DB_MARVEL);
    }
}
