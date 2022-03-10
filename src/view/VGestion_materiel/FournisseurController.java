/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.VGestion_materiel;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import entities.fournisseur;
import services.fournisseurCRUD;
import utils.MyDB;
import java.awt.Desktop;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import sun.swing.SwingUtilities2;

/**
 * FXML Controller class
 *
 * @author AMINE
 */
public class FournisseurController implements Initializable {
    @FXML
    private TextField id_f;
    @FXML
    private TextField nom_f;
    @FXML
    private TextField add_f;
    @FXML
    private TextField num_f;
    @FXML
    private TableView<fournisseur> VboxF;
    @FXML
    private Button a_f;
    @FXML
    private Button m_f;
    @FXML
    private Button s_f;
    @FXML
    private Button af_f;
    private Button aj_f;
    @FXML
    private TableColumn<fournisseur, String> nomf;
    @FXML
    private TableColumn<fournisseur, String> adf;
    @FXML
    private TableColumn<fournisseur, Integer> numf;
    fournisseurCRUD fc = new fournisseurCRUD();
    @FXML
    private TableColumn<fournisseur, Integer> idf;
    @FXML
    private Label label;
    @FXML
    private Label label1;
    @FXML
    private Button pdf;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

 
    public void showFournisseur(){
      ObservableList<fournisseur> list = fc.afficherFournisseur();
      idf.setCellValueFactory(new PropertyValueFactory<fournisseur,Integer>("id"));
      nomf.setCellValueFactory(new PropertyValueFactory<fournisseur,String>("nom"));
      adf.setCellValueFactory(new PropertyValueFactory<fournisseur,String>("adresse"));
      numf.setCellValueFactory(new PropertyValueFactory<fournisseur,Integer>("numtel"));
      VboxF.setItems(list);
}

    @FXML
    private void mo_f(ActionEvent event) {
        fc.modifierFournisseur(Integer.parseInt(id_f.getText()),nom_f.getText(),add_f.getText(),Integer.parseInt(num_f.getText()));
        id_f.setText("");
        nom_f.setText("");
        add_f.setText("");
        num_f.setText("");
        showFournisseur();
    }

    @FXML
    private void su_f(ActionEvent event) {
        int id = Integer.parseInt(id_f.getText());
        fc.SupprimerFournisseur(id);
        showFournisseur();
    }

    @FXML
    private void af_f(ActionEvent event) {
        showFournisseur();  
    }

    @FXML
    private void aj_f(ActionEvent event) {
        int test=0;
        int test2=0;
            if (nom_f.getText() != null && !nom_f.getText().isEmpty()) {
              
                test = 1;
                label.setText("");
            } else {
                label.setText("Nom can't be empty");
            }
        if(num_f.getText().length()==8){
                  test2= 1;
                  label1.setText("");
              }else{
                  label1.setText("8 numbers required");
              }
        if((test==1) && (test2==1)){
            fournisseur f = new fournisseur(nom_f.getText(),add_f.getText(),Integer.parseInt(num_f.getText()));
            fc.ajouterFournisseur(f);
            nom_f.clear();
            add_f.clear();
            num_f.clear();
            label.setText("");
            label1.setText("");
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
    
    
    
    
    
    

    @FXML
    private void pdf(ActionEvent event) {
       
    try { 
                 
              
                Document doc = new Document();
                PdfWriter.getInstance(doc, new FileOutputStream("C:\\Users\\firas\\OneDrive\\Desktop\\integration\\GymPlus\\Fournisseur.pdf"));
                doc.open();
                
                
                doc.add(new Paragraph(" "));
               doc.add(new Paragraph("liste des fournisseur : "));
               doc.add(new Paragraph(" "));
                
                
//                 Paragraph p =new Paragraph();
//                p.add("liste de produit");
//              
//                
//                 
//                
//                
//             doc.add(p);
             
                 PdfPTable t = new PdfPTable(4);
                 t.setWidthPercentage(100);
            PdfPCell c = new PdfPCell(new Phrase("id"));
            c.setHorizontalAlignment(Element.ALIGN_CENTER);
            c.setBackgroundColor(BaseColor.GRAY);
            t.addCell(c);
             c = new PdfPCell(new Phrase("nom"));
             c.setHorizontalAlignment(Element.ALIGN_CENTER);
            c.setBackgroundColor(BaseColor.GRAY);
            t.addCell(c);
              c = new PdfPCell(new Phrase("adresse"));
              c.setHorizontalAlignment(Element.ALIGN_CENTER);
            c.setBackgroundColor(BaseColor.GRAY);
            t.addCell(c);
              c = new PdfPCell(new Phrase("numtel"));
              c.setHorizontalAlignment(Element.ALIGN_CENTER);
            c.setBackgroundColor(BaseColor.GRAY);
            t.addCell(c);
            doc.add(t);
               
            
              Connection cnx =MyDB.getInstance().getConnexion();
            String query = "select * from fournisseur";
            Statement stmt = null;
            stmt = cnx.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            Paragraph p3 = null;
                PdfPTable table = new PdfPTable(4);
                 table.setWidthPercentage(100);
               while(rs.next()){ 
           
                 
            PdfPCell  c1 = new PdfPCell(new Phrase(rs.getString("id")));
            c1.setHorizontalAlignment(Element.ALIGN_CENTER);
            c1.setBackgroundColor(BaseColor.WHITE);
            table.addCell(c1);
                 
             c1 = new PdfPCell(new Phrase(rs.getString("nom")));
             c1.setHorizontalAlignment(Element.ALIGN_CENTER);
            c1.setBackgroundColor(BaseColor.WHITE);
            table.addCell(c1);
                 
             c1 = new PdfPCell(new Phrase(rs.getString("adresse")));
             c1.setHorizontalAlignment(Element.ALIGN_CENTER);
            c1.setBackgroundColor(BaseColor.WHITE);
            table.addCell(c1);
            
             c1 = new PdfPCell(new Phrase(rs.getString("numtel")));
             c1.setHorizontalAlignment(Element.ALIGN_CENTER);
            c1.setBackgroundColor(BaseColor.WHITE);
            table.addCell(c1);
               }
               
                doc.add(table);
                
                doc.close();
                Desktop.getDesktop().open(new File("C:\\Users\\firas\\OneDrive\\Desktop\\integration\\GymPlus\\Fournisseur.pdf"));
             } catch (Exception e) {
          System.err.print(e);
        }
  
    
    
    }
}
