/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import services.SeanceService;
import entities.Seance;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Properties;
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
 * @author saisi
 */
public class SeanceFXMLController implements Initializable {

    @FXML
    private ComboBox<String> comb;
    @FXML
    private DatePicker DateNaissance_Input;
    @FXML
    private ImageView Photo_Input;
    @FXML
    private Button button_Submit;
    @FXML
    private Text alertNom;
    @FXML
    private Text alertCIN;
    @FXML
    private Text alertPrenom;
    @FXML
    private Text alertAdresse;
    @FXML
    private Text alertDatenaissance;
    @FXML
    private Text alertEmail;
    @FXML
    private Text alertRole;
    @FXML
    private Text alertTel;
    @FXML
    private DatePicker DateNaissance_Input1;
    @FXML
    private ImageView Photo_Input1;
    @FXML
    private Button button_Submit1;
    @FXML
    private Text alertNom1;
    @FXML
    private Text alertCIN1;
    @FXML
    private Text alertAdresse1;
    @FXML
    private Text alertRole1;
    @FXML
    private Text alertTel1;
    @FXML
    private TextField Nom_Input;
    @FXML
    private TextField ICoach_name;
    @FXML
    private DatePicker IDate_debut;
    @FXML
    private DatePicker IDate_fin;
    private String username = "saisidiheb@gmail.com";
    private String password = "brandlvl1";
    @FXML
    private Button Envoyer;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ObservableList<String> list = FXCollections.observableArrayList("boxe","taekwondo","aerobic","MMA");
        comb.setItems(list);
         }
        @FXML
    private void AddPerson(ActionEvent event) {
        SeanceService ps = new SeanceService();
        String datetest = IDate_debut.getValue().toString();
        String datetest2 = IDate_fin.getValue().toString();
        Seance s = new Seance(comb.getValue(),datetest,datetest2,Integer.valueOf(ICoach_name.getText()));
        
        try {
            ps.ajouterSeance(s);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Success");
            alert.setContentText("Person is added successfully!");
            alert.show();
            ICoach_name.setText("");
            Timestamp ca = new Timestamp(0);
            IDate_debut.setValue(ca.toLocalDateTime().toLocalDate());  
            IDate_fin.setValue(ca.toLocalDateTime().toLocalDate()); 
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
        
        
        
   
    }    

    @FXML
    private void handleButtonSubmitAction(ActionEvent event) {
    }
    @FXML
    private void Envoyer (ActionEvent event)    {
        
// Etape 1 : Création de la session
Properties props = new Properties();
props.put("mail.smtp.auth", "true");
props.put("mail.smtp.starttls.enable","true");
props.put("mail.smtp.host","smtp.gmail.com");
props.put("mail.smtp.port","587");
Session session = Session.getInstance(props,
new javax.mail.Authenticator() {
protected PasswordAuthentication getPasswordAuthentication() {
return new PasswordAuthentication(username, password);
}
});
try {
// Etape 2 : Création de l'objet Message
Message message = new MimeMessage(session);
message.setFrom(new InternetAddress("saisidiheb@gmail.com"));
message.setRecipients(Message.RecipientType.TO,
InternetAddress.parse("raed.guembri@esprit.tn"));
message.setSubject("Test email");
message.setText("Bonjour, ce message est un test ...");
// Etape 3 : Envoyer le message
Transport.send(message);
System.out.println("Message_envoye");
} catch (MessagingException e) {
throw new RuntimeException(e);
} }
//Etape 4 : Tester la méthode


        
    }
    

