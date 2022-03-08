/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.model;

/**
 *
 * @author firas
 */
public class Facture {
    
    private int id_facture ; 
    private int id ; 
    private float total ; 

    public Facture(int id_facture, int id, float total) {
        this.id_facture = id_facture;
        this.id = id;
        this.total = total;
    }

    public Facture(int id, float total) {
        this.id = id;
        this.total = total;
    }

    public int getId_facture() {
        return id_facture;
    }

    public void setId_facture(int id_facture) {
        this.id_facture = id_facture;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public float getTotal() {
        return total;
    }

    public void setTotal(float total) {
        this.total = total;
    }
    
}
