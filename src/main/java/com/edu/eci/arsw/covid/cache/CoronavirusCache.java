package com.edu.eci.arsw.covid.cache;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;

import com.edu.eci.arsw.covid.models.Country;

@Service
public class CoronavirusCache {
    HashMap<String, CoronavirusCachePaises> cache;

    HashMap<String, ArrayList<Country>> provincias;

    public CoronavirusCache(){
        cache = new HashMap<>();
        provincias = new HashMap<>();
    }

    public void addNewCountry(String key, Country data){
        CoronavirusCachePaises aux = new CoronavirusCachePaises(data);
        cache.put(key, aux);
    }

    public CoronavirusCachePaises getCache(String key){
        return cache.get(key);
    }

    public void addCacheProv(String key,ArrayList<Country> array){
        provincias.put(key, array);
    }

    public ArrayList<Country> getCacheProv(String key){
        return provincias.get(key);
    }

    public HashMap<String, CoronavirusCachePaises> getCache(){
        return cache;
    }

    public boolean isEmpty(){
        return cache.isEmpty();
    }

}
