package services;

import entities.Abonnement;
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
 * @author macbook
 */
public class AbonnementService implements IAbonnement<Abonnement> {

    Connection connexion;
    Statement stm;

    public AbonnementService() {
        connexion = MyDB.getInstance().getConnexion();
    }

    @Override
    public void ajouter_ab(Abonnement a)throws SQLException {
        try{
            String req = "INSERT INTO `Abonnement` (`nom_ab`, `prix_ab`, `description`) "
                    + "VALUES (?,?,?) ";
             PreparedStatement ps = connexion.prepareStatement(req);
                ps.setString(1, a.getNom_ab());
                ps.setInt(2, a.getPrix_ab());
                ps.setString(3, a.getDescription());
                ps.executeUpdate();
            }
            catch (SQLException ex){System.out.println(ex.getMessage());}
            }

            public void SupprimerAbonnement(int id_abonnement){
                try {
                    String requete3 = "DELETE FROM abonnement WHERE id_abonnement=?";
                    PreparedStatement pst = connexion.prepareStatement(requete3);
                    pst.setInt(1,id_abonnement);
                    pst.executeUpdate();
                    System.out.println("abonnement supprimée");
                } catch (SQLException ex) {
                    System.err.println(ex.getMessage());
                }
            }
            
            public void modifier_abonnement(int id_abonnement,String nom_ab,int prix_ab, String description  ){
                try {
                    String requete4 = " UPDATE abonnement SET " + "nom_ab = ?, prix_ab= ?, description= ? WHERE id_abonnement = ? " ;
                    PreparedStatement pst = connexion.prepareStatement(requete4);
                    
                    pst.setString(1,nom_ab);
                    pst.setInt(2,prix_ab);
                    pst.setString(3,description);
                    pst.setInt(4,id_abonnement);
                    pst.executeUpdate();
                    System.out.println("abonnement modifiée");
                } catch (SQLException ex) {
                    System.err.println(ex.getMessage());
                }
            }

            @Override
            public List<Abonnement> afficher_ab() throws SQLException {
                // TODO Auto-generated method stub
                return null;
            }
            
            
            
            public Abonnement RechecheAbonnement( int id_ab) throws SQLException   {
                 
  PreparedStatement st = connexion.prepareStatement("select * from abonnement WHERE `id_abonnement`=?");
            st.setInt(1, id_ab);
            ResultSet rst = st.executeQuery();
            rst.beforeFirst();
                 while (rst.next()) {
                Abonnement pr = new Abonnement (rst.getInt("id_abonnement"),
                        rst.getString("nom_ab"),
                        rst.getInt("prix_ab"),
                        rst.getString("description"));
               
                return pr;
                 }
                return  null ;
        }
            
            
            

   /* @Override
    public List<Abonnement> afficher_Abonnements() throws SQLException {
    List<Abonnement> Abonnements = new ArrayList<>();
    String req = "select * from abonnement";
    stm = connexion.createStatement();
    //ensemble de resultat
    ResultSet rst = stm.executeQuery(req);

    while (rst.next()) {
        Abonnement a = new Abonnement(rst.getInt("id_abonnement"),
                rst.getString("nom_ab"),
                rst.getInt("prix_ab"),
                rst.getString("description"),
        Abonnements.add(a);
    }
    return Abonnements;*/
}