/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.Client;
import java.io.*;
import java.io.BufferedWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

import utils.MyDB;

/**
 *
 * @author macbook
 */
public class ClientService implements IClient<Client> {

    Connection connexion;
    Statement stm;

    public ClientService() {
        connexion = MyDB.getInstance().getConnexion();
    }

    @Override
    public void ajouterClient(Client p) throws SQLException {
        try {
            String req = "INSERT INTO `Client` (`nom`, `prenom`, `adresse`, `mail`,`mdp_client`, `id_abonnement`) "
                    + "VALUES (?,?,?,?,?,(SELECT id_abonnement from abonnement WHERE abonnement.id_abonnement=?)) ";
            PreparedStatement ps = connexion.prepareStatement(req);
            ps.setString(1, p.getNom());
            ps.setString(2, p.getPrenom());
            ps.setString(3, p.getAdresse());
            ps.setString(4, p.getMail());
            ps.setString(5, p.getMdp_client());
            ps.setInt(6, p.getId_abonnement());
            ps.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void SupprimerCliente(int id) {
        ;
        try {
            String requete3 = "DELETE FROM client WHERE id=?";
            PreparedStatement pst = connexion.prepareStatement(requete3);
            pst.setInt(1, id);
            pst.executeUpdate();
            System.out.println("Client supprimée");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    public void modifierCliente(int id, String nom, String prenom, String adresse, String mail, String mdp_client,
            int id_abonnement) {
        try {
            String requete4 = " UPDATE Client SET "
                    + "nom = ?, prenom= ?, adresse= ?, mail = ?, mdp_client = ?, id_abonnement = ? WHERE id = ? ";
            PreparedStatement pst = connexion.prepareStatement(requete4);

            pst.setString(1, nom);
            pst.setString(2, prenom);
            pst.setString(3, adresse);
            pst.setString(4, mail);
            pst.setString(5, mdp_client);
            pst.setInt(6, id_abonnement);
            pst.setInt(7, id);
            pst.executeUpdate();
            System.out.println("Client modifiée");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }
    
   @Override
    public List<Client> afficherCliente() throws SQLException {
        List<Client> clients = new ArrayList<>();
        String req = "select * from Client";
        stm = connexion.createStatement();
        // ensemble de resultat
        ResultSet rst = stm.executeQuery(req);

        while (rst.next()) {
            Client p = new Client(rst.getInt("id"), // or rst.getInt(1)
                    rst.getString("nom"),
                    rst.getString("prenom"),
                    rst.getString("adresse"),
                    rst.getString("mail"),
                    rst.getString("mdp_client"),
                    rst.getInt("id_abonnement"));
            clients.add(p);
        }
        return clients;
    }
    

    // ****************************************************METIER********************************************************************************
    // */

    public List<Client> afficherPDF() {
        List<Client> list = new ArrayList();

        String requete = "SELECT * FROM Client";
        try {

            PreparedStatement pst = connexion.prepareStatement(requete);
            ResultSet rs = pst.executeQuery(requete);
            while (rs.next()) {
                Client c = new Client();
                c.setId(rs.getInt("id"));
                c.setNom(rs.getString("nom"));
                c.setPrenom(rs.getString("prenom"));
                c.setAdresse(rs.getString("adresse"));
                c.setMail(rs.getString("mail"));
                c.setMdp_client(rs.getString("mdp_client"));
                c.setId_abonnement(rs.getInt("id_abonnement"));
                list.add(c);
            }

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return list;
    }

public Client RechecheClient( int id) throws SQLException   {
                 
  PreparedStatement st = connexion.prepareStatement("select * from client WHERE `id`=?");
            st.setInt(1, id);
            ResultSet rst = st.executeQuery();
            rst.beforeFirst();
                 while (rst.next()) {
                Client pr = new Client (rst.getInt("id"),
                        rst.getString("nom"),
                        rst.getString("prenom"),
                        rst.getString("adresse"),
                        rst.getString("mail"),
                        rst.getString("mdp_client"),
                        rst.getInt("id_abonnement"));
               
                return pr;
                 }
                return  null ;
        }

    

    public List<Client> tri_clientBy_Nom() throws SQLException { // TRi
        List<Client> recherche_client = new ArrayList<>();
        System.out.println("TRI par ID!:");
        String req = "select * from client " + " ORDER BY nom ASC";
        System.out.println("*********************TRI:*********************");
        stm = connexion.createStatement();
        // ensemble de resultat
        ResultSet rst = stm.executeQuery(req);
        while (rst.next()) {
            Client p = new Client(rst.getInt("id"), // or rst.getInt(1)
                    rst.getString("nom"),
                    rst.getString("prenom"),
                    rst.getString("adresse"),
                    rst.getString("mail"),
                    rst.getString("mdp_client"),
                    rst.getInt("id_abonnement"));
            recherche_client.add(p);
        }
        return recherche_client;
    }


    public void export_Client_csv(){
        try {
            String csvFilePath = "Client_export.csv";
            String sql = "select * from client";
            Statement statement= connexion.createStatement();
            try (ResultSet result = statement.executeQuery(sql)) {
                BufferedWriter fileWriter = new BufferedWriter(new FileWriter(csvFilePath));

                // write header line containing column names       
                fileWriter.write("ID_cLIENT,Nom,Prenom,adresse,mail,mdp_client,id_abonnement");
                while (result.next()) {
                    int C_ID_clients = result.getInt("id");
                    String C_nom = result.getString("nom");
                    String C_prenom = result.getString("prenom");
                    String C_adresse = result.getString("adresse");
                    String C_mail = result.getString("mail");
                    String C_mdp_client = result.getString("mdp_client");
                    int C_id_abonnement = result.getInt("id_abonnement");
                     
                     
                    String line = String.format("%d,%s,%s,%s,%s,%s,%d",
                    C_ID_clients,C_nom, C_prenom, C_adresse, C_mail,C_mdp_client, C_id_abonnement);
                     
                    fileWriter.newLine();
                    fileWriter.write(line);            
                }fileWriter.close();
            }

        } catch (Exception e) {
            System.out.println("Datababse error:");
            e.printStackTrace();}
    
    }
    
    
    
    public int loginMembre(String email, String password) {
        String requete = "SELECT * FROM client where email=? and password=? ";
        ResultSet res;
        try {
            PreparedStatement pst = connexion.prepareStatement(requete);
            pst.setString(1, email);
            pst.setString(2, password);
            res = pst.executeQuery();
            if (res.last()) {
                Client membre = null;
                Client p = new Client(res.getInt(1), res.getString(2), res.getString(3), res.getString(4), res.getString(5), res.getString(6), res.getInt(7));
               // UserSession.getInstance().setLoggedUser(membre);
                return 1;
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return 0;
    }
    
    

    public Client authentifier(String mail, String mdp_client) throws SQLException {
        String req = "select * from  client where mail=?  and mdp_client=? ";
        PreparedStatement stm = connexion.prepareStatement(req);
        stm.setString(1, mail);
        stm.setString(2, mdp_client);
        ResultSet rst = stm.executeQuery();
        if (rst.next()) {
                    Client e = new Client(rst.getInt("id"),
                            rst.getString("nom"),
                            rst.getString("prenom"),
                            rst.getString("adresse"),
                            rst.getString("mail"),
                            rst.getString("mdp_client"),
                            rst.getInt("id_abonnement")
                    );
                    
                    e.setAuthentifie(true);
                    return e;
            }
        else{System.out.println("verifier votre données");}

        return null;
    }
    
    
    

    /*
     * @Override
     * public ObservableList<Client> TrieParNom() {
     * ObservableList<Client> list = this.afficherCliente();
     * Collections.sort(list, new Client());
     * Collections.reverse(list);
     * return list;
     * }
     */

}
