package com.edu.eci.arsw.covid.services;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.springframework.stereotype.Service;

@Service
public class HttpConnectionServices {

    /**
     * Conexión con el API covid-19-coronavirus-statistics
     * @param country Nombre del pais
     * @return String con los datos del pais buscado
     * @throws UnirestException Control de excepciones
     */
    public String getCountryStats(String country) throws UnirestException{
        HttpResponse<String> response = Unirest.get("https://covid-19-coronavirus-statistics.p.rapidapi.com/v1/stats?country="+ country)
                .header("x-rapidapi-host", "covid-19-coronavirus-statistics.p.rapidapi.com")
                .header("x-rapidapi-key", "46ad70a720msh7d753a721a48e85p13a416jsnef19b9788ec7")
                .asString();
        return response.getBody();
    }

    /**
     * Conexión con el API covid-19-coronavirus-statistics
     * @return String con los datos todos los paises
     * @throws UnirestException Control de excepciones
     */
    public String getAllCou() throws UnirestException{
        HttpResponse<String> response = Unirest.get("https://covid-19-coronavirus-statistics.p.rapidapi.com/v1/stats")
                .header("x-rapidapi-host", "covid-19-coronavirus-statistics.p.rapidapi.com")
                .header("x-rapidapi-key", "46ad70a720msh7d753a721a48e85p13a416jsnef19b9788ec7")
                .asString();
        return response.getBody();
    }


}
