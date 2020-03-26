package com.edu.eci.arsw.covid.cache;

import com.edu.eci.arsw.covid.models.Country;

import java.time.LocalDateTime;

public class CoronavirusCachePaises {

    public LocalDateTime creationTime;
    private Country data;

    public CoronavirusCachePaises(Country data){
        this.data = data;
        creationTime = LocalDateTime.now();
    }

    public Country getData(){
        return data;
    }
}
