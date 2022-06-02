package com.example.cc2;

import java.io.Serializable;

public class Entreprise implements Serializable {
    private int ID;
    private String RaisonSociale;

    public Entreprise() {
    }

    public Entreprise(int ID, String raisonSociale, String adresse, Double capitale) {
        this.ID = ID;
        RaisonSociale = raisonSociale;
        Adresse = adresse;
        Capitale = capitale;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getRaisonSociale() {
        return RaisonSociale;
    }

    public void setRaisonSociale(String raisonSociale) {
        RaisonSociale = raisonSociale;
    }

    public String getAdresse() {
        return Adresse;
    }

    public void setAdresse(String adresse) {
        Adresse = adresse;
    }

    public Double getCapitale() {
        return Capitale;
    }

    public void setCapitale(Double capitale) {
        Capitale = capitale;
    }

    private String Adresse;
    private Double Capitale;
}
