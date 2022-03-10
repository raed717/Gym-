/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;
import java.util.Objects;

/**
 *
 * @author firas
 */
public class Categorie {
     private int id_categorie ; 
    private String description_categorie ; 

    public Categorie(int id_categorie, String description_categorie) {
        this.id_categorie = id_categorie;
        this.description_categorie = description_categorie;
    }

    public Categorie(String description_categorie) {
        this.description_categorie = description_categorie;
    
    }

    @Override
    public String toString() {
        return "Categorie{" + "id_categorie=" + id_categorie + ", description_categorie=" + description_categorie + '}';
    }

    public int getId_categorie() {
        return id_categorie;
    }

    public void setId_categorie(int id_categorie) {
        this.id_categorie = id_categorie;
    }

    public String getDescription_categorie() {
        return description_categorie;
    }

    public void setDescription_categorie(String description_categorie) {
        this.description_categorie = description_categorie;
    }
    
}
