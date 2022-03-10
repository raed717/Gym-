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
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.print.PrinterJob;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.Window;
import javax.lang.model.element.Element;
import javax.swing.JOptionPane;
import utils.MyDB;

/**
 * FXML Controller class
 *
 * @author Raed
 */
public class VGestion_ClientDashboardController implements Initializable {

    @FXML
    private TableColumn<Client, String> adresse;

    @FXML
    private TableColumn<Client, Integer> id;

    @FXML
    private TableColumn<Client, Integer> id_abonnement;

    @FXML
    private TableColumn<Client, String> mail;

    @FXML
    private TableColumn<Client, String> mdp_client;

    @FXML
    private TableColumn<Client, String> nom;

    @FXML
    private TableColumn<Client, String> prenom;

    @FXML
    private TableView<Client> tab_client;

    @FXML
    private TextField id_text;
    @FXML
    private TextField nom_txt;
    @FXML
    private TextField prenom_txt;
    @FXML
    private TextField adresse_txt;
    @FXML
    private TextField email_txt;
    @FXML
    private TextField mdp_txt;
    @FXML
    private TextField abonnement_txt;
    private int index = -1;
    int id_session;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    Connection connexion;
    Statement stm;
    public ObservableList<Client> data = FXCollections.observableArrayList();

    @FXML
    void AfficherClient(ActionEvent event) throws SQLException {
        //CLEAR TABLE VIEW

        for (int i = 0; i < tab_client.getItems().size(); i++) {
            tab_client.getItems().clear();
        }
        connexion = MyDB.getInstance().getConnexion();
        String req = "select * from client";
        stm = connexion.createStatement();
        ResultSet rst = stm.executeQuery(req);
        while (rst.next()) {
            data.add(new Client(rst.getInt("id"),
                    rst.getString("nom"),
                    rst.getString("prenom"),
                    rst.getString("adresse"),
                    rst.getString("mail"),
                    crypt(5, rst.getString("mdp_client")),
                    rst.getInt("id_abonnement")));
        }
        id.setCellValueFactory(new PropertyValueFactory<Client, Integer>("id"));
        nom.setCellValueFactory(new PropertyValueFactory<Client, String>("nom"));
        prenom.setCellValueFactory(new PropertyValueFactory<Client, String>("prenom"));
        adresse.setCellValueFactory(new PropertyValueFactory<Client, String>("adresse"));
        mail.setCellValueFactory(new PropertyValueFactory<Client, String>("mail"));
        mdp_client.setCellValueFactory(new PropertyValueFactory<Client, String>("mdp_client"));
        id_abonnement.setCellValueFactory(new PropertyValueFactory<Client, Integer>("id_abonnement"));
        tab_client.setItems(data);
        //crypt(2,"mdp_client")

    }

    public void Quit(ActionEvent event) {
        System.exit(0);
    }

    @FXML
    void Export(ActionEvent event) {
        ClientService sp = new ClientService();
        sp.export_Client_csv();
        JOptionPane.showMessageDialog(null, "Exported in D:\\Doc\\NetBeansProjects\\PidevJavanb");
    }

    @FXML
    void Bouton_Rechercher(ActionEvent event) throws SQLException {
        int idr = Integer.parseInt(id_text.getText());
        ClientService sp = new ClientService();
        Client pdt = sp.RechecheClient(idr);

        nom_txt.setText(pdt.getNom());
        prenom_txt.setText(pdt.getPrenom());
        adresse_txt.setText(pdt.getAdresse());
        email_txt.setText(pdt.getMail());
        mdp_txt.setText(pdt.getMdp_client());
        abonnement_txt.setText(String.valueOf(pdt.getId_abonnement()));

    }

    @FXML
    void Bouton_Supprimer(ActionEvent event) {
        int idr = Integer.parseInt(id_text.getText());
        ClientService sp = new ClientService();
        sp.SupprimerCliente(idr);
        JOptionPane.showMessageDialog(null, "Client supprimé");
    }

    @FXML
    void bouton_modifier(ActionEvent event) {
        int idr = Integer.parseInt(id_text.getText());
        ClientService sp = new ClientService();
        sp.modifierCliente(idr, nom_txt.getText(), prenom_txt.getText(), adresse_txt.getText(), email_txt.getText(), mdp_txt.getText(), Integer.parseInt(abonnement_txt.getText()));
        JOptionPane.showMessageDialog(null, "Client modifié");
    }

    /**
     *
     * @param event
     */
    public void getSelected(MouseEvent event) {
        index = tab_client.getSelectionModel().getSelectedIndex();
        if (index <= -1) {

            return;
        }
        id_text.setText(id.getCellData(index).toString());
        nom_txt.setText(nom.getCellData(index));
        prenom_txt.setText(prenom.getCellData(index));
        adresse_txt.setText(adresse.getCellData(index));
        email_txt.setText(mail.getCellData(index));
        mdp_txt.setText(mdp_client.getCellData(index));
        abonnement_txt.setText(id_abonnement.getCellData(index).toString());
    }

