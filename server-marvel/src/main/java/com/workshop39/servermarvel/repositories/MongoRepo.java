package com.workshop39.servermarvel.repositories;

import java.sql.Timestamp;
import java.time.Instant;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

import com.workshop39.servermarvel.models.Comment;

import jakarta.json.Json;

@Repository
public class MongoRepo {
    public static final String COLLECTION_COMMENTS = "comments";

    @Autowired
    @Qualifier("mongodb")
    private MongoTemplate mongoTemplate;
    
    // TODO: Insert comment into DB (return inserted comment)
    public Comment insertComment(String id, String comment) {
        Timestamp ts = Timestamp.from(Instant.now());
        Document d = Document.parse(Json.createObjectBuilder()
                    .add("ts", ts.toString())
                    .add("id", id)
                    .add("comment", comment)
                    .build().toString());

        Document inserted = mongoTemplate.insert(d, COLLECTION_COMMENTS);
        return Comment.docToComment(inserted);
    }

    // TODO: Retrieve comment into DB (Top 10 latest comments)
    public void getComments() {

    }
}
