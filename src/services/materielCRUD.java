/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.materiel;
import java.util.List;
import utils.MyDB;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author AMINE
 */
public class materielCRUD {
    Connection cnx2;
    public materielCRUD(){
    cnx2 = MyDB.getInstance().getConnexion();
}
    public void ajouterMateriel(){
        try {
            String requete = "INSERT INTO materiel (nom,etat)" + "VALUES ('makina','bon')";
            //objet qui va ramener la requete vers l sjbd w yekhou il resultat
            Statement st = cnx2.createStatement();
            st.executeUpdate(requete);
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
               
    }
    public void ajouterMateriel2(materiel m){
        try {
            String requete2 = "INSERT INTO materiel (nom,prix,etat)" + "VALUES (?,?,?)";
            PreparedStatement pst = cnx2.prepareStatement(requete2);
            pst.setString(1, m.getNom());
            pst.setFloat(2, m.getPrix());
            pst.setString(3, m.getEtat());
            pst.executeUpdate();
            System.out.println("materiel ajoutée");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }
    public ObservableList<materiel>afficherMateriel(){
           ObservableList<materiel> MyList = FXCollections.observableArrayList();
        try {
            String requete3 = "SELECT * FROM materiel";
            Statement st = cnx2.createStatement();
            ResultSet rs = st.executeQuery(requete3);
            while(rs.next()){
                materiel m = new materiel();
                m.setId(rs.getInt(1));
                m.setNom(rs.getString("nom"));
                m.setPrix(rs.getFloat("prix"));
                m.setEtat(rs.getString("etat"));
                MyList.add(m);
            }
        } catch (SQLException ex) {
        System.err.println(ex.getMessage());
        }
        return MyList;
    }
    public void SupprimerMateriel(int id){;
        try {
            String requete4 = "DELETE FROM materiel WHERE id=?";
            PreparedStatement pst = cnx2.prepareStatement(requete4);
            pst.setInt(1,id);
            pst.executeUpdate();
            System.out.println("materiel supprimée");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }
    public void modifierMateriel(int id , String nom , float prix , String etat){
        try {
            String requete5 = " UPDATE materiel SET " + " nom = ? , prix = ? , etat = ? WHERE id = ? " ;
            PreparedStatement pst = cnx2.prepareStatement(requete5);
            pst.setString(1,nom);
            pst.setFloat(2,prix);
            pst.setString(3,etat);
            pst.setInt(4,id);
            pst.executeUpdate();
            System.out.println("materiel modifiée");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }
}
