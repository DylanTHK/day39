package com.workshop39.servermarvel.controllers;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.workshop39.servermarvel.models.MarvelCharacter;
import com.workshop39.servermarvel.models.Comment;
import com.workshop39.servermarvel.services.MarvelService;
import com.workshop39.servermarvel.services.MongoService;
import com.workshop39.servermarvel.services.RedisService;

import jakarta.json.Json;
import jakarta.json.JsonArrayBuilder;

@RestController
@RequestMapping(path="/api")
public class MarvelController {
    
    @Autowired
    private MarvelService marvelSvc;

    @Autowired
    private RedisService redisSvc;

    @Autowired
    private MongoService mongoSvc;

    // to get list of related character(s)
    @GetMapping(path="/characters")
    public ResponseEntity<String> getCharacters(
        @RequestParam String name, 
        @RequestParam(defaultValue="1") Integer ts,
        @RequestParam(defaultValue="5") Integer limit,
        @RequestParam(defaultValue="0") Integer offset) {
        
        List<MarvelCharacter> listMC =
            marvelSvc.getMarvelCharacters(name, ts, limit, offset);
        
        // save characters to redis
        if (listMC.size() >= 0)
            redisSvc.saveToRedis(listMC);
        
        System.out.println("\n\nLIST of Marvel Char: " + listMC);

        // convert List<MarvelCharacter> to JsonArray 
        JsonArrayBuilder arrayBuilder = Json.createArrayBuilder();
        // MarvelCharacter -> JsonObject, push to array builder
        listMC.stream()
            .forEach(mc -> {
                arrayBuilder.add(mc.toJson());
            });
        
        return ResponseEntity
                .status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(arrayBuilder.build().toString());
    }

    // Controller to retrieve ONE character
    @GetMapping(path="/character/{id}")
    public ResponseEntity<String> getSingleCharacter(@PathVariable Integer id) {
        // 1. query redis for existing characters
        MarvelCharacter mc = redisSvc.getCharacter(id);

        if (null != mc) {
            List<MarvelCharacter> list = new ArrayList<>();
            list.add(mc);
            redisSvc.saveToRedis(list);
            return ResponseEntity
                .status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(mc.toJson().toString());
        }
        String message = Json.createObjectBuilder()
            .add("message", "Invalid Character ID")
            .add("error","BAD REQUEST")
            .build().toString();
        return ResponseEntity
            .status(HttpStatus.BAD_REQUEST)
            .contentType(MediaType.APPLICATION_JSON)
            .body(message);
    }


    // Controller to save comments & TODO:return inserted comment
    @PostMapping(path="/character/{id}")
    public ResponseEntity<String> addComment(
        @PathVariable String id,
        @RequestBody String comment) {

        Comment c = mongoSvc.saveComment(id, comment);
        
        System.out.println("\n\n INSIDE addComment: " + c);
        return ResponseEntity
                .status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(c.toJson().toString());
    }
}
