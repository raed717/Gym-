/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.VGestion_produit;

import java.io.FileOutputStream;
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
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
 import entities.Facture;
 


import utils.MyDB;

/**
 * FXML Controller class
 *
 * @author firas
 */
public class FactureController implements Initializable {
        Connection connexion;
    Statement stm;

   @FXML
    private Button Bt_Dashboard1;

    @FXML
    private Button Bt_Dashboard11;

    @FXML
    private TableView<Facture> Tableau;

    @FXML
    private Button button_display;
@FXML
    private Button button_exporter;
    @FXML
    private Button button_quitter;

    @FXML
    private TableColumn<Facture, Integer> id_f;

    @FXML
    private TableColumn<Facture, Integer>id;

    @FXML
    private TableColumn<Facture, Float> total;
    
     public ObservableList<Facture> data = FXCollections.observableArrayList();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    @FXML
    public void Display(ActionEvent event) throws SQLException {

                    connexion = MyDB.getInstance().getConnexion();

 String req = "SELECT * from facture";
        stm = connexion.createStatement();
         ResultSet rst = stm.executeQuery(req);
        while (rst.next()) {
            data.add(new Facture (rst.getInt("id_f"),
                    rst.getInt("id"),
                    rst.getFloat("total")));
        }
   
          id_f.setCellValueFactory(new PropertyValueFactory<Facture, Integer>("id_f"));
          id.setCellValueFactory(new PropertyValueFactory<Facture, Integer>("id"));
          total.setCellValueFactory(new PropertyValueFactory<Facture, Float>("total"));
           Tableau.setItems(data);

}
        @FXML

      private void Exporter (ActionEvent event) {
      
try{
    
String filename=("c:/Excel/test3.xls ") ;
 HSSFWorkbook hwb=new HSSFWorkbook();
HSSFSheet sheet =  hwb.createSheet("new sheet");

HSSFRow rowhead=sheet.createRow((short)0); 
rowhead.createCell((short) 0).setCellValue("id_f");
 rowhead.createCell((short) 1).setCellValue("id");
rowhead.createCell((short) 2).setCellValue("total");
 

Statement st=connexion.createStatement();
ResultSet rs=st.executeQuery("SELECT id_f, id, total from facture" );
int i=1;
while(rs.next()){
HSSFRow row= sheet.createRow((short)i);

row.createCell((short) 0).setCellValue(Integer.toString(rs.getInt("id_f")));
row.createCell((short) 1).setCellValue(Integer.toString(rs.getInt("id")));
row.createCell((short) 2).setCellValue(Float.toString(rs.getFloat("total")));
 i++;
}
    try (FileOutputStream fileOut = new FileOutputStream(filename)) {
        hwb.write(fileOut);
    }
System.out.println("Your excel file has been generated!");

} catch ( IOException | SQLException ex ) {
    System.out.println(ex);

}
    }
    
    
}