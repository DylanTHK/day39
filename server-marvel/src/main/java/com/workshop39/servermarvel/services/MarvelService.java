package com.workshop39.servermarvel.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.workshop39.servermarvel.models.MarvelCharacter;
import com.workshop39.servermarvel.MD5Utils;

@Service
public class MarvelService {
    
    private String MARVEL_URL = "https://gateway.marvel.com/v1/public/characters";
    private String PUBLIC_KEY = "f886f6f8de8dcc526df360bf71f33db4";
    private String PRIVATE_KEY = "f886f6f8de8dcc526df360bf71f33db4";
        

    public List<MarvelCharacter> getMarvelCharacters(String name, Integer ts) {

        String hashString = ts + PRIVATE_KEY + PUBLIC_KEY;
        
        String hashKey = MD5Utils.stringToHash(hashString);
        
        System.out.println("\n\nMarvelSvc hashString: " + hashString);
        System.out.println("\n\nMarvelSvc hashKey: " + hashKey);
        return null;
    }


}
