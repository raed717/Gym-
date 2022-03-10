/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

/**
 *
 * @author AMINE
 */
public class fournisseur {
    private int id;
    private String nom;
    private String adresse;
    private int numtel;
    
    public fournisseur() {
    }

    public fournisseur(int id, String nom, String adresse,int numtel) {
        this.id = id;
        this.nom = nom;
        this.adresse = adresse;
        this.numtel = numtel;
    }
public fournisseur(String nom,String adresse, int numtel){
    
        this.nom = nom;
        this.adresse = adresse;
        this.numtel = numtel;
}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }
    public int getNumtel() {
        return numtel;
    }

    public void setNumtel(int numtel) {
        this.numtel = numtel;
    }

    @Override
    public String toString() {
        return "fournisseur{" + "id=" + id + ", nom=" + nom + ", adresse=" + adresse + ", numtel=" + numtel + '}';
    }


    
    
}
