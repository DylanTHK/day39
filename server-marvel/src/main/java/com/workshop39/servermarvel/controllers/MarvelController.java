package com.workshop39.servermarvel.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.workshop39.servermarvel.models.MarvelCharacter;
import com.workshop39.servermarvel.services.MarvelService;
import com.workshop39.servermarvel.services.RedisService;

import jakarta.json.Json;
import jakarta.json.JsonArrayBuilder;

@Controller
@RequestMapping(path="/api")
public class MarvelController {
    
    @Autowired
    MarvelService marvelSvc;

    @Autowired
    RedisService redisSvc;

    // to get list of related character(s)
    @GetMapping(path="/characters")
    @ResponseBody
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
    public void getSingleCharacter(@PathVariable Integer id) {
        
        // TODO: 
        // 1. query redis for existing characters

        // 2. if not available, make API call with character id (TBC)

        
    }


    // Controller to save comments
    @PostMapping(path="/character/{id}", 
        consumes=MediaType.APPLICATION_JSON_VALUE, 
        produces=MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody // indicate return value of a method
    public void postComment(
        @PathVariable String id,
        @RequestBody String comment) {
        System.out.println("\n\nReceived ID: " + id);
        System.out.println("Received Comment: " + comment);
        

        // TODO: save comment to mongoDB

    }
}
