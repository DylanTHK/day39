package com.workshop39.servermarvel.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.workshop39.servermarvel.models.MarvelCharacter;
import com.workshop39.servermarvel.repositories.RedisRepo;

@Service
public class RedisService {

    @Autowired
    RedisRepo redisRepo;

    @Autowired
    MarvelService marvelSvc;

    public void saveToRedis(List<MarvelCharacter> listMc) {
        redisRepo.save(listMc);
    }

    public MarvelCharacter getCharacter(Integer id) {
        // check if any value stored in redis
        MarvelCharacter mc = getCharFromRedis(id.toString());
        System.out.println("MarvelCharacter Retrieved: " + mc);
        
        // make API call from marvelSvc
        if (null == mc) {
            Optional<MarvelCharacter> opt = marvelSvc.getMarvelCharacter(id);
            if(!opt.isEmpty()) {
                return opt.get();
            }
            return null;
        }
        return mc;
    }

    public MarvelCharacter getCharFromRedis(String id) {
        Optional<MarvelCharacter> opt = redisRepo.get(id);
        if (opt.isEmpty()) {
            return null;
        }
        return opt.get();
    }
}
