/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.Produit;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import utils.MyDB;

/**
 *
 * @author firas
 */
     public class ProduitService extends Produit{
         
    Connection connexion;
    Statement stm;

    public ProduitService() {
        connexion = MyDB.getInstance().getConnexion();
    }

  
 public void ajouterProduit(Produit p) throws SQLException {
     try{
        String req = "INSERT INTO `produit` (`description`, `name`,`price` ,`id_categorie`) "
                + "VALUES ( ?, ?,?,( SELECT Id_categorie FROM categorie WHERE categorie.id_categorie = ? )) ";
        
      PreparedStatement ps = connexion.prepareStatement(req);
        ps.setString(1, p.getDescription());
        ps.setString(2, p.getName());
        ps.setFloat(3, p.getPrice());
        ps.setInt(4, p.getId_categorie());
        
        ps.executeUpdate();
          } catch (SQLException ex) {
            System.out.println(ex.getMessage()); 
          } 
  
     
 }
public List<Produit> afficherproduit() throws SQLException {
        List<Produit> produits = new ArrayList<>();
        String req = "select * from produit";
        stm = connexion.createStatement();
         ResultSet rst = stm.executeQuery(req);
        while (rst.next()) {
            Produit p = new Produit   (rst.getInt("id"),
                    rst.getString("name"),
                    rst.getString("description"),
                     rst.getFloat("price") ,
                     rst.getInt("id_categorie"));
                      produits.add(p); 
        }
        return produits;
    }

public void SupprimerProduit(int id){
        try {
            String requete3 = "DELETE FROM produit WHERE id=?";
            PreparedStatement pst = connexion.prepareStatement(requete3);
            pst.setInt(1,id);
            pst.executeUpdate();
            System.out.println("produit supprimée");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }
public void modifierProduit(int id,String description,String name,float price ){
        try {
            String requete4 = " UPDATE produit SET " + " description = ?, name = ?, price= ? WHERE id = ? " ;
            PreparedStatement pst = connexion.prepareStatement(requete4);
            pst.setString(1,description);
            pst.setString(2,name);
            pst.setFloat(3,price);
             pst.setInt(4,id);
            pst.executeUpdate();
            System.out.println("produit modifiée");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

public Produit RechecheProduit( int name) throws SQLException   {
                 
  PreparedStatement st = connexion.prepareStatement("select * from produit WHERE `id`=?");
            st.setInt(1, name);
            ResultSet rst = st.executeQuery();
            rst.beforeFirst();
                 while (rst.next()) {
                Produit pr = new Produit (rst.getInt("id"),
                        rst.getString("description"),
                        rst.getString("name"),
                        rst.getFloat("price") ,
                        rst.getInt("id_categorie"));
               
                return pr;
                 }
                return  null ;
        }
   
 public Produit RechecheProduit2( String nom) throws SQLException   {
                 
  PreparedStatement st = connexion.prepareStatement("select * from produit WHERE `name`=?");
            st.setString(1, nom);
            ResultSet rst = st.executeQuery();
            rst.beforeFirst();
                 while (rst.next()) {
                Produit pr = new Produit (rst.getInt("id"),
                        rst.getString("description"),
                        rst.getString("name"),
                        rst.getFloat("price") ,
                        rst.getInt("id_categorie"));
               
                return pr;
                 }
                return  null ;
        }

}



 


    
     
 
 
     

