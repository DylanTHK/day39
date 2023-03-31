package com.workshop39.servermarvel.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.workshop39.servermarvel.models.MarvelCharacter;
import com.workshop39.servermarvel.repositories.RedisRepo;

@Service
public class RedisService {
    

    @Autowired
    RedisRepo redisRepo;

    public void saveToRedis(List<MarvelCharacter> listMc) {
        redisRepo.save(listMc);
    }
}
