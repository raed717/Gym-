/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.VGestion_produit;

import java.awt.event.MouseEvent;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
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
import entities.Produit;
import utils.MyDB;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.Node;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 * FXML Controller class
 *
 * @author firas
 */
public class PanierController implements Initializable {

    Connection connexion;
    Statement stm;

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

    private   String username = "firasyazid4@gmail.com";
    private   String password = "moneymoney.";
    int index = -1;
    float x;

    public ObservableList<Produit> data = FXCollections.observableArrayList();
    @FXML
    private Button Add;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            connexion = MyDB.getInstance().getConnexion();

            String req = "select * from produit";
            stm = connexion.createStatement();
            ResultSet rst = stm.executeQuery(req);
            while (rst.next()) {
                data.add(new Produit(rst.getInt("id"),
                        rst.getString("name"),
                        rst.getString("description"),
                        rst.getFloat("price"),
                        rst.getInt("id_categorie")));

            }

            id.setCellValueFactory(new PropertyValueFactory<Produit, Integer>("id"));
            description.setCellValueFactory(new PropertyValueFactory<Produit, String>("description"));
            nom.setCellValueFactory(new PropertyValueFactory<Produit, String>("name"));
            prix.setCellValueFactory(new PropertyValueFactory<Produit, Float>("price"));
            categorie.setCellValueFactory(new PropertyValueFactory<Produit, Integer>("id_categorie"));

            tabpanier.setItems(data);
        } catch (SQLException ex) {
            Logger.getLogger(PanierController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void afficher(ActionEvent event) throws SQLException {

        connexion = MyDB.getInstance().getConnexion();

        String req = "select * from produit";
        stm = connexion.createStatement();
        ResultSet rst = stm.executeQuery(req);
        while (rst.next()) {
            data.add(new Produit(rst.getInt("id"),
                    rst.getString("name"),
                    rst.getString("description"),
                    rst.getFloat("price"),
                    rst.getInt("id_categorie")));

        }

        id.setCellValueFactory(new PropertyValueFactory<Produit, Integer>("id"));
        description.setCellValueFactory(new PropertyValueFactory<Produit, String>("description"));
        nom.setCellValueFactory(new PropertyValueFactory<Produit, String>("name"));
        prix.setCellValueFactory(new PropertyValueFactory<Produit, Float>("price"));
        categorie.setCellValueFactory(new PropertyValueFactory<Produit, Integer>("id_categorie"));

        tabpanier.setItems(data);

    }

    public float GetSelectedPrice(ActionEvent event) throws SQLException {

        index = tabpanier.getSelectionModel().getSelectedIndex();
        x = prix.getCellData(index);
        return x;

    }

    @FXML
    private void Envoyer(ActionEvent event) {

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
 
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
        Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("firasyazid4@gmail.com"));
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse("raed.guembri@esprit.tn"));
            message.setSubject("Test email");
            message.setText("Votre commande à été valider");
// Etape 3 : Envoyer le message
            Transport.send(message);
            System.out.println("Message_envoye");
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    private void Add(ActionEvent event) {
        float a = Float.valueOf(prix_total.getText());
        float b = 0;
        try {
            b = GetSelectedPrice(event);
        } catch (SQLException ex) {
            Logger.getLogger(PanierController.class.getName()).log(Level.SEVERE, null, ex);
        }
        a += b;
        prix_total.setText(String.valueOf(a));
    }

}
