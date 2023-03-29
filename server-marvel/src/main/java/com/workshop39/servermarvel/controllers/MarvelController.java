package com.workshop39.servermarvel.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.workshop39.servermarvel.MD5Utils;
import com.workshop39.servermarvel.services.MarvelService;

@Controller
@RequestMapping(path="/api")
public class MarvelController {
    
    @Autowired
    MarvelService marvelSvc;

    @GetMapping(path="/")
    @ResponseBody
    public ResponseEntity<String> getCharacters(
        @RequestParam String name, 
        @RequestParam(defaultValue="1") Integer ts) {
        
        marvelSvc.getMarvelCharacters(name, ts);

        return ResponseEntity.ok("Yes");


    }
}
