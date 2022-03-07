/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;
import services.AbonnementService;
import entities.Abonnement;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javax.swing.JOptionPane;
import utils.MyDB;


/**
 * FXML Controller class
 *
 * @author Raed
 */
public class VGestion_abonnementController implements Initializable {

 @FXML
    private TableColumn<Abonnement, String> description;

    @FXML
    private TextField iDescription_ab;

    @FXML
    private TextField iNom_abonnement;

    @FXML
    private TableColumn<Abonnement, Integer> id_abonnement;

    @FXML
    private TextField iprix_ab;

    @FXML
    private TableColumn<Abonnement, String> nom_ab;

    @FXML
    private TableColumn<Abonnement, Integer> prix_ab;

    @FXML
    private TableView<Abonnement> tab_ab;
    
        @FXML
    private TextField id_text;


    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }


    Connection connexion;
    Statement stm;
     public ObservableList<Abonnement> data = FXCollections.observableArrayList();
 
        @FXML
    void AfficherAbonnement(ActionEvent event) throws SQLException {
        connexion = MyDB.getInstance().getConnexion();
         String req = "select * from abonnement";
        stm = connexion.createStatement();
        ResultSet rst = stm.executeQuery(req);
        while (rst.next()) {
            data.add(new Abonnement(rst.getInt("id_abonnement"),
                    rst.getString("nom_ab"),
                    rst.getInt("prix_ab"),
                    rst.getString("description")));
        }
        id_abonnement.setCellValueFactory(new PropertyValueFactory<Abonnement, Integer>("id_abonnement"));
        nom_ab.setCellValueFactory(new PropertyValueFactory<Abonnement, String>("nom_ab"));
        prix_ab.setCellValueFactory(new PropertyValueFactory<Abonnement, Integer>("prix_ab"));
        description.setCellValueFactory(new PropertyValueFactory<Abonnement, String>("description"));
        tab_ab.setItems(data);
    }
    
    
    
    @FXML
    public void AddAbonnement(ActionEvent event) {
        AbonnementService sp = new AbonnementService();
        Abonnement p = new Abonnement(0, iNom_abonnement.getText(), Integer.parseInt(iprix_ab.getText()),iDescription_ab.getText() );
        try {
            sp.ajouter_ab(p);
            System.out.println("ajout avec succes");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }  
    
    
    
    @FXML
    void Bouton_Rechercher(ActionEvent event) throws SQLException {
        int idr = Integer.parseInt(id_text.getText()); 
        AbonnementService sp=new AbonnementService();
        Abonnement pdt=sp.RechecheAbonnement(idr);
        
        iNom_abonnement.setText(pdt.getNom_ab());
        iprix_ab.setText(String.valueOf(pdt.getPrix_ab()));
        iDescription_ab.setText(pdt.getDescription());

    }
    
    
                @FXML
    void bouton_modifier(ActionEvent event) {
          int idr = Integer.parseInt(id_text.getText()); 
          AbonnementService sp=new AbonnementService();
          sp.modifier_abonnement(idr, iNom_abonnement.getText(),Integer.parseInt(iprix_ab.getText()), iDescription_ab.getText());
          JOptionPane.showMessageDialog(null, "Abonnement modifié");
    }
    
    
    
        @FXML
    void Bouton_Supprimer(ActionEvent event) {
          int idr = Integer.parseInt(id_text.getText()); 
          AbonnementService sp=new AbonnementService();
          sp.SupprimerAbonnement(idr);
          JOptionPane.showMessageDialog(null, "Abonnement supprimé");
    }
    
    










    
    
}
