/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package geeks;

import entities.Coach;
import entities.Seance;
import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import services.CoachService;
import services.SeanceService;
/**
 *
 * @author saisi
 */
public class Geeks extends Application  {
    
   AnchorPane parent;
  Stage stage;
    
    
   @Override
    public void start(Stage primaryStage) {
        this.stage = primaryStage;
        try {
            parent = FXMLLoader.load(getClass().getResource("../View/SeanceFXML.fxml"));
             
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        Scene scene = new Scene(parent);
        stage.setScene(scene);
        stage.setTitle("Add");
        stage.show();
    }

    

    /**
     * @param args the command line arguments
     * @throws java.sql.SQLException
     */
    public static void main(String[] args) throws SQLException {
        
        launch(args);
       //Seance s = new Seance("boxe","13PM","15PM",1);
      // SeanceService ps = new SeanceService();
        //Coach c = new Coach("ahmed sbika","boxe","ahm@gmail.com","12345623");
       //CoachService cs = new CoachService();
       //cs.ajouterCoach(c);
       //ps.ajouterSeance(s);
        //System.out.println(ps.afficherSeance());
        //ps.SupprimerSeance(2);
        //ps.modifierSeance(8,"kick","19pm","20PM");

    }
    
}

