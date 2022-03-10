/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.VGestion_seance;

import entities.Coach;
import java.awt.Color;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import services.CoachService;

/**
 * FXML Controller class
 *
 * @author saisi
 */
public class CoachFXMLController implements Initializable {

    @FXML
    private TextField ICoach_name;
    @FXML
    private TextField IMail;
    @FXML
    private TextField ISpecialite;
    @FXML
    private TextField IPwd;
    
    @FXML
    private ImageView Photo_Input1;
    @FXML
    private Button button_Submit1;
    @FXML
    private Text alertDatenaissance1;
    @FXML
    private Text alertEmail1;
    @FXML
    private Text alertTel1;
    @FXML
    private Label lblErrors1;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void AddPerson(ActionEvent event) {
        String error = "verifier les champs : ";
        if (true) {
            if ("".equals(ICoach_name.getText())) {
                error = error.concat("nom");
            }
            if ("".equals(ISpecialite.getText())) {
                error = error.concat(" ,specialite");
            }
            if (!IMail.getText().matches("\\w{3,}@\\S+")) {
                error = error.concat(" ,email ");
            }
            setLblError1(Color.red, error);
        } if(error == "verifier les champs : ") {
            setLblError1(Color.green, "bienvenue");
            CoachService cs = new CoachService();
            Coach s = new Coach(ICoach_name.getText(), ISpecialite.getText(), IMail.getText(), IPwd.getText());

            try {
                cs.ajouterCoach(s);
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Success");
                alert.setContentText("Coach is added successfully!");
                alert.show();
                ICoach_name.setText("");
                ISpecialite.setText("");
                IMail.setText("");
                IPwd.setText("");
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }

    }

    private void setLblError1(Color color, String text) {
        lblErrors1.setText(text);
        System.out.println(text);
    }

   

}
