/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import entities.Coach;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
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
   
    

    /**
     * Initializes the controller class.
     */
   @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
   private void AddPerson(ActionEvent event) {
        CoachService cs = new CoachService();
        Coach s = new Coach(ICoach_name.getText(),ISpecialite.getText(),IMail.getText(),IPwd.getText());
        
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
