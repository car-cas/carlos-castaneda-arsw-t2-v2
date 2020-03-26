package com.edu.eci.arsw.covid.services;

import org.springframework.stereotype.Service;
import org.json.JSONObject;
import java.util.HashMap;

import com.edu.eci.arsw.covid.models.Country;

@Service
public interface CoronavirusServices {

    public HashMap<String, Country> getCountries();

    public HashMap<String, Country> getCountry(String country);
}