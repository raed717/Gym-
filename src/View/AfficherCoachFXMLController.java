/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import entities.Coach;
import entities.Seance;
import java.net.URL;
import java.sql.Connection;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import services.CoachService;
import services.SeanceService;
import singleton.Singleton;

/**
 * FXML Controller class
 *
 * @author saisi
 */
public class AfficherCoachFXMLController implements Initializable {
Connection connexion;
    Statement stm;
   
    @FXML
    private TableView<Coach> Tableau;
    @FXML
    private TableColumn<Coach, String> INom;
    @FXML
    private TableColumn<Coach, String> ISpecialite;
    @FXML
    private TableColumn<Coach, String> IMail;
    @FXML
    private TableColumn<Coach, String> IPwd;
    @FXML
    private Button Bt_Supprimer;
    @FXML
    private Button Bt_Search;
    
    
    public ObservableList<Coach> data = FXCollections.observableArrayList();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void SupprimerEmployer(ActionEvent event) {
        int idr = Integer.parseInt(INom.getText()); 
        CoachService sp = new CoachService(); 
       sp.SupprimerCoach(idr); 
    }

    @FXML
    private void SearchEmployer(ActionEvent event) throws SQLException{
          connexion = Singleton.getInstance().getConnexion();
          String req = "select * from tab_coach";
        stm = connexion.createStatement();
         ResultSet rs = stm.executeQuery(req);
        while (rs.next()) {
            data.add(new Coach  (rs.getString("nom_coach"),
                rs.getString("specialite"),
                rs.getString("mail"),
                rs.getString("mdp_coach")));
            
    }
        INom.setCellValueFactory(new PropertyValueFactory<Coach, String>("nom_coach"));
          ISpecialite.setCellValueFactory(new PropertyValueFactory<Coach, String>("specialite"));
          IMail.setCellValueFactory(new PropertyValueFactory<Coach, String>("mail"));
          IPwd.setCellValueFactory(new PropertyValueFactory<Coach, String>("mdp_coach"));
        
          
            Tableau.setItems(data);
    
}
}