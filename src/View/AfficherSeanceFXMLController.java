/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import entities.Seance;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import static java.util.Collections.singleton;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseDragEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javax.swing.JOptionPane;
import services.SeanceService;
import singleton.Singleton;
import static sun.font.FontManagerNativeLibrary.load;

/**
 * FXML Controller class
 *
 * @author saisi
 */
public class AfficherSeanceFXMLController implements Initializable {
    
    Connection connexion;
    Statement stm;

    @FXML
    private TableView<Seance> Tableau;
    @FXML
    private Button Bt_Supprimer;
    @FXML
    private Text alertRechere;
    @FXML
    private TableColumn<Seance,String > Type_seance;
    @FXML
    private TableColumn<Seance,String > Date_debut;
    @FXML
    private TableColumn<Seance,String > Date_fin;
    @FXML
    private TableColumn<Seance,Integer > Id_coach;
    @FXML
    private Button Bt_Search;
   
    @FXML
    private TableColumn<Seance,Integer> Id_seance;
    @FXML
    private TextField Id_text;
    
    /**
     *
     */
    public ObservableList<Seance> data = FXCollections.observableArrayList();
    
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }   
    

    @FXML
    private void SearchEmployer(ActionEvent event) throws SQLException {
         connexion = Singleton.getInstance().getConnexion();

    String req = "select * from tab_seance";
        stm = connexion.createStatement();
         ResultSet rs = stm.executeQuery(req);
        while (rs.next()) {
            data.add(new Seance  (rs.getInt("id_seance"),
                rs.getString("Type_seance"),
                rs.getString("Date_debut"),
                rs.getString("Date_fin"),
                rs.getInt("Id_coach")));
             
        }
   
         Type_seance.setCellValueFactory(new PropertyValueFactory<Seance, String>("type_seance"));
          Date_debut.setCellValueFactory(new PropertyValueFactory<Seance, String>("date_debut"));
          Date_fin.setCellValueFactory(new PropertyValueFactory<Seance, String>("date_fin"));
          Id_coach.setCellValueFactory(new PropertyValueFactory<Seance, Integer>("id_coach"));
           Id_seance.setCellValueFactory(new PropertyValueFactory<Seance, Integer>("id_seance"));
        
          
            Tableau.setItems(data);
            Tableau.refresh();
    }

    @FXML
    private void SupprimerEmployer(ActionEvent event) {
        String v = String.valueOf(Id_text.getText());
        //int v =   Integer.parseInt(Id_text.getText()); 
        SeanceService sp = new SeanceService(); 
       sp.SupprimerSeance(v);
      Tableau.refresh();
       
       JOptionPane.showMessageDialog(null, "seance supprimé");
       Tableau.setItems(data);
        
     }

   public void Quit (ActionEvent event) { 
        System.exit(0);
    }

   
  

   

        
    }

        
    

