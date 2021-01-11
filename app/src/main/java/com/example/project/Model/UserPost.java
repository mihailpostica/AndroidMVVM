package com.example.project.Model;

public class UserPost {

    private String data;
    private String titlu;
    private String descriere;
    private Float avgRating;
    private Utilizator utilizator;
    private Categorie categorie;

    public UserPost( String data, String titlu, String descriere, Float avgRating, Utilizator utilizator, Categorie categorie) {
        this.data = data;
        this.titlu = titlu;
        this.descriere = descriere;
        this.avgRating = avgRating;
        this.utilizator = utilizator;
        this.categorie = categorie;
    }



    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getTitlu() {
        return titlu;
    }

    public void setTitlu(String titlu) {
        this.titlu = titlu;
    }

    public String getDescriere() {
        return descriere;
    }

    public void setDescriere(String descriere) {
        this.descriere = descriere;
    }

    public Float getAvgRating() {
        return avgRating;
    }

    public void setAvgRating(Float avgRating) {
        this.avgRating = avgRating;
    }

    public Utilizator getUtilizator() {
        return utilizator;
    }

    public void setUtilizator(Utilizator utilizator) {
        this.utilizator = utilizator;
    }

    public Categorie getCategorie() {
        return categorie;
    }

    public void setCategorie(Categorie categorie) {
        this.categorie = categorie;
    }



}

