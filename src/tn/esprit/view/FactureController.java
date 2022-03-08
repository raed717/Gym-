/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.view;

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
import tn.esprit.model.Facture;
import tn.esprit.model.Produit;
import tn.esprit.utils.MyDB;

/**
 * FXML Controller class
 *
 * @author firas
 */
public class FactureController implements Initializable {
        Connection connexion;
    Statement stm;

   @FXML
    private Button Bt_Dashboard1;

    @FXML
    private Button Bt_Dashboard11;

    @FXML
    private TableView<Facture> Tableau;

    @FXML
    private Button button_display;

    @FXML
    private Button button_quitter;

    @FXML
    private TableColumn<Facture, Integer> id;

    @FXML
    private TableColumn<Facture, Integer>id_p;

    @FXML
    private TableColumn<Facture, Float> total;
    
     public ObservableList<Facture> data = FXCollections.observableArrayList();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    @FXML
    public void Display(ActionEvent event) throws SQLException {

                    connexion = MyDB.getInstance().getConnexion();

 String req = "select * from facture";
        stm = connexion.createStatement();
         ResultSet rst = stm.executeQuery(req);
        while (rst.next()) {
            data.add(new Facture (rst.getInt("id_f"),
                    rst.getInt("id"),
                    rst.getFloat("total")));
        }
   
          id.setCellValueFactory(new PropertyValueFactory<Facture, Integer>("id"));
          id_p.setCellValueFactory(new PropertyValueFactory<Facture, Integer>("id"));
           total.setCellValueFactory(new PropertyValueFactory<Facture, Float>("total"));
           Tableau.setItems(data);

}
}