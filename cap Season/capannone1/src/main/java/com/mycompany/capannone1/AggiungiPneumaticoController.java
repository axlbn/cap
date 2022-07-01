/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.mycompany.capannone1;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author canel
 */
public class AggiungiPneumaticoController implements Initializable {

    @FXML
    private TextField TF_marca;
    @FXML
    private TextField TF_misura;
    @FXML
    private TextField TF_indice_C_V;
    @FXML
    private TextField TF_prezzo;
    @FXML
    private Button btn_fatto;
    @FXML
    private Button btn_indietro;
    @FXML
    private Spinner<Integer> Spinner_Quantita;
    @FXML
    private RadioButton RB_Summer;
    @FXML
    private RadioButton RB_winter;
    @FXML
    private RadioButton RB_AllSeason;
    @FXML
    private ToggleGroup tgSeason;
    @FXML
    private Label lbl_errore;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
        Global.ifFromAggiungiPneumaticoController=true;
            
        SpinnerValueFactory<Integer> valueFactory=new SpinnerValueFactory.IntegerSpinnerValueFactory(1,100);
        valueFactory.setValue(1);
        Spinner_Quantita.setValueFactory(valueFactory);
    }    

    @FXML
    private void btn_fatto_click(ActionEvent event) throws IOException, Exception {
        String season = "";
        if (RB_Summer.isSelected()) {
            season = "summer";
        }
        if (RB_winter.isSelected()) {
            season = "winter";
        }
        if (RB_AllSeason.isSelected()) {
            season = "all season";
        }
        if (RB_Summer.isSelected() == false && RB_winter.isSelected() == false && RB_AllSeason.isSelected() == false) {
            lbl_errore.setText("Errore:Inserire la stagionalità");
            lbl_errore.setVisible(true);
        } else {
            String misura[];
            misura = TF_misura.getText().split(" ");
            Tire p = new Tire(TF_marca.getText(), Integer.parseInt(misura[0]), Integer.parseInt(misura[1]), Integer.parseInt(misura[2]), TF_indice_C_V.getText(), Float.parseFloat(TF_prezzo.getText()), season);
            for (int i = 0; i < Spinner_Quantita.getValue(); i++) {
                Global.totalTiresList.add(p);
            }
            Global.saveOnFile();
            Global.clearLists();
            App.setRoot("primary");
            Stage stage = (Stage) btn_indietro.getScene().getWindow();
            stage.close();
        }
    }

    @FXML
    private void btn_indietro_click(ActionEvent event) throws IOException  {
        Global.clearLists();
        App.setRoot("primary");
        Stage stage = (Stage) btn_indietro.getScene().getWindow();
        stage.close();
        
        
    }
    
}
