package com.workshop39.servermarvel.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;

import static com.workshop39.servermarvel.Constants.*;

@Configuration
public class MongoConfig {
    
    @Value("${mongo.url}")
    private String connectionString;

    @Bean("mongodb")
    public MongoTemplate mongoTemplate() {
        MongoClient client = MongoClients.create(connectionString);
        return new MongoTemplate(client, DB_MARVEL);
    }
}
