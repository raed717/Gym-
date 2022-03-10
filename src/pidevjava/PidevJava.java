package pidevjava;

import entities.Client;
import entities.Abonnement;



import java.awt.Image;
import java.awt.geom.Rectangle2D;
import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import services.ClientService;
import services.AbonnementService;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Screen;
import javafx.stage.Stage;

/**
 * @author macbook
 */
public class PidevJava extends Application  {
    
    static AnchorPane parent;
    Stage stage;
    public static AnchorPane mainLayout;

    
    @Override
    public void start(Stage primaryStage) {
        this.stage = primaryStage;
        try {
            parent = FXMLLoader.load(getClass().getResource("../view/VGestion_Client.fxml""));
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        Scene scene = new Scene(parent);
        stage.setScene(scene);
        stage.setTitle("Gym+");
        stage.show();

    }
      
    
    public static void main(String[] args) throws SQLException {
     launch(args);

       
    }
    
    
}