    @FXML
    private void printRec(ActionEvent event) {
        System.out.println("Impression en cours...!");
        PrinterJob job = PrinterJob.createPrinterJob();
        if (job != null) {
            Window primaryStage = null;
            job.showPrintDialog(primaryStage);
            Node root = this.tab_client;
            job.printPage(root);
            job.endJob();
        }
    }

    // -----------------------------------DEBUT CRYPTAGE-----------------------------------//
    static char[] alphabet1 = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'};

    public static String crypt(int n, String sIn) {

        char[] charSIn = sIn.toCharArray();
        char[] charSOut = new char[charSIn.length];
        int pos1, pos2;
        for (int i = 0; i < charSIn.length; i++) {
            pos1 = posChar(charSIn[i], alphabet1);
            pos2 = newPos(pos1, n);
            if (pos2 == -1) {
                charSOut[i] = '7'; // si -1, c'est que ce n'est pas une lettre, on met un espace à la place    
            } else {
                charSOut[i] = alphabet1[pos2];
            }
        }
        return new String(charSOut); // on fait un string avec le tableau de char
    }

    public static String decrypt(int n, String sIn) {
        /*
         * La marche a suivre est la meme que pour le crypt sauf qu'on prend un n' = -n;  
         */

        char[] charSIn = sIn.toCharArray();
        char[] charSOut = new char[charSIn.length];
        int pos1, pos2;
        for (int i = 0; i < charSIn.length; i++) {
            pos1 = posChar(charSIn[i], alphabet1);
            pos2 = newPos(pos1, -n);
            if (pos2 == -1) {
                charSOut[i] = ' '; // si -1, c'est que ce n'est pas une lettre, on met un espace à la place    
            } else {
                charSOut[i] = alphabet1[pos2];
            }
        }
        return new String(charSOut); // on fait un string avec le tableau de char  
    }

    // Renvoie la position du caractere dans le tableau  
    // -1 si il n'est pas dans le tableau  
    private static int posChar(char c, char[] tab) {
        for (int i = 0; i < tab.length; i++) {
            if (tab[i] == c) {
                return i;
            }
        }
        return -1;
    }

    // Donne la nouvelle position dans l'alphabet en fonction de n  
    private static int newPos(int pos, int n) {
        int pos2 = pos;
        if (pos <= -1) { // -1 signifie que le caractere n'a pas été trouvé dans l'alphabet (caractere spécial, chiffre, espace, etc.)
            pos2 = -1;
        } else {
            int i = 0;
            while (i < abs(n)) {
                if (n < 0) {
                    if (pos2 - 1 == -1) {
                        pos2 = 25;
                    } else {
                        pos2--;
                    }
                } else {
                    if (pos2 + 1 >= 25) {
                        pos2 = 0;
                    } else {
                        pos2++;
                    }
                }
                i++;
            }
        }
        return pos2;
    }

    // Valeur absolue de a  
    public static int abs(int a) {
        if (a >= 0) {
            return a;
        } else {
            return -a;
        }
    }
    // -----------------------------------FIN CRYPTAGE-----------------------------------//

    /*---------------------------------------NAVIGATION BAR--------------------------------------------*/
    @FXML
    private Button go_abonnement;

    @FXML
    void Go_abonnement(ActionEvent event) throws IOException {
        go_abonnement.getScene().getWindow().hide();
        Parent root = FXMLLoader.load(getClass().getResource("VGestion_abonnement.fxml"));
        Stage mainStage = new Stage();
        Scene scene = new Scene(root);
        mainStage.setScene(scene);
        mainStage.show();
    }

    @FXML
    private Button go_logout;

    @FXML
    void go_logout(ActionEvent event) throws IOException {
        id_session = 0;
        go_logout.getScene().getWindow().hide();
        Parent root = FXMLLoader.load(getClass().getResource("VGestion_client.fxml"));
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
        Parent root = FXMLLoader.load(getClass().getResource("VGestion_clientDashboard.fxml"));
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
        Parent root = FXMLLoader.load(getClass().getResource("VGestion_seance/AfficherSeanceFXML.fxml"));
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
        Parent root = FXMLLoader.load(getClass().getResource("VGestion_seance/AfficherCoachFXML.fxml"));
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
        Parent root = FXMLLoader.load(getClass().getResource("VGestion_produit/AccueilA.fxml"));
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
        Parent root = FXMLLoader.load(getClass().getResource("VGestion_materiel/materiel.fxml"));
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
        Parent root = FXMLLoader.load(getClass().getResource("VGestion_materiel/fournisseur.fxml"));
        Stage mainStage = new Stage();
        Scene scene = new Scene(root);
        mainStage.setScene(scene);
        mainStage.show();

    }
    /*---------------------------------------NAVIGATION BAR--------------------------------------------*/

}   //FIN
