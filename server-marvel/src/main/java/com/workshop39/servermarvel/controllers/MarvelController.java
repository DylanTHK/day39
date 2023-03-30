package com.workshop39.servermarvel.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.workshop39.servermarvel.MD5Utils;
import com.workshop39.servermarvel.models.MarvelCharacter;
import com.workshop39.servermarvel.services.MarvelService;

import jakarta.json.Json;
import jakarta.json.JsonArrayBuilder;

@Controller
@RequestMapping(path="/api")
public class MarvelController {
    
    @Autowired
    MarvelService marvelSvc;

    @GetMapping(path="/")
    @ResponseBody
    public ResponseEntity<String> getCharacters(
        @RequestParam String name, 
        @RequestParam(defaultValue="1") Integer ts,
        @RequestParam(defaultValue="5") Integer limit,
        @RequestParam(defaultValue="0") Integer offset) {
        
        List<MarvelCharacter> listMC =
            marvelSvc.getMarvelCharacters(name, ts, limit, offset);
        
        System.out.println("\n\nLIST of Marvel Char: " + listMC);

        // convert List<MarvelCharacter> to JsonArray 
        JsonArrayBuilder arrayBuilder = Json.createArrayBuilder();
        // MarvelCharacter -> JsonObject, push to array builder
        listMC.stream()
            .forEach(mc -> {
                arrayBuilder.add(mc.toJson());
            });
        
        // FIXME:
        return ResponseEntity
                .status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(arrayBuilder.build().toString());
    }
}
