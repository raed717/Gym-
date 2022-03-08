/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.view;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import tn.esprit.model.Produit;
import tn.esprit.service.ProduitService;
import tn.esprit.utils.MyDB;

/**
 * FXML Controller class
 *
 * @author firas
 */
public class PanierController implements Initializable {
 Connection connexion;
    Statement stm;
   
    @FXML
    private Button display;
    @FXML
    private Button Bt_Dashboard1;

    @FXML
    private Button Bt_Dashboard11;


    @FXML
    private TableColumn<Produit, String> description;

    @FXML
    private Pane history;

    @FXML
    private TableColumn<Produit, Integer> id;

    @FXML
    private TableColumn<Produit, String> nom;

    @FXML
    private TableColumn<Produit, Float> prix;

    @FXML
    private TableColumn<Produit, Integer> categorie;

    @FXML
    private Label prix_total;

    @FXML
    private Pane retour;

    @FXML
    private TableView<Produit> tabpanier;

    @FXML
    private Button valider;

     
 public ObservableList<Produit> data = FXCollections.observableArrayList();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
     }

    
    
    
    public void afficher(ActionEvent event) throws SQLException { 
    
             connexion = MyDB.getInstance().getConnexion();

 String req = "select * from produit";
        stm = connexion.createStatement();
         ResultSet rst = stm.executeQuery(req);
        while (rst.next()) {
            data.add(new Produit (rst.getInt("id"),
                    rst.getString("name"),
                    rst.getString("description"),
                     rst.getFloat("price") ,
                     rst.getInt("id_categorie")));
             
        }
   
          id.setCellValueFactory(new PropertyValueFactory<Produit, Integer>("id"));
          description.setCellValueFactory(new PropertyValueFactory<Produit, String>("description"));
           nom.setCellValueFactory(new PropertyValueFactory<Produit, String>("name"));
          prix.setCellValueFactory(new PropertyValueFactory<Produit, Float>("price"));
          categorie.setCellValueFactory(new PropertyValueFactory<Produit, Integer>("id_categorie"));

           tabpanier.setItems(data);
          
    }
                                    
      
    
    
}
