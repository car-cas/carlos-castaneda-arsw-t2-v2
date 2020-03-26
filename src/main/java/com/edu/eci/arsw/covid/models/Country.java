package com.edu.eci.arsw.covid.models;

public class Country{


    private String country;
    private int confirmed;
    private int deaths;
    private int recovered;

    public Country(){

    }

    public Country(String country, int confirmed, int deaths, int recovered){
        this.country = country;
        this.confirmed = confirmed;
        this.deaths = deaths;
        this.recovered = recovered;
    }

    public void sumConfirmed(int data){
        this.confirmed += data;
    }

    public void sumDeaths(int data){
        this.deaths += data;
    }

    public void sumRecovered(int data){
        this.recovered += data;
    }

    public String getCountry() {
        return country;
    }

    public int getConfirmed() {
        return confirmed;
    }

    public int getDeaths() {
        return deaths;
    }

    public int getRecovered() {
        return recovered;
    }

}