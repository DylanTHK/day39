package com.workshop39.servermarvel.repositories;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.aggregation.LimitOperation;
import org.springframework.data.mongodb.core.aggregation.MatchOperation;
import org.springframework.data.mongodb.core.aggregation.SortOperation;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.stereotype.Repository;

import com.workshop39.servermarvel.models.Comment;

import jakarta.json.Json;

@Repository
public class MongoRepo {

    private final String COLLECTION_COMMENTS = "comments";

    @Autowired
    private MongoTemplate mongoTemplate;
    
    // Insert comment into DB (return inserted comment)
    public Comment insertComment(Integer id, String comment) {
        Timestamp ts = Timestamp.from(Instant.now());
        Document d = Document.parse(Json.createObjectBuilder()
                    .add("ts", ts.toString())
                    .add("id", id)
                    .add("comment", comment)
                    .build().toString());

        Document inserted = mongoTemplate.insert(d, COLLECTION_COMMENTS);
        return Comment.docToComment(inserted);
    }

    // Retrieve comment into DB (Top 10 latest comments)
    public List<Comment> getComments(Integer id) {
        // db.comments.aggregate([
        //     {$match: {id: 1017603}},
        //     {$sort: {ts: -1}},
        //     {$limit: 10}
        // ]);
        // prepare operation
        MatchOperation matchId = Aggregation.match(Criteria.where("id").is(id));
        SortOperation sortByTs = Aggregation.sort(Sort.by(Direction.DESC, "ts"));
        LimitOperation limitBy = Aggregation.limit(10);
        // prepare pipeline
        Aggregation pipeline = Aggregation.newAggregation(matchId, sortByTs, limitBy);
        // get results from mongo
        AggregationResults<Document> results = mongoTemplate.aggregate(pipeline, COLLECTION_COMMENTS, Document.class);

        List<Document> resultList = results.getMappedResults();

        List<Comment> listComment = new ArrayList<>();

        // convert doc -> comment
        // resultList.stream()
        //     .map(Comment::docToComment)
        //     .forEach(c -> listComment.add(c));

        resultList.stream()
            .map(d -> Comment.docToComment(d))
            .forEach(c -> listComment.add(c));
        
        return listComment;
    }
}
