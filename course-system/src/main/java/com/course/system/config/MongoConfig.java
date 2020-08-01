package com.course.system.config;

import com.mongodb.MongoClientSettings;
import com.mongodb.reactivestreams.client.MongoClient;
import com.mongodb.reactivestreams.client.MongoClients;
import org.bson.UuidRepresentation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractReactiveMongoConfiguration;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories;

@Configuration
@EnableReactiveMongoRepositories(basePackages = "com.course.system.dao")
public class MongoConfig extends AbstractReactiveMongoConfiguration {
    @Value("${port}")
    private String port;

    @Value("${dbname}")
    private String dbName;

    @Override
    public MongoClient reactiveMongoClient() {
        return MongoClients.create(mongoClientSettings());
    }

    @Bean
    protected MongoClientSettings mongoClientSettings() {
        MongoClientSettings.Builder builder = MongoClientSettings.builder();
        builder.uuidRepresentation(UuidRepresentation.JAVA_LEGACY);
        this.configureClientSettings(builder);
        return builder.build();
    }

    @Override
    protected String getDatabaseName() {
        return dbName;
    }

    @Bean
    public ReactiveMongoTemplate reactiveMongoTemplate() {
        return new ReactiveMongoTemplate(reactiveMongoClient(), getDatabaseName())                ;
    }
}