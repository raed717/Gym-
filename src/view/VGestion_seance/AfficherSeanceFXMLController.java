/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.VGestion_seance;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import entities.Seance;
import java.awt.Desktop;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import static java.util.Collections.singleton;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseDragEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import services.SeanceService;
import utils.MyDB;
import static sun.font.FontManagerNativeLibrary.load;

/**
 * FXML Controller class
 *
 * @author saisi
 */
public class AfficherSeanceFXMLController implements Initializable {

    Connection connexion;
    Statement stm;

    @FXML
    private TableView<Seance> Tableau;
    @FXML
    private Button Bt_Supprimer;
    @FXML
    private Text alertRechere;
    @FXML
    private TableColumn<Seance, String> Type_seance;
    @FXML
    private TableColumn<Seance, String> Date_debut;
    @FXML
    private TableColumn<Seance, String> Date_fin;
    @FXML
    private TableColumn<Seance, Integer> Id_coach;
    @FXML
    private Button Bt_Search;

    @FXML
    private TableColumn<Seance, Integer> Id_seance;
    @FXML
    private TextField Id_text;

    /**
     *
     */
    public ObservableList<Seance> data = FXCollections.observableArrayList();
    @FXML
    private TextField text_type;
    @FXML
    private TextField txt_debut;
    @FXML
    private TextField txt_fin;
    @FXML
    private TextField txt_coachid;
    @FXML
    private TextField txt_seanceid;
    @FXML
    private Button pdf_btn;
    @FXML
    private Button Bt_Dashboard1;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void SearchEmployer(ActionEvent event) throws SQLException {

        connexion = MyDB.getInstance().getConnexion();

        String req = "select * from tab_seance";
        stm = connexion.createStatement();
        ResultSet rs = stm.executeQuery(req);
        while (rs.next()) {
            data.add(new Seance(rs.getInt("id_seance"),
                    rs.getString("Type_seance"),
                    rs.getString("Date_debut"),
                    rs.getString("Date_fin"),
                    rs.getInt("Id_coach")));

        }

        Type_seance.setCellValueFactory(new PropertyValueFactory<Seance, String>("type_seance"));
        Date_debut.setCellValueFactory(new PropertyValueFactory<Seance, String>("date_debut"));
        Date_fin.setCellValueFactory(new PropertyValueFactory<Seance, String>("date_fin"));
        Id_coach.setCellValueFactory(new PropertyValueFactory<Seance, Integer>("id_coach"));
        Id_seance.setCellValueFactory(new PropertyValueFactory<Seance, Integer>("id_seance"));

        Tableau.setItems(data);

    }

    @FXML
    private void SupprimerEmployer(ActionEvent event) {
        String v = String.valueOf(Id_text.getText());
        //int v =   Integer.parseInt(Id_text.getText()); 
        SeanceService sp = new SeanceService();
        sp.SupprimerSeance(v);

        JOptionPane.showMessageDialog(null, "seance supprimé");
        Tableau.setItems(data);

    }

    public void Quit(ActionEvent event) {
        System.exit(0);
    }

    @FXML
    void RechercherSeance(ActionEvent event) throws SQLException {
        int idr = Integer.parseInt(Id_text.getText());
        SeanceService sp = new SeanceService();
        Seance pdt = sp.RechecheSeance(idr);

        text_type.setText(pdt.getType_seance());
        txt_debut.setText(pdt.getDate_debut());
        txt_fin.setText((pdt.getDate_fin()));
        txt_coachid.setText(String.valueOf(pdt.getId_coach()));
        txt_seanceid.setText(String.valueOf(pdt.getId_seance()));

    }

    @FXML
    public void UpdateSeance(ActionEvent event) throws SQLException {

        int idr = Integer.parseInt(Id_text.getText());

        String type = text_type.getText();
        String debut = txt_debut.getText();
        String fin = txt_fin.getText();
        int coach_id = Integer.parseInt(txt_coachid.getText());

        Seance pdt = new Seance(type, debut, fin, coach_id);
        pdt.setId_seance(idr);

        pdt.setType_seance(type);
        pdt.setDate_debut(debut);
        pdt.setDate_fin(fin);
        pdt.setId_coach(coach_id);
        SeanceService sp = new SeanceService();
        sp.modifierSeance(idr, type, debut, fin, coach_id);

    }

