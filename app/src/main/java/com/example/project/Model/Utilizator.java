package com.example.project.Model;
public class Utilizator {
    public Utilizator( String nume, String prenume, String imagine) {
        this.nume = nume;
        this.prenume = prenume;
        this.imagine = imagine;
    }

    private String nume;
    private String prenume;
    private String imagine;

    public String getNume() {
        return nume;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public String getPrenume() {
        return prenume;
    }

    public void setPrenume(String prenume) {
        this.prenume = prenume;
    }

    public String getImagine() {
        return imagine;
    }

    public void setImagine(String imagine) {
        this.imagine = imagine;
    }
}
