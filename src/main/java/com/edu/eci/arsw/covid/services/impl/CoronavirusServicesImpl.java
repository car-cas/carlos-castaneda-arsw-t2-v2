package com.edu.eci.arsw.covid.services.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.edu.eci.arsw.covid.models.Country;
import com.edu.eci.arsw.covid.cache.CoronavirusCachePaises;
import com.edu.eci.arsw.covid.cache.CoronavirusCache;
import com.edu.eci.arsw.covid.services.CoronavirusServices;
import com.edu.eci.arsw.covid.services.HttpConnectionServices;

import org.json.JSONArray;
import org.json.JSONObject;
import com.mashape.unirest.http.exceptions.UnirestException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CoronavirusServicesImpl implements CoronavirusServices {

    @Autowired
    HttpConnectionServices httpCS;
    @Autowired
    CoronavirusCache cacheP;
    String paises;

    private void extraccionJson(String data, String tipo, HashMap<String, Country> datos){
        JSONObject json = new JSONObject(data);
        JSONArray jArray = json.getJSONObject("data").getJSONArray("covid19Stats");
        for(int i = 0 ; i < jArray.length(); i++){
            JSONObject aux = jArray.getJSONObject(i);
            if (datos.get(aux.getString(tipo)) != null) {
                if (aux.getString(tipo).equals("")) {
                    if (!aux.getString("city").equals("")) {
                        Country country = datos.get(aux.getString("city"));
                        country.sumConfirmed(aux.getInt("confirmed"));
                        country.sumDeaths(aux.getInt("deaths"));
                        country.sumRecovered(aux.getInt("recovered"));
                    } else {
                        System.out.println(aux.toString());
                        Country country = datos.get(aux.getString("country"));
                        country.sumConfirmed(aux.getInt("confirmed"));
                        country.sumDeaths(aux.getInt("deaths"));
                        country.sumRecovered(aux.getInt("recovered"));
                    }

                } else {
                    Country country = datos.get(aux.getString(tipo));
                    country.sumConfirmed(aux.getInt("confirmed"));
                    country.sumDeaths(aux.getInt("deaths"));
                    country.sumRecovered(aux.getInt("recovered"));
                }
            } else {
                if (aux.getString(tipo).equals("")) {
                    if (!aux.getString("city").equals("")) {
                        Country country = new Country(aux.getString("city"), aux.getInt("confirmed"), aux.getInt("deaths"), aux.getInt("recovered"));
                        datos.put(aux.getString(tipo), country);
                    } else {
                        Country country = new Country(aux.getString("country"), aux.getInt("confirmed"), aux.getInt("deaths"), aux.getInt("recovered"));
                        datos.put(aux.getString(tipo), country);
                    }

                } else {
                    Country country = new Country(aux.getString(tipo), aux.getInt("confirmed"), aux.getInt("deaths"), aux.getInt("recovered"));
                    datos.put(aux.getString(tipo), country);
                }
            }
        }
    }

    @Override
    public HashMap<String, Country> getCountries() {
        HashMap <String, Country> datos = new HashMap<String, Country>();
        if(cacheP.isEmpty()){
            try{
                paises = httpCS.getAllCou();
                extraccionJson(paises, "country", datos);
            }catch(UnirestException e){
                e.printStackTrace();
            }
            for(Map.Entry<String, Country> c : datos.entrySet()){
                cacheP.addNewCountry(c.getKey(), c.getValue());
            }
        }else{
            for(Map.Entry<String, CoronavirusCachePaises> c : cacheP.getCache().entrySet()){
                datos.put(c.getKey(), c.getValue().getData());
            }
        }

        return datos;
    }

    @Override
    public HashMap<String, Country> getCountry(String country)  {
        HashMap <String, Country> datos = new HashMap<String, Country>();

        if(cacheP.getCacheProv(country) == null) {
            try {
                String countryU = country.replaceAll("\\s+", "");
                paises = httpCS.getCountryStats(countryU);
                //System.out.println(paises);
                extraccionJson(paises, "province", datos);

            } catch (UnirestException e) {
                e.printStackTrace();
            }
            ArrayList<Country> array = new ArrayList<Country>();
            for(Map.Entry<String, Country> c : datos.entrySet()){
                array.add(c.getValue());
            }
            cacheP.addCacheProv(country, array);
        } else {
            for(Country c : cacheP.getCacheProv(country)){
                datos.put(c.getCountry(), c);
            }
        }

        return datos;
    }

}