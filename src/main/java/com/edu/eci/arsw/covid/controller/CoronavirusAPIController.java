package com.edu.eci.arsw.covid.controller;

import java.util.logging.Level;
import java.util.logging.Logger;

import com.edu.eci.arsw.covid.services.CoronavirusServices;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/countries")
public class CoronavirusAPIController {
    @Autowired
    CoronavirusServices cServices;


    /**
     * Endpoint para consultar un pais
     * @param name Nombre de pais a consultar
     * @return Datos desde el API del pais bucado
     */
    @RequestMapping(value = "/{name}", method = RequestMethod.GET)
    public ResponseEntity<?> getCasesByCountry(@PathVariable ("name") String name){
        try {
            return new ResponseEntity<>(cServices.getCountry(name), HttpStatus.OK);
        } catch (Exception ex) {
            Logger.getLogger(CoronavirusAPIController.class.getName()).log(Level.SEVERE, null, ex);
            return new ResponseEntity<>("Not found", HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Endpoint para consultar todos los paises
     * @return Los datos de todos los pasis en el API
     */
    @RequestMapping(value = "/getAll")
    public ResponseEntity<?> getAllCases(){
        try {
            return new ResponseEntity<>(cServices.getCountries(), HttpStatus.OK);
        } catch (Exception ex) {
            Logger.getLogger(CoronavirusAPIController.class.getName()).log(Level.SEVERE, null, ex);
            return new ResponseEntity<>("Not found", HttpStatus.NOT_FOUND);
        }

    }
}