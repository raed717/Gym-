/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import tn.esprit.model.Categorie;
import tn.esprit.utils.MyDB;

 
 import tn.esprit.model.Categorie;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import tn.esprit.utils.MyDB;


/**
 *
 * @author firas
 */
public class CategorieService implements ICategorie<Categorie> {
    Connection connexion;
    Statement stm;

    public CategorieService() {
        connexion = MyDB.getInstance().getConnexion();
    }

     

    @Override
    public void ajouterc(Categorie p) throws SQLException {
        
             try{
        String req = "INSERT INTO `categorie` ( `description_categorie` ) "
                + "VALUES ( ?) ";
        
        PreparedStatement ps = connexion.prepareStatement(req);
        ps.setString(1, p.getDescription_categorie());
        ps.executeUpdate();
          } catch (SQLException ex) {
            System.out.println(ex.getMessage()); 
          } 
     }

     public List<Categorie> afficherc() throws SQLException {
        List<Categorie> categories = new ArrayList<>();
        String req = "select * from categorie";
        stm = connexion.createStatement();
         ResultSet rst = stm.executeQuery(req);
        while (rst.next()) {
            Categorie c= new Categorie   (rst.getInt("id_categorie"),
                     rst.getString("description_categorie"));
             categories.add(c);
        }
        return categories;
    }
    public void SupprimerCategorie(int id_categorie){;
        try {
            String requette4 = "DELETE FROM categorie WHERE id_categorie=?";
            PreparedStatement pst = connexion.prepareStatement(requette4);
            pst.setInt(1,id_categorie);
            pst.executeUpdate();
            System.out.println("categorie supprimée");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    
    }
    public void modifierCategorie(int id_categorie,String description_categorie ){
        try {
            String requette5 = " UPDATE categorie SET " + " description_categorie = ?  WHERE id_categorie = ? " ;
            PreparedStatement pst = connexion.prepareStatement(requette5);
            pst.setString(1,description_categorie);
              pst.setInt(2,id_categorie);
            pst.executeUpdate();
            System.out.println("categorie modifiée");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }
}