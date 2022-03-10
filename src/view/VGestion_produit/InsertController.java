/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.VGestion_produit;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import entities.Produit;
import services.ProduitService;

/**
 * FXML Controller class
 *
 * @author firas
 */
public class InsertController implements Initializable {
    Parent parent;
    Stage stage;
   @FXML
    private Text alertDesc;

    @FXML
    private Text alertNom;


 @FXML
    private Button Bt_Dashboard1;

    @FXML
    private Button Bt_Dashboard11;

    @FXML
    private Button Bt_Dashboard111;

    @FXML
    private Button button_insert;

    @FXML
    private Button button_vider;

    @FXML
    private TextField categorie_text;

    @FXML
    private TextField description_text;

    @FXML
    private TextField nom_text;

    @FXML
    private TextField prix_text;
    
     public ObservableList<Produit> data = FXCollections.observableArrayList();

    /**
     * Initializes the controller class.
     * @param 
     * @param 
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }  
        @FXML

    
    private void AddProduit(ActionEvent event) throws SQLException {
        
        ProduitControl pc = new ProduitControl(); 
        
        ProduitService sp= new ProduitService();
        Produit p = new Produit ( description_text.getText(),nom_text .getText(), Float.parseFloat(prix_text.getText()),
                Integer.parseInt(categorie_text.getText()));
 
        sp.ajouterProduit(p);
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Success");
        alert.setContentText("Product is added successfully!");
        alert.show();
        description_text.setText("description");  
        nom_text.setText("name ");
        prix_text.setText("price");
        categorie_text.setText("id_categorie");
        
        
        
        
                Boolean retourstr= true;
                if (!pc.ControleDesc(p)){
            alertDesc.setText("description non valable. ");
            retourstr=false; 
        }

         if (!pc.ControleNOM(p)){
            alertNom.setText("Nom non valable. ");
            retourstr=false;
        }
        
        
        
    }
            @FXML

        private void clear (ActionEvent event)  
        
        {
            description_text.clear();
            nom_text.clear();
           prix_text.clear(); 
           categorie_text.clear();
            
        }
         public void GoToDashbord (ActionEvent event) throws IOException { 
              
         try {
            parent = FXMLLoader.load(getClass().getResource("/tn/esprit/view/Home.fxml"));
            
         Stage stage = new Stage(); 

         stage.setScene(new Scene(parent));
        stage.setTitle("Dashbord");
        stage.show();
        
        
} catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
}
        
         @FXML
    
    public void quitter (ActionEvent event) { 
        System.exit(0);
}}
 

