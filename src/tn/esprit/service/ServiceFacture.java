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
import tn.esprit.model.Produit;
import tn.esprit.utils.MyDB;
import tn.esprit.model.Produit;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import tn.esprit.model.Facture;
import tn.esprit.utils.MyDB;


/**
 *
 * @author firas
 */
public class ServiceFacture extends Produit{
         
    Connection connexion;
    Statement stm;

    public ServiceFacture() {
        connexion = MyDB.getInstance().getConnexion();
    }
    
    
 public void AjouterFacture(Facture f) throws SQLException {
     try{
        String req = "INSERT INTO `facture` ( `id`,`total` ) "
                + "VALUES ( ( SELECT id FROM produit WHERE produit.id = ?),?) ";
        
      PreparedStatement ps = connexion.prepareStatement(req);
        ps.setInt(1, f.getId());
        ps.setFloat(2, f.getTotal());
        ps.executeUpdate();
          } catch (SQLException ex) {
            System.out.println(ex.getMessage()); 
          } 
 }
      public Facture RechecheFacture( int id) throws SQLException   {
                 
  PreparedStatement st = connexion.prepareStatement("select * from facture WHERE `id`=?");
            st.setInt(1, id);
            ResultSet rst = st.executeQuery();
            rst.beforeFirst();
                 while (rst.next()) {
                Facture f = new Facture (rst.getInt("id_f"),
                rst.getInt("id"),
                rst.getFloat("total"));
               
                return f;
                 }
                return  null ;

 }}