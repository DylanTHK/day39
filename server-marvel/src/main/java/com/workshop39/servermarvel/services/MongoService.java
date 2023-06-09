package com.workshop39.servermarvel.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.workshop39.servermarvel.models.Comment;
import com.workshop39.servermarvel.repositories.MongoRepo;

@Service
public class MongoService {
    
    @Autowired
    private MongoRepo mongoRepo;

    // save comment in MongoRepo
    public Comment saveComment(Integer id, String comment) {
        Comment c = mongoRepo.insertComment(id, comment);
        return c;
    }

    public List<Comment> getComments(Integer id) {
        return mongoRepo.getComments(id);
    }
    
}
