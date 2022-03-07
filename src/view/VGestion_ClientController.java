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
    private Button btnSignin;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }
    
  
    
    
    /*@FXML
    String logIn() {
        String status = "Success";
        String mail = log_mail.getText();
        String mdp_client = log_mdp.getText();
        ResultSet res;

        if (!mail.matches("\\w{3,}@\\S+") || mdp_client.length()<4) {
            //JOptionPane.showMessageDialog(null, "veuillez remplir les champs correctement");
            setLblError(Color.TOMATO, "Enter Correct Email/Password");
            status = "Error";
        } else {
            // query
            String req = "SELECT * FROM client Where mail = ? and mdp_client = ? ";
            try {
                PreparedStatement ps = connexion.prepareStatement(req);
                ps.setString(1, mail);
                ps.setString(2, mdp_client);
                res = preparedStatement.executeQuery();
                if (!res.next()) {
                    setLblError(Color.TOMATO, "Enter Correct Email/Password");
                    status = "Error";
                } else {
                    setLblError(Color.GREEN, "Login Successful..Redirecting..");
                    
                }
            } catch (SQLException ex) {
                System.err.println(ex.getMessage());
                status = "Exception";
            }
        }
        return status;
    }


    
     
    @FXML
    public void handleButtonAction(ActionEvent event) {

        if (event.getSource() == btnSignin) {
            //login here
            if (logIn().equals("Success")) {
                try {

                    //add you loading or delays - ;-)
                    Node node = (Node) event.getSource();
                    Stage stage = (Stage) node.getScene().getWindow();
                    //stage.setMaximized(true);
                    stage.close();
                    Scene scene = new Scene(FXMLLoader.load(getClass().getResource("/VGestion_abonnement.fxml")));
                    stage.setScene(scene);
                    stage.show();

                } catch (IOException ex) {
                    System.err.println(ex.getMessage());
                }

            }
        }
    }*/
      

    @FXML
    public void AddClient(ActionEvent event) {
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
    

    
      


/*
     * @FXML
     * private void ListPersons(ActionEvent event) {
     * ClientService sp= new ClientService();
     * try {
     * listP.setText(sp.afficherpersonne().toString());
     * } catch (SQLException ex) {
     * System.out.println(ex.getMessage());
     * }
     * }
 */
    
    
    
        @FXML  
    private void logIn2 (ActionEvent event) throws Exception{  
        
       connexion = MyDB.getInstance().getConnexion();
    String req = "Select * from client where mail = ? and mdp_client = ? ";
        try {
            preparedStatement = connexion.prepareStatement(req);
            preparedStatement.setString(1, log_mail.getText());
            preparedStatement.setString(2, log_mdp.getText());
            
            resultSet = preparedStatement.executeQuery();
            
            if(resultSet.next()){ 
                JOptionPane.showMessageDialog(null, "Email et mot de passe sont correctes");
                setLblError(Color.GREEN, "Connexion réussie..Redirection..");
                
                btnSignin.getScene().getWindow().hide();
                Parent root = FXMLLoader.load(getClass().getResource("VGestion_ClientDashboard.fxml"));
                Stage mainStage = new Stage();
                Scene scene = new Scene(root);
                mainStage.setScene(scene);
                mainStage.show();
                
            }else
                setLblError(Color.TOMATO, "Entrez le bon e-mail/mot de passe");
                //JOptionPane.showMessageDialog(null, "E-mail ou mot de passe invalide");
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
   
    }
    
    
    
    
    
    
    
    
    
    
    
    /*@FXML
    public void logIn2(ActionEvent event) throws IOException {
        String status = "Success";
        String mail = log_mail.getText();
        String mdp_client = log_mdp.getText();
        ResultSet res;

        if (!mail.matches("\\w{3,}@\\S+") || mdp_client.length()<4) {
            //JOptionPane.showMessageDialog(null, "veuillez remplir les champs correctement");
            setLblError(Color.TOMATO, "Enter Correct Email/Password");
            status = "Error";
        } else {
            setLblError(Color.GREEN, "Login Successful..Redirecting..");
                    //add you loading or delays - ;-)
                    Node node = (Node) event.getSource();
                    Stage stage = (Stage) node.getScene().getWindow();
                    //stage.setMaximized(true);
                    stage.close();
                    Scene scene = new Scene(FXMLLoader.load(getClass().getResource("/VGestion_abonnement.fxml")));
                    stage.setScene(scene);
                    stage.show();        
        }
       
    }*/
    
    
        private void setLblError(Color color, String text) {
        lblErrors.setTextFill(color);
        lblErrors.setText(text);
        System.out.println(text);
    }
    
    
    
    
    
    
}
