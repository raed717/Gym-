/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.VGestion_produit;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;

/**
 * FXML Controller class
 *
 * @author firas
 */
public class ChartController implements Initializable {

    @FXML
    private BarChart<?, ?> Chart;

    @FXML
    private Button bt;

    @FXML
    private CategoryAxis x;

    @FXML
    private NumberAxis y;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        XYChart.Series set1= new XYChart.Series<>() ;  
        set1.getData().add(new XYChart.Data("weight" ,5000 ));
               set1.getData().add(new XYChart.Data("protein" ,10000 ));
                             set1.getData().add(new XYChart.Data("protein" ,2000 ));
                             set1.getData().add(new XYChart.Data(" masse" ,8000 ));
 Chart.getData().addAll(set1); 
 
        
        
        
    }    
    
}
