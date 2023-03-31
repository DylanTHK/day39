package com.workshop39.servermarvel.repositories;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Repository;

import com.workshop39.servermarvel.models.MarvelCharacter;

import jakarta.json.Json;
import jakarta.json.JsonArray;
import jakarta.json.JsonArrayBuilder;
import jakarta.json.JsonObject;
import jakarta.json.JsonReader;

@Repository
public class RedisRepo {
    
    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    // save multiple MarvelCharacter into Redis (with id as key) + key expiry: 60min
    public void save(List<MarvelCharacter> listMc) {
        // check if id exists in redis
        listMc.stream()
            .forEach(mc -> {
                String id = mc.getId().toString();
                if(!redisTemplate.hasKey(id)) {
                    ValueOperations<String, String> valueOps = redisTemplate.opsForValue();
                    valueOps.set(id, mc.toJson().toString(), 60, TimeUnit.MINUTES);
                }
            });
    }

    // TODO: check code block is valid
    public Optional<MarvelCharacter> get(String id) {
        // Query MarvelCharacter by id
        String json = redisTemplate.opsForValue().get(id);
        if ((null == json) || (json.trim().length() <= 0))
            return Optional.empty();
        
        JsonObject obj = Json.createReader(new StringReader(json)).readObject();
		return Optional.of(MarvelCharacter.toMarvelChar(obj));
    }
}
