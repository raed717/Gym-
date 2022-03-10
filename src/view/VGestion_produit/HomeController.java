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
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author firas
 */
public class HomeController implements Initializable {
    
   
   
   
    /**
     * Initializes the controller class.
     */
     
    Parent parent;
    Stage stage;
    @FXML
        public void GoTo (ActionEvent event) throws IOException { 
              
         try {
            parent = FXMLLoader.load(getClass().getResource("/tn/esprit/view/Produit.fxml"));
            
         Stage stage = new Stage(); 

         stage.setScene(new Scene(parent));
        stage.setTitle("Produit");
        stage.show();
        
        
} catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        }
    @FXML
              public void GoToI (ActionEvent event) throws IOException { 
              
         try {
            parent = FXMLLoader.load(getClass().getResource("/tn/esprit/view/Insert.fxml"));
            
         Stage stage = new Stage(); 

         stage.setScene(new Scene(parent));
        stage.setTitle("Produit");
        stage.show();
        
        
} catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        }
 
    @FXML
          public void GoToII (ActionEvent event) throws IOException { 
              
         try {
            parent = FXMLLoader.load(getClass().getResource("/tn/esprit/view/Update.fxml"));
            
         Stage stage = new Stage(); 

         stage.setScene(new Scene(parent));
        stage.setTitle("Produit");
        stage.show();
        
        
} catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        }
 
          
    @FXML 
    public void GoToPanier (ActionEvent event) throws IOException { 
              
         try {
            parent = FXMLLoader.load(getClass().getResource("/tn/esprit/view/Panier.fxml"));
            
         Stage stage = new Stage(); 

         stage.setScene(new Scene(parent));
        stage.setTitle("Panier");
        stage.show();
        
        
} catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
}

    @FXML 
    public void GoToFacture (ActionEvent event) throws IOException { 
              
         try {
            parent = FXMLLoader.load(getClass().getResource("/tn/esprit/view/Facture.fxml"));
            
         Stage stage = new Stage(); 

         stage.setScene(new Scene(parent));
        stage.setTitle("Facture");
        stage.show();
        
        
} catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
}


    @FXML 
    public void GoToChart (ActionEvent event) throws IOException { 
              
         try {
            parent = FXMLLoader.load(getClass().getResource("/tn/esprit/view/Chart.fxml"));
            
         Stage stage = new Stage(); 

         stage.setScene(new Scene(parent));
        stage.setTitle("Charta");
        stage.show();
        
        
} catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
}
    @FXML
    
    public void Quitter (ActionEvent event) { 
        System.exit(0);
        
        
    }
        
        
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
