/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import services.ClientService;
import entities.Client;
import java.awt.event.MouseEvent;
import java.io.IOException;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.PasswordField;
import java.sql.SQLException;
import java.sql.Statement;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ComboBox;
import javafx.scene.paint.Color;
import javax.swing.JOptionPane;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import static jdk.nashorn.internal.objects.ArrayBufferView.length;
import utils.MyDB;

/**
 * FXML Controller class
 *
 * @author Raed
 */
public class VGestion_ClientController implements Initializable {

    Connection connexion = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;

    @FXML
    private TextField inom;
    @FXML
    private TextField iprenom;
    @FXML
    private TextField iadresse;
    @FXML
    private TextField iemail;
    @FXML
    private PasswordField imdp_client;
    @FXML
    private TextField iid_abonnement;
    @FXML
    private TextField log_mail;
    @FXML
    private TextField log_mdp;
    @FXML
    private Label lblErrors;
    @FXML
    private Label lblErrors1;
    @FXML
    private Button btnSignin;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    public void AddClient(ActionEvent event) {
        String error = "Faute de saisie :";
        if (true) {
            if ("".equals(inom.getText())) {
                error = error.concat("/Nom/");
            }
            if ((imdp_client.getText()).length() < 5) {
                error = ("/Mot de passe/");
            }
            if ("".equals(iprenom.getText())) {
                error = error.concat("/Prenom/");
            }
            if (!iemail.getText().matches("\\w{3,}@\\S+")) {
                error = error.concat("/ Email /");
            }
            setLblError1(Color.TOMATO, error);
        } if("Faute de saisie :".equals(error)) {
            setLblError1(Color.GREEN, error);
            ClientService sp = new ClientService();
            Client p = new Client(0, inom.getText(), iprenom.getText(), iadresse.getText(), iemail.getText(),
                    imdp_client.getText(), Integer.parseInt(iid_abonnement.getText()));
            try {
                sp.ajouterClient(p);
                System.out.println("ajout avec succes");
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }

        }

    }


    @FXML
    private void logIn2(ActionEvent event) throws Exception {
        
 //LOGIN ADMIN
        if ("admin@gymplus.com".equals(log_mail.getText()) && "admin".equals(log_mdp.getText())) {
            setLblError(Color.GREEN, "Connexion réussite..Redirection..");
            JOptionPane.showMessageDialog(null, "Bienvenue Admin !");
            btnSignin.getScene().getWindow().hide();
            Parent root = FXMLLoader.load(getClass().getResource("VGestion_ClientDashboard.fxml"));
            Stage mainStage = new Stage();
            Scene scene = new Scene(root);
            mainStage.setScene(scene);
            mainStage.show();
            
//LOGIN CLIENT
        } else { 
                    connexion = MyDB.getInstance().getConnexion();
        String req = "Select * from client where mail = ? and mdp_client = ? ";
        try {
            preparedStatement = connexion.prepareStatement(req);
            preparedStatement.setString(1, log_mail.getText());
            preparedStatement.setString(2, log_mdp.getText());
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                setLblError(Color.GREEN, "Connexion réussite..Redirection..");
                JOptionPane.showMessageDialog(null, "Bienvenue");
                btnSignin.getScene().getWindow().hide();
                Parent root = FXMLLoader.load(getClass().getResource("VGestion_produit/Accueil.fxml"));
                Stage mainStage = new Stage();
                Scene scene = new Scene(root);
                mainStage.setScene(scene);
                mainStage.show();
            } else {
                setLblError(Color.TOMATO, "Entrez E-mail/mot de passe valide");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        } 
        }
    }

    
    private void setLblError(Color color, String text) {
        lblErrors.setTextFill(color);
        lblErrors.setText(text);
        System.out.println(text);
    }

    private void setLblError1(Color color, String text) {
        lblErrors1.setTextFill(color);
        lblErrors1.setText(text);
        System.out.println(text);
    }

}
