/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.VGestion_materiel;

import entities.materiel;
import java.io.IOException;
import services.materielCRUD;
import java.net.PasswordAuthentication;
import java.net.URL;
import java.sql.SQLException;
import java.util.Properties;
import java.util.ResourceBundle;
import java.util.function.Predicate;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.Authenticator;




/**
 * FXML Controller class
 *
 * @author AMINE
 */
public class MaterielController implements Initializable {

    @FXML
    private TextField id_m;
    @FXML
    private TextField nom_m;
    @FXML
    private ChoiceBox<String> etat_m;
    @FXML
    private TableView<materiel> VboxM;
    @FXML
    private Button a_m;
    materielCRUD mc = new materielCRUD();
    @FXML
    private TableColumn<materiel, Integer> idm;
    @FXML
    private TableColumn<materiel, String> nomm;
    @FXML
    private TableColumn<materiel, String> etatm;
    @FXML
    private Button m_m;
    @FXML
    private Button s_m;
    @FXML
    private TextField prix_m;
    @FXML
    private Label label;
    @FXML
    private TableColumn<materiel, Integer> prixm;
    @FXML
    private Label label1;

    private String[] etat = {"Bon", "Moyen", "Mauvais"};
    @FXML
    private TextField tsrearch;
    @FXML
    private Button R_m;
    @FXML
    private Button M_m;
    @FXML
    private TextField mailm;
    private String username = "firasyazid4@gmail.com";
    @FXML
    private String password = "moneymoney.";

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        etat_m.getItems().addAll(etat);
        //materiel.setOnAction(this::etat);
    }

    @FXML
    private void aj_m(ActionEvent event) {
        if (nom_m.getText() != null && !nom_m.getText().isEmpty()) {
            materiel m = new materiel(nom_m.getText(), Integer.parseInt(prix_m.getText()), etat_m.getValue());
            mc.ajouterMateriel2(m);
            label.setText("");
        } else {
            label.setText("Nom can't be empty");
        }
        id_m.setText("");
        nom_m.setText("");
        prix_m.setText("");

    }

    public void showMateriel() {
        ObservableList<materiel> list = mc.afficherMateriel();
        idm.setCellValueFactory(new PropertyValueFactory<materiel, Integer>("id"));
        nomm.setCellValueFactory(new PropertyValueFactory<materiel, String>("nom"));
        prixm.setCellValueFactory(new PropertyValueFactory<materiel, Integer>("prix"));
        etatm.setCellValueFactory(new PropertyValueFactory<materiel, String>("etat"));
        VboxM.setItems(list);
    }

    @FXML
    private void af_m(ActionEvent event) {
        showMateriel();
    }

    @FXML
    private void md_m(ActionEvent event) {
        mc.modifierMateriel(Integer.parseInt(id_m.getText()), nom_m.getText(), Integer.parseInt(prix_m.getText()), etat_m.getValue());
        id_m.setText("");
        nom_m.setText("");
        prix_m.setText("");
        showMateriel();
    }

    @FXML
    private void su_m(ActionEvent event) {
        int id = Integer.parseInt(id_m.getText());
        mc.SupprimerMateriel(id);
        showMateriel();
    }

    public void searchMateriel() {
        materielCRUD ue = new materielCRUD();

        ObservableList<materiel> l = ue.afficherMateriel();
        try {

            VboxM.setItems(l);
            FilteredList<materiel> f = new FilteredList<>(l, b -> true);
            tsrearch.textProperty().addListener((ObservableValue<? extends String> observable, String olValue, String newValue) -> {
                f.setPredicate(new Predicate<materiel>() {
                    public boolean test(materiel m) {
                        if (newValue == null || newValue.isEmpty()) {
                            return true;
                        }
                        String lowerCaseFilter = newValue.toLowerCase();

                        if (m.getNom().toLowerCase().contains(lowerCaseFilter)) {
                            return true;
                        } else if (String.valueOf(m.getId()).indexOf(lowerCaseFilter) != -1) {
                            return true;
                        } else {
                            return false;
                        }
                    }

                });
            });
            SortedList<materiel> sortedData = new SortedList<>(f);
            sortedData.comparatorProperty().bind(VboxM.comparatorProperty());
            VboxM.setItems(sortedData);

        } catch (Exception e) {
            System.out.println(e.getMessage());

        }
    }

    @FXML
    private void R_m(ActionEvent event) {
        searchMateriel();
    }

    @FXML
    private void M_m(ActionEvent event) {
        // Etape 1 : Création de la session
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
        Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication(){
                return new PasswordAuthentication(username,password.toCharArray());
            }
        });
        try {
// Etape 2 : Création de l'objet Message
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("firasyazid4@gmail.com"));
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse("raed.guembri@esprit.tn"));
            message.setSubject("Test email");
            message.setText("Bonjour, les seances sont ajoutées ...");
