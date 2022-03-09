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
            //parent = FXMLLoader.load(getClass().getResource("../view/VGestion_ClientDashboard.fxml"));
            parent = FXMLLoader.load(getClass().getResource("../view/VGestion_Client.fxml"));
            //parent = FXMLLoader.load(getClass().getResource("../view/VGestion_abonnement.fxml"));
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        Scene scene = new Scene(parent);
        //stage.setFullScreen(true);
        stage.setScene(scene);
        stage.setTitle("Gym+");
        stage.show();
        
        // stage.setResizable(false);
    }
      
    
    public static void main(String[] args) throws SQLException {
     launch(args);
// ****************************************ABONNEMENT***************************************************************      
        Abonnement a = new Abonnement(0,"Fitness", 80, "blaaaaaaaa");
        AbonnementService aService = new AbonnementService();

                // ADD NEW Abonnement
        /*try {
            aService.ajouter_ab(a);
            System.out.println("ajout abonnement avec succes");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }*/
        
        //System.out.println(aService.RechecheAbonnement(4));
            
        //aService.SupprimerAbonnement(3);//delete
        //aService.modifier_abonnement(4, "fitnessPro", 100, "description fitness pro" );
        //System.out.println("aService.afficher_Abonnements()");



// ****************************************CLIENT***************************************************************  
        Client p = new Client(0, "mon","pote","fah","mail@mail.com","root",1);
        ClientService sp = new ClientService();
        //sp.afficherPDF();
       
        

        // ADD NEW CLIENT
      /* try {
            sp.ajouterClient(p);
            System.out.println("ajout avec succes");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }*/

        //sp.export_Client_csv();


        // SHOW CLIENTS
        /*try {
            System.out.println(sp.afficherCliente());
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }*/

        // ADD/DELETE CLIENT
        //System.out.println(sp.afficherCliente());
        //sp.SupprimerCliente(3);
        //sp.modifierCliente(2, "firas","saidi","rades","mail@esprit.tn","root",40);

        //tri nom_a
        //sp.tri_clientBy_Nom();

        //recherche_client
        //System.out.println(sp.recherche_client_id());

        //afficherPDF
        //sp.afficherPDF();
    }
    
    
}