    @FXML
    private void PdfSeance(ActionEvent event) {
        try {

            Document doc = new Document();
            PdfWriter.getInstance(doc, new FileOutputStream("C:\\Users\\firas\\OneDrive\\Desktop\\integration\\GymPlus\\Seance.pdf"));
            doc.open();

            doc.add(new Paragraph(" "));
            doc.add(new Paragraph("liste des seances : "));
            doc.add(new Paragraph(" "));

//                 Paragraph p =new Paragraph();
//                p.add("liste de produit");
//              
//                
//                 
//                
//                
//             doc.add(p);
            PdfPTable t = new PdfPTable(5);
            t.setWidthPercentage(100);
             PdfPCell c = new PdfPCell(new Phrase("id_seance"));
            c.setHorizontalAlignment(Element.ALIGN_CENTER);
            c.setBackgroundColor(BaseColor.GRAY);
            t.addCell(c);
            c = new PdfPCell(new Phrase("typ_seance"));
            c.setHorizontalAlignment(Element.ALIGN_CENTER);
            c.setBackgroundColor(BaseColor.GRAY);
            t.addCell(c);
            c = new PdfPCell(new Phrase("date_debut"));
            c.setHorizontalAlignment(Element.ALIGN_CENTER);
            c.setBackgroundColor(BaseColor.GRAY);
            t.addCell(c);
            c = new PdfPCell(new Phrase("date_fin"));
            c.setHorizontalAlignment(Element.ALIGN_CENTER);
            c.setBackgroundColor(BaseColor.GRAY);
            t.addCell(c);
            c = new PdfPCell(new Phrase("id_coach"));
            c.setHorizontalAlignment(Element.ALIGN_CENTER);
            c.setBackgroundColor(BaseColor.GRAY);
            t.addCell(c);
            doc.add(t);

            Connection cnx = MyDB.getInstance().getConnexion();
            String query = "select * from tab_seance";
            Statement stmt = null;
            stmt = cnx.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            Paragraph p3 = null;
            PdfPTable table = new PdfPTable(5);
            table.setWidthPercentage(100);
            while (rs.next()) {

                PdfPCell c1 = new PdfPCell(new Phrase(rs.getString("id_seance")));
                c1.setHorizontalAlignment(Element.ALIGN_CENTER);
                c1.setBackgroundColor(BaseColor.WHITE);
                table.addCell(c1);

                c1 = new PdfPCell(new Phrase(rs.getString("type_seance")));
                c1.setHorizontalAlignment(Element.ALIGN_CENTER);
                c1.setBackgroundColor(BaseColor.WHITE);
                table.addCell(c1);

                c1 = new PdfPCell(new Phrase(rs.getString("date_debut")));
                c1.setHorizontalAlignment(Element.ALIGN_CENTER);
                c1.setBackgroundColor(BaseColor.WHITE);
                table.addCell(c1);

                c1 = new PdfPCell(new Phrase(rs.getString("date_fin")));
                c1.setHorizontalAlignment(Element.ALIGN_CENTER);
                c1.setBackgroundColor(BaseColor.WHITE);
                table.addCell(c1);
                
                c1 = new PdfPCell(new Phrase(rs.getString("id_coach")));
                c1.setHorizontalAlignment(Element.ALIGN_CENTER);
                c1.setBackgroundColor(BaseColor.WHITE);
                table.addCell(c1);
            }

            doc.add(table);

            doc.close();
            Desktop.getDesktop().open(new File("C:\\Users\\firas\\OneDrive\\Desktop\\integration\\GymPlus\\Seance.pdf"));
        } catch (Exception e) {
            System.err.print(e);
        }

    }
Parent parent;
    Stage stage;
    @FXML
    private void GoToDashbord(ActionEvent event) {
        
          try {
            parent = FXMLLoader.load(getClass().getResource("../VGestion_ClientDashboard.fxml"));
            
         Stage stage = new Stage(); 

         stage.setScene(new Scene(parent));
        stage.setTitle("Dashbord");
        stage.show();
        
        
} catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        
        
    }
}


