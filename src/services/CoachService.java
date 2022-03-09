/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.Coach;
import entities.Seance;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import singleton.Singleton;

/**
 *
 * @author saisi
 */
public class CoachService implements ICoach<Coach>{
    Connection connexion;
    Statement stm;

    public CoachService() {
        connexion = Singleton.getInstance().getConnexion();
    }

    @Override
    public void ajouterCoach(Coach s) throws SQLException {
      // String req = "INSERT INTO `tab_seance` (`id_coach`, `nom_coach`, `specialite`, `mail`, `mdp_coach`) "
              //  + "VALUES ( ?, ?, ?, ?, ?) ";
               String req = "INSERT INTO tab_coach (`nom_coach`,`specialite`,`mail`,`mdp_coach`) VALUES ( '"
                + s.getNom_coach() + "', '" + s.getSpecialite() + "', '" + s.getMail() + "', '" + s.getMdp_coach() + "') ";
        PreparedStatement cs = connexion.prepareStatement(req);
        /*cs.setInt(1, s.getId_coach());
        cs.setString(2,  s.getNom_coach());
        cs.setString(3,  s.getSpecialite());
        cs.setString(4, s.getMail());
        cs.setString(5,  s.getMdp_coach());*/
        cs.executeUpdate();
    }
     public List<Coach>afficherCoach() throws SQLException{
           List<Coach> MyList = new ArrayList<>();
        try {
            String req2 = "SELECT * FROM tab_coach";
            Statement st = connexion.createStatement();
            ResultSet rs = st.executeQuery(req2);
            while(rs.next()){
                Coach s = new Coach(rs.getInt("id_coach"),
                rs.getString("nom_coach"),
                rs.getString("specialite"),
                rs.getString("mail"),
                rs.getString("mdp_coach"));
                MyList.add(s);
            }
        } catch (SQLException ex) {
        System.out.println(ex.getMessage());
        }
        return MyList;
     }
        
        public void SupprimerCoach(int id_coach){
        try {
            String req3 = "DELETE FROM tab_coach WHERE id_coach=?";
            PreparedStatement pst = connexion.prepareStatement(req3);
            pst.setInt(1,id_coach);
            pst.executeUpdate();
            System.out.println("coach supprimée");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    public void modifierSeance(int id_coach,String nom_coach,String specialite,String mail,String mdp_coach){
        try {
            String req4 = " UPDATE tab_coach SET " + " nom_coach = ?, specialite = ?,mail = ?,mdp_coach = ? WHERE id_coach = ? " ;
            PreparedStatement pst = connexion.prepareStatement(req4);
            pst.setString(1,nom_coach);
            pst.setString(2,specialite);
            pst.setString(3,mail);
            pst.setString(4,mdp_coach);
            pst.setInt(5,id_coach);
            pst.executeUpdate();
            System.out.println("coach modifiée");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    
}
