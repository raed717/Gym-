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
public class Produit {
    private int id; 
     private String description , name;
     private float price ; 
     private int id_categorie ;  

    public Produit() {
    }

    public Produit(int id, String description, String name, float price, int id_categorie) {
        this.id = id;
        this.description = description;
        this.name = name;
        this.price = price;
        this.id_categorie = id_categorie;
    }

    public Produit(String description, String name, float price, int id_categorie) {
        this.description = description;
        this.name = name;
        this.price = price;
        this.id_categorie = id_categorie;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public int getId_categorie() {
        return id_categorie;
    }

    public void setId_categorie(int id_categorie) {
        this.id_categorie = id_categorie;
    }

    @Override
    public String toString() {
        return "Produit{" + "id=" + id + ", description=" + description + ", name=" + name + ", price=" + price + ", id_categorie=" + id_categorie + '}';
    }

}