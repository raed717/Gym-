/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.VGestion_seance;

import entities.Coach;
import entities.Seance;
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
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import services.CoachService;
import services.SeanceService;
import utils.MyDB;

/**
 * FXML Controller class
 *
 * @author saisi
 */
public class AfficherCoachFXMLController implements Initializable {

    Connection connexion;
    Statement stm;
    @FXML
    private Button Bt_Rech;
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
    private Button Bt_Search;
    @FXML
    private TextField id_text;

    @FXML
    private TextField mail_txt;

    @FXML
    private TextField mdp_txt;

    @FXML
    private TextField nom_txt;

    @FXML
    private TextField specialite_txt;

    public ObservableList<Coach> data = FXCollections.observableArrayList();
    @FXML
    private Button Bt_Dashboard1;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    private void SupprimerEmployer(ActionEvent event) {
        int idr = Integer.parseInt(INom.getText());
        CoachService sp = new CoachService();
        sp.SupprimerCoach(idr);
    }

    @FXML
    private void SearchEmployer(ActionEvent event) throws SQLException {
        connexion = MyDB.getInstance().getConnexion();
        String req = "select * from tab_coach";
        stm = connexion.createStatement();
        ResultSet rs = stm.executeQuery(req);
        while (rs.next()) {
            data.add(new Coach(rs.getString("nom_coach"),
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

    @FXML
    void Rechercher(ActionEvent event) throws SQLException {
        int idr = Integer.parseInt(id_text.getText());
        CoachService sp = new CoachService();
        Coach pdt = sp.RechecheCoach(idr);

        nom_txt.setText(pdt.getNom_coach());
        specialite_txt.setText(pdt.getSpecialite());
        mail_txt.setText((pdt.getMail()));
        mdp_txt.setText((pdt.getMdp_coach()));

    }

    @FXML
    public void UpdateCoach(ActionEvent event) throws SQLException {

        int idr = Integer.parseInt(id_text.getText());
        String nom = nom_txt.getText();
        String spec = specialite_txt.getText();
        String mail = mail_txt.getText();
        String mdp = mdp_txt.getText();

        Coach pdt = new Coach(nom, spec, mail, mdp);
        pdt.setId_coach(idr);
        pdt.setNom_coach(nom);
        pdt.setSpecialite(spec);

        pdt.setMail(mail);

        pdt.setMdp_coach(mdp);
          CoachService sp = new CoachService();
        sp.modifierSeance(idr,nom,spec,mail,mdp);

    }
    @FXML
    private void SupprimerCoach(ActionEvent event) {
        int v = Integer.parseInt(id_text.getText());
        //int v =   Integer.parseInt(Id_text.getText()); 
        CoachService sp = new CoachService();
        sp.SupprimerCoach(v);
        

        JOptionPane.showMessageDialog(null, "coach supprimé");
        Tableau.setItems(data);

    }
Parent parent;
    Stage stage;
    @FXML
    private void GoToDashbord(ActionEvent event) {
        
           try {
            parent = FXMLLoader.load(getClass().getResource("../VGestion_ClientDashboard.fxml"));
            
         Stage stage = new Stage(); 

         stage.setScene(new Scene(parent));
        stage.setTitle("Dashbord");
        stage.show();
        
        
} catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        
        
    }
    }


