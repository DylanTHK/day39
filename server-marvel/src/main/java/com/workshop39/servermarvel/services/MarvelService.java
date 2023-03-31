package com.workshop39.servermarvel.services;

import java.io.StringReader;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.workshop39.servermarvel.models.MarvelCharacter;
import com.workshop39.servermarvel.repositories.RedisRepo;

import jakarta.json.Json;
import jakarta.json.JsonArray;
import jakarta.json.JsonObject;
import jakarta.json.JsonReader;

import com.workshop39.servermarvel.MD5Utils;

@Service
public class MarvelService {
    
    private String MARVEL_URL = "https://gateway.marvel.com/v1/public/characters";
    private String PUBLIC_KEY = "f886f6f8de8dcc526df360bf71f33db4";
    private String PRIVATE_KEY = "30b07410754152bf9e5b25f2d71f7c5a800e08d4";
    
    @Autowired
    RedisRepo redisRepo;

    // public JsonArray getMarvelCharacters(
    public List<MarvelCharacter> getMarvelCharacters(
        String name, Integer ts, Integer limit, Integer offset) {

        String hashString = ts + PRIVATE_KEY + PUBLIC_KEY;
        String hashKey = MD5Utils.stringToHash(hashString);
        
        // System.out.println("\n\nMarvelSvc hashString: " + hashString);
        // System.out.println("\n\nMarvelSvc hashKey: " + hashKey);

        String url = UriComponentsBuilder.fromUriString(MARVEL_URL)
            .queryParam("ts", ts)
            .queryParam("apikey", PUBLIC_KEY)
            .queryParam("hash", hashKey)
            .queryParam("nameStartsWith", name)
            .queryParam("limit", limit)
            .queryParam("offset", offset)
            .build().toString();

        System.out.println("\n\n URL: " + url);

        // Formatting Request headers
        RequestEntity<Void> req = RequestEntity.get(url)
            .accept(MediaType.APPLICATION_JSON)
            .build();

        // Initialise template and entity
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> resp = null;

        // Make API Call
        try {
            resp = restTemplate.exchange(req, String.class);
        } catch(RestClientException e) {
            e.printStackTrace();
            // return Collections.emptyList();
        }
        
        // get body -> stringReader -> createrReader
        JsonReader reader = Json.createReader(new StringReader(resp.getBody()));
        JsonArray jsonArray = reader.readObject()
            .getJsonObject("data")
            .getJsonArray("results");
        
        // convert Json Objects to List of MarvelCharacters
        return jsonArray.stream()
            .map(c -> c.asJsonObject())
            .map(MarvelCharacter::toMarvelChar)
            .toList();
        
    }


}
