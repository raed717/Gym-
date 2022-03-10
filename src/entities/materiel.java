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
public class materiel {
    private int id;
    private String nom;
    private float prix;
    private String etat; 

    public materiel() {
    }

    
    public materiel(int id, String nom , float prix , String etat) {
        this.id = id;
        this.nom = nom;
        this.prix = prix;
        this.etat = etat;
    }

    public materiel(String nom, float prix , String etat) {
        this.nom = nom;
        this.prix = prix;
        this.etat = etat;
    }

    public float getPrix() {
        return prix;
    }

    public void setPrix(float prix) {
        this.prix = prix;
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

    public String getEtat() {
        return etat;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }

    @Override
    public String toString() {
        return "materiel{" + "id=" + id + ", nom=" + nom + ", prix=" + prix + ", etat=" + etat + '}';
    }
}

