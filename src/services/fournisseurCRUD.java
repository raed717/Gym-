/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.fournisseur;
import entities.materiel;
import utils.MyDB;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;


/**
 *
 * @author AMINE
 */
public class fournisseurCRUD {
    Connection cnx2;
    public fournisseurCRUD(){
        cnx2 = MyDB.getInstance().getConnexion();
    }
    public void ajouterFournisseur(fournisseur f){
        try {
            String requete = "INSERT INTO fournisseur (nom,adresse,numtel)" + "VALUES (?,?,?)";
            PreparedStatement pst = cnx2.prepareStatement(requete);
            pst.setString(1, f.getNom());
            pst.setString(2, f.getAdresse());
            pst.setInt(3, f.getNumtel());
            pst.executeUpdate();
            System.out.println("fournisseur ajoutée");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }
     public ObservableList<fournisseur>afficherFournisseur(){
           ObservableList<fournisseur> MyList = FXCollections.observableArrayList();
        try {
            String requete2 = "SELECT * FROM fournisseur";
            Statement st = cnx2.createStatement();
            ResultSet rs = st.executeQuery(requete2);
            while(rs.next()){
                fournisseur f = new fournisseur();
                f.setId(rs.getInt(1));
                f.setNom(rs.getString("nom"));
                f.setAdresse(rs.getString("adresse"));
                f.setNumtel(rs.getInt("numtel"));
                MyList.add(f);
            }
        } catch (SQLException ex) {
        System.err.println(ex.getMessage());
        }
        return MyList;
    }
     public void SupprimerFournisseur(int id){;
        try {
            String requete3 = "DELETE FROM fournisseur WHERE id=?";
            PreparedStatement pst = cnx2.prepareStatement(requete3);
            pst.setInt(1,id);
            pst.executeUpdate();
            System.out.println("fournisseur supprimée");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }
     public void modifierFournisseur(int id,String nom,String adresse,int numtel){
        try {
            String requete4 = " UPDATE fournisseur SET " + " nom = ?, adresse = ?, numtel = ? WHERE id = ? " ;
            PreparedStatement pst = cnx2.prepareStatement(requete4);
            pst.setString(1,nom);
            pst.setString(2,adresse);
            pst.setInt(3,numtel);
            pst.setInt(4,id);
            pst.executeUpdate();
            System.out.println("fournisseur modifiée");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }
     
}
