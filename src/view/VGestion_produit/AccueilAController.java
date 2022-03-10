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
public class AccueilAController implements Initializable {
Parent parent;
    Stage stage;

    @FXML
    private BorderPane borderpane;
    @FXML
    private Label nom_utilisateur;
    @FXML
    private Button update_button;
    @FXML
    private Button chart_button;
    @FXML
    private Button facture_button;
    @FXML
    private Button insert_btn;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

     
    @FXML
    
    private Button produit_btn;

    @FXML
    private void GoTo1(ActionEvent event) throws IOException {
                produit_btn.getScene().getWindow().hide();
                Parent root = FXMLLoader.load(getClass().getResource("Produit.fxml"));
        Stage mainStage = new Stage();
        Scene scene = new Scene(root);
        mainStage.setScene(scene);
        mainStage.show();

     }

    @FXML
    private void GoTo2(ActionEvent event) {
         try {
            parent = FXMLLoader.load(getClass().getResource("Update.fxml"));
            
         Stage stage = new Stage(); 

         stage.setScene(new Scene(parent));
        stage.setTitle("update");
        stage.show();
        
        
} catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        
        
    }


    @FXML
    private void GoTo4(ActionEvent event) {
        
          try {
            parent = FXMLLoader.load(getClass().getResource("Chart.fxml"));
            
         Stage stage = new Stage(); 

         stage.setScene(new Scene(parent));
        stage.setTitle("Chart");
        stage.show();
        
        
} catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        
    }

    @FXML
    private void GoTo5(ActionEvent event) {
        
        
          try {
            parent = FXMLLoader.load(getClass().getResource("Facture.fxml"));
            
         Stage stage = new Stage(); 

         stage.setScene(new Scene(parent));
        stage.setTitle("Facture");
        stage.show();
        
        
} catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        
    }

    @FXML
    private void AccueilLoad(MouseEvent event) {
    }
    
@FXML
    private Button accueil_btn;
    @FXML
    private void accueil_btn(ActionEvent event) throws IOException {
        accueil_btn.getScene().getWindow().hide();
                 Parent root = FXMLLoader.load(getClass().getResource("../VGestion_ClientDashboard.fxml"));
        Stage mainStage = new Stage();
        Scene scene = new Scene(root);
        mainStage.setScene(scene);
        mainStage.show();

        
    }

    @FXML
    private void ProduitLoad(MouseEvent event) {
    }

    @FXML
    private void Insert(ActionEvent event) {
         try {
            parent = FXMLLoader.load(getClass().getResource("Insert.fxml"));
            
         Stage stage = new Stage(); 

         stage.setScene(new Scene(parent));
        stage.setTitle("Facture");
        stage.show();
        
        
} catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        
    }
    
}
