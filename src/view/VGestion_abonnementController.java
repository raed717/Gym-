/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;
import services.AbonnementService;
import entities.Abonnement;
import java.io.IOException;

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
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
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
    
    
/*---------------------------------------NAVIGATION BAR--------------------------------------------*/
 @FXML
    private Button go_abonnement;
        @FXML
    void Go_abonnement(ActionEvent event) throws IOException {
        go_abonnement.getScene().getWindow().hide();
         Parent root = FXMLLoader.load(getClass().getResource("VGestion_abonnement.fxml"));
                Stage mainStage = new Stage();
                Scene scene = new Scene(root);
                mainStage.setScene(scene);
                mainStage.show();
    }
    @FXML
    private Button go_logout;
        @FXML
    void go_logout(ActionEvent event) throws IOException {
        go_logout.getScene().getWindow().hide();
         Parent root = FXMLLoader.load(getClass().getResource("VGestion_client.fxml"));
                Stage mainStage = new Stage();
                Scene scene = new Scene(root);
                mainStage.setScene(scene);
                mainStage.show();
    }
        @FXML
    private Button go_client;
        @FXML
    void go_client(ActionEvent event) throws IOException {
        go_client.getScene().getWindow().hide();
         Parent root = FXMLLoader.load(getClass().getResource("VGestion_clientDashboard.fxml"));
                Stage mainStage = new Stage();
                Scene scene = new Scene(root);
                mainStage.setScene(scene);
                mainStage.show();
    }
    
        @FXML
    private Button go_seance;

    @FXML
    void go_seance(ActionEvent event) throws IOException {
        go_seance.getScene().getWindow().hide();
        Parent root = FXMLLoader.load(getClass().getResource("VGestion_seance/AfficherSeanceFXML.fxml"));
        Stage mainStage = new Stage();
        Scene scene = new Scene(root);
        mainStage.setScene(scene);
        mainStage.show();
    }
    
    @FXML
    private Button Go_Coach;

    @FXML
    private void Go_Coach(ActionEvent event) throws IOException {
        go_seance.getScene().getWindow().hide();
        Parent root = FXMLLoader.load(getClass().getResource("VGestion_seance/AfficherCoachFXML.fxml"));
        Stage mainStage = new Stage();
        Scene scene = new Scene(root);
        mainStage.setScene(scene);
        mainStage.show();

    }
    
    @FXML
    private Button GoProduit;

    @FXML
    private void GoProduit(ActionEvent event) throws IOException {
        GoProduit.getScene().getWindow().hide();
        Parent root = FXMLLoader.load(getClass().getResource("VGestion_produit/AccueilA.fxml"));
        Stage mainStage = new Stage();
        Scene scene = new Scene(root);
        mainStage.setScene(scene);
        mainStage.show();

    }
    @FXML
    private Button GoMateriel;

    @FXML
    private void GoMateriel(ActionEvent event) throws IOException {
        GoMateriel.getScene().getWindow().hide();
        Parent root = FXMLLoader.load(getClass().getResource("VGestion_materiel/materiel.fxml"));
        Stage mainStage = new Stage();
        Scene scene = new Scene(root);
        mainStage.setScene(scene);
        mainStage.show();

    }

    @FXML
    private Button GoFournisseur;

    @FXML
    private void GoFournisseur(ActionEvent event) throws IOException {

        GoFournisseur.getScene().getWindow().hide();
        Parent root = FXMLLoader.load(getClass().getResource("VGestion_materiel/fournisseur.fxml"));
        Stage mainStage = new Stage();
        Scene scene = new Scene(root);
        mainStage.setScene(scene);
        mainStage.show();

    }



/*---------------------------------------NAVIGATION BAR--------------------------------------------*/




    
    
}
