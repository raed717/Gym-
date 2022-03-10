/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.VGestion_produit;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author firas
 */
public class AccueilController implements Initializable {

    @FXML
    private BorderPane borderpane;
    @FXML
    private Button produit_btn;
    @FXML
    private Label nom_utilisateur;

    Parent parent;
    Stage stage;
    @FXML
    private Button Dashbord_btn;
    @FXML
    private Button panier_btn;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void AccueilLoad(MouseEvent event) {
                      
       
    }


    @FXML
    private void ProduitLoad(MouseEvent event) {
    }

    @FXML
    private void GoTo1(ActionEvent event) {
          try {
            parent = FXMLLoader.load(getClass().getResource("Produit.fxml"));
            
         Stage stage = new Stage(); 

         stage.setScene(new Scene(parent));
        stage.setTitle("Product");
        stage.show();
        
        
} catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    private void GoTo2(ActionEvent event) {
        
          try {
            parent = FXMLLoader.load(getClass().getResource("/tn/esprit/view/Panier.fxml"));
            
         Stage stage = new Stage(); 

         stage.setScene(new Scene(parent));
        stage.setTitle("Product");
        stage.show();
        
        
} catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        
    }

    @FXML
    private void GoToDash(ActionEvent event) {
        
         try {
            parent = FXMLLoader.load(getClass().getResource("../VGestion_ClientDashboard.fxml"));
            
         Stage stage = new Stage(); 

         stage.setScene(new Scene(parent));
        stage.setTitle("Facture");
        stage.show();
        
        
} catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        
    }
    

    @FXML
    private void GoToPanier(ActionEvent event) {
    try {
            parent = FXMLLoader.load(getClass().getResource("Panier.fxml"));
            
         Stage stage = new Stage(); 

         stage.setScene(new Scene(parent));
        stage.setTitle("Facture");
        stage.show();
        
        
} catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        
    }
    }