// Etape 3 : Envoyer le message
            Transport.send(message);
            System.out.println("Message_envoye");
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }
    
    
/*---------------------------------------NAVIGATION BAR--------------------------------------------*/
    @FXML
    private Button go_abonnement;

    @FXML
    void Go_abonnement(ActionEvent event) throws IOException {
        go_abonnement.getScene().getWindow().hide();
        Parent root = FXMLLoader.load(getClass().getResource("../VGestion_abonnement.fxml"));
        Stage mainStage = new Stage();
        Scene scene = new Scene(root);
        mainStage.setScene(scene);
        mainStage.show();
    }

    @FXML
    private Button go_logout;

    @FXML
    void go_logout(ActionEvent event) throws IOException {
        go_logout.getScene().getWindow().hide();
        Parent root = FXMLLoader.load(getClass().getResource("../VGestion_client.fxml"));
        Stage mainStage = new Stage();
        Scene scene = new Scene(root);
        mainStage.setScene(scene);
        mainStage.show();
    }

    @FXML
    private Button go_client;

    @FXML
    void go_client(ActionEvent event) throws IOException {
        go_client.getScene().getWindow().hide();
        Parent root = FXMLLoader.load(getClass().getResource("../VGestion_clientDashboard.fxml"));
        Stage mainStage = new Stage();
        Scene scene = new Scene(root);
        mainStage.setScene(scene);
        mainStage.show();
    }

    @FXML
    private Button go_seance;

    @FXML
    void go_seance(ActionEvent event) throws IOException {
        go_seance.getScene().getWindow().hide();
        Parent root = FXMLLoader.load(getClass().getResource("../VGestion_seance/AfficherSeanceFXML.fxml"));
        Stage mainStage = new Stage();
        Scene scene = new Scene(root);
        mainStage.setScene(scene);
        mainStage.show();
    }

    
    @FXML
    private Button Go_Coach;

    @FXML
    private void Go_Coach(ActionEvent event) throws IOException {
        go_seance.getScene().getWindow().hide();
        Parent root = FXMLLoader.load(getClass().getResource("../VGestion_seance/AfficherCoachFXML.fxml"));
        Stage mainStage = new Stage();
        Scene scene = new Scene(root);
        mainStage.setScene(scene);
        mainStage.show();

    }
    @FXML
    private Button GoProduit;

    @FXML
    private void GoProduit(ActionEvent event) throws IOException {
        GoProduit.getScene().getWindow().hide();
        Parent root = FXMLLoader.load(getClass().getResource("../VGestion_produit/AccueilA.fxml"));
        Stage mainStage = new Stage();
        Scene scene = new Scene(root);
        mainStage.setScene(scene);
        mainStage.show();

    }
    @FXML
    private Button GoMateriel;

    @FXML
    private void GoMateriel(ActionEvent event) throws IOException {
        GoMateriel.getScene().getWindow().hide();
        Parent root = FXMLLoader.load(getClass().getResource("../VGestion_materiel/materiel.fxml"));
        Stage mainStage = new Stage();
        Scene scene = new Scene(root);
        mainStage.setScene(scene);
        mainStage.show();

    }

    @FXML
    private Button GoFournisseur;

    @FXML
    private void GoFournisseur(ActionEvent event) throws IOException {

        GoFournisseur.getScene().getWindow().hide();
        Parent root = FXMLLoader.load(getClass().getResource("../VGestion_materiel/fournisseur.fxml"));
        Stage mainStage = new Stage();
        Scene scene = new Scene(root);
        mainStage.setScene(scene);
        mainStage.show();

    }
    /*---------------------------------------NAVIGATION BAR--------------------------------------------*/
    
    
    
    
    
    
    
    
    
}
