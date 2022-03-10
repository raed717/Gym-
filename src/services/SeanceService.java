/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.Seance;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import utils.MyDB;

/**
 *
 * @author saisi
 */
public class SeanceService implements ISeance<Seance> {

    Connection connexion;
    Statement stm;

    public SeanceService() {
        connexion = MyDB.getInstance().getConnexion();
    }

    public void ajouterSeance(Seance s) throws SQLException {
        String req = "INSERT INTO `tab_seance` (`type_seance`, `date_debut`, `date_fin`, `id_coach`) "
                + "VALUES ( ?, ?, ?, ( SELECT id_coach FROM tab_coach WHERE tab_coach.id_coach = ? )) ";
        PreparedStatement ps = connexion.prepareStatement(req);
        ps.setString(1, s.getType_seance());
        ps.setString(2, s.getDate_debut());
        ps.setString(3, s.getDate_fin());
        ps.setInt(4, s.getId_coach());
        ps.executeUpdate();
    }

    public List<Seance> afficherSeance() throws SQLException {
        List<Seance> MyList = new ArrayList<>();
        try {
            String req2 = "SELECT * FROM tab_seance";
            Statement st = connexion.createStatement();
            ResultSet rs = st.executeQuery(req2);
            while (rs.next()) {
                Seance s = new Seance(rs.getInt("id_seance"),
                        rs.getString("Type_seance"),
                        rs.getString("Date_debut"),
                        rs.getString("Date_fin"),
                        rs.getInt("id_coach"));
                MyList.add(s);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return MyList;
    }

    public void SupprimerSeance(String id_seance) {
        try {
            String req3 = "DELETE FROM tab_seance WHERE id_seance=?";
            PreparedStatement pst = connexion.prepareStatement(req3);
            pst.setString(1, id_seance);
            pst.executeUpdate();
            System.out.println("seance supprimée");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void modifierSeance(int id_seance, String type_seance, String date_debut, String date_fin,int id_coach) {
        try {
            String req4 = " UPDATE tab_seance SET " + " type_seance = ?, date_debut = ?,date_fin = ?,id_coach = ? WHERE id_seance = ? ";
            PreparedStatement pst = connexion.prepareStatement(req4);
            pst.setString(1, type_seance);
            pst.setString(2, date_debut);
            pst.setString(3, date_fin);
            pst.setInt(4, id_coach);
                                    pst.setInt(5, id_seance);

            pst.executeUpdate();
            System.out.println("seance modifiée");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public Seance RechecheSeance(int id_seance) throws SQLException {

        PreparedStatement st = connexion.prepareStatement("select * from tab_seance WHERE `id_seance`=?");
        st.setInt(1, id_seance);
        ResultSet rst = st.executeQuery();
        rst.beforeFirst();
        while (rst.next()) {
            Seance pr = new Seance(rst.getInt("id_seance"),
                     rst.getString("type_seance"),
                    rst.getString("date_debut"),
                    rst.getString("date_fin"),
                    rst.getInt("id_coach"));

            return pr;
        }
        return null;
    }

}
