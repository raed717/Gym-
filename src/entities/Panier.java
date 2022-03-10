/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

/**
 *
 * @author firas
 */
public class Panier {
    private int id_p ; 
     private float prix; 
    private int quantite; 
    private int id ; 

    public Panier(int id_p, float prix, int quantite, int id) {
        this.id_p = id_p;
        this.prix = prix;
        this.quantite = quantite;
        this.id = id;
    }

    public Panier(float prix, int quantite, int id) {
        this.prix = prix;
        this.quantite = quantite;
        this.id = id;
    }

    public int getId_p() {
        return id_p;
    }

    public void setId_p(int id_p) {
        this.id_p = id_p;
    }

    public float getPrix() {
        return prix;
    }

    public void setPrix(float prix) {
        this.prix = prix;
    }

    public int getQuantite() {
        return quantite;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Panier{" + "id_p=" + id_p + ", prix=" + prix + ", quantite=" + quantite + ", id=" + id + '}';
    }

}