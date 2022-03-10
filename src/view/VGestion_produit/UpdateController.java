/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.VGestion_produit;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import entities.Produit;
import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import services.ProduitService;

/**
 * FXML Controller class
 *
 * @author firas
 */
public class UpdateController implements Initializable {
@FXML
    private Button button_chercher;

    @FXML
    private Button button_modifier;

    @FXML
    private Button button_quitter;

    @FXML
    private TextField categorie_text;

    @FXML
    private TextField description_text;

    @FXML
    private TextField id_text;

    @FXML
    private TextField nom_text;

    @FXML
    private TextField prix_text;
         public ObservableList<Produit> data = FXCollections.observableArrayList();

     
    /**
     * Initializes the controller class.
     */
    
        @FXML
public void getProduit(ActionEvent event) throws SQLException { 
   
    int idr = Integer.parseInt(id_text.getText()); 
            ProduitService sp= new ProduitService();
    Produit pdt =sp. RechecheProduit(idr); 
    
 description_text.setText(pdt.getDescription());
nom_text.setText(pdt.getName());
 categorie_text. setText(String.valueOf(pdt.getId_categorie()));
prix_text.setText(String.valueOf(pdt.getPrice()));
    
}


@FXML
public void UpdateProduit(ActionEvent event) throws SQLException { 
    
    
        int idr = Integer.parseInt(id_text.getText()); 
        String desc = description_text.getText();
        String nom = nom_text.getText();
       float price =Float.parseFloat(prix_text.getText());
         int  cat=Integer.parseInt(categorie_text.getText());
          
 Produit pdt = new Produit(desc, nom, price, cat); 
 pdt.setId(idr);
 pdt.setDescription(desc);
 pdt.setName(nom);
 pdt.setPrice(price);
pdt.setId_categorie(cat); ; 
 ProduitService sp = new ProduitService(); 
 sp.modifierProduit(idr, desc, nom, price); 
 
}
@FXML
public void DeleteProduit(ActionEvent event) throws SQLException { 

        int idr = Integer.parseInt(id_text.getText()); 
ProduitService sp = new ProduitService(); 
 sp.SupprimerProduit(idr); 
 
}

@FXML
    private Button Bt_Dashboard1;
    @FXML
    private void Bt_Dashboard1(ActionEvent event) throws IOException {
        Bt_Dashboard1.getScene().getWindow().hide();
                 Parent root = FXMLLoader.load(getClass().getResource("../VGestion_ClientDashboard.fxml"));
        Stage mainStage = new Stage();
        Scene scene = new Scene(root);
        mainStage.setScene(scene);
        mainStage.show();

        
    }





@FXML
 
    public void Exit (ActionEvent event) { 
        System.exit(0);
        
        
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
