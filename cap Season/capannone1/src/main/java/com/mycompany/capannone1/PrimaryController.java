package com.mycompany.capannone1;

import static com.mycompany.capannone1.Global.b_now;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.util.Callback;
import static com.mycompany.capannone1.Global.tireMeasuresList;
import static com.mycompany.capannone1.Global.totalTiresList;
import static com.mycompany.capannone1.Global.brandList;
import static com.mycompany.capannone1.Global.m_now;
import static com.mycompany.capannone1.Global.pneumaticiColumnList;
import java.text.BreakIterator;
import javafx.css.PseudoClass;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableRow;

public class PrimaryController implements Initializable {

    @FXML
    private TableView<Measure> TableViewMisure;
    @FXML
    private TableView<Tire> TableViewPneumatici;
    @FXML
    private TableColumn<Measure, String> TableColumnMisure;
    @FXML
    private TableColumn<?, ?> TableColumnPneumatici;
    @FXML
    private Button btn_aggiungiPneumatico;
    @FXML
    private TableView<Brand> TableViewMarca;
    @FXML
    private TableColumn<Brand, String> TableColumnMarca;
    @FXML
    private TableColumn<Tire, Integer> TableColumnPrezzo;
    @FXML
    private TableColumn<Tire, String> TableColumn_C_V;
    protected ObservableList<Season> seasonList = FXCollections.observableArrayList();

    protected ObservableList<Brand> C_V_prezzoList = FXCollections.observableArrayList();
    private int index;
    private Season stag_now;
    private Brand b_now_test;//this is needed for setTableColumnMarca so it's null only when intialized and it's not set null in afterSwitchSceneSetting while b_now yes 
    @FXML
    private Button btn_venduto;
    @FXML
    private Button btn_modifica;
    @FXML
    private TableView<Season> TableViewStag;
    @FXML
    private TableColumn<Season, String> TableColumnStag;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        setUp();

    }
    private void setUp(){
        TableViewPneumatici.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        try {
            Global.loadFromFile();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        Global.sort_totalTiresList();
        Global.make_tireMeasuresList();
      
        setTableColumnMisure();
        
        setTableColumnMarca();
        setTableColumnPnueumatici();
        loadLastSelectedItemInList();
    }
    private void loadLastSelectedItemInList() {
        if (Global.ifFromAggiungiPneumaticoController == true) {
            if (m_now != null) {
                TableViewMisure.getSelectionModel().clearSelection();
                Global.tireMeasuresList.forEach((t) -> {
                    if (t.toString().compareToIgnoreCase(m_now.toString()) == 0) {
                        index = Global.tireMeasuresList.indexOf(t);
                    }
                });
                TableViewMisure.getSelectionModel().select(index);
            }
            if (b_now != null) {
                TableViewMarca.getSelectionModel().clearSelection();
                Global.brandList.forEach((t) -> {
                    if (t.getBrand().compareToIgnoreCase(b_now.getBrand()) == 0) {
                        index = Global.brandList.indexOf(t);
                    }
                });
                TableViewMarca.getSelectionModel().select(index);
            }
            b_now=null;
            

            Global.ifFromAggiungiPneumaticoController = false;
        }
    }

    public void setTableColumnMisure() {
        TableColumnMisure.setCellValueFactory(new PropertyValueFactory<Measure, String>("stringTot"));
        TableViewMisure.setItems(Global.tireMeasuresList);
    }
    private void setTableColumnStag(){
        Season s;
        if (m_now == null || b_now_test==null) {
            return;
        }
        ArrayList<Season> provv = new ArrayList<Season>();
        Brand brand;
        for (int i = 0; i < totalTiresList.size(); i++) {
            if (totalTiresList.get(i).getLength() == m_now.getLength() && totalTiresList.get(i).getPercentage() == m_now.getPercentage() && totalTiresList.get(i).getWheelRim_radius() == m_now.getWheelRim_radius() && totalTiresList.get(i).getBrand().equalsIgnoreCase(b_now_test.getBrand())) {
                s=new Season(totalTiresList.get(i).getSeason());
                provv.add(s);
            }
        }
        if (provv.isEmpty() == true) {
            return;
        }
        s = new Season(provv.get(0).getSeason());
        seasonList.add(s);
        boolean var = true;
        for (int i = 0; i < provv.size(); i++) {
            for (int j = 0; j < seasonList.size(); j++) {
                if (provv.get(i).getSeason().compareToIgnoreCase(seasonList.get(j).getSeason())==0) {
                    seasonList.get(j).setQuantity(seasonList.get(j).getQuantity() + 1);
                    System.out.println(seasonList.get(j).getQuantity());
                    var = false;
                }
            }
            if (var == true) {
                s = new Season(provv.get(i).getSeason());
                seasonList.add(s);
                seasonList.get(seasonList.size() - 1).setQuantity(seasonList.get(seasonList.size() - 1).getQuantity() + 1);
            }
            var = true;
        }
        TableColumnStag.setCellValueFactory(new PropertyValueFactory<Season, String>("stringTot"));
        TableViewStag.setItems(seasonList);
    }
     private void setTableColumnMarca() {
        brandList.clear();
        if (m_now == null) {
            return;
        }
        ArrayList<Tire> provv = new ArrayList<Tire>();
        Brand brand;
        for (int i = 0; i < totalTiresList.size(); i++) {
            if (totalTiresList.get(i).getLength() == m_now.getLength() && totalTiresList.get(i).getPercentage() == m_now.getPercentage() && totalTiresList.get(i).getWheelRim_radius() == m_now.getWheelRim_radius() ) {
                provv.add(totalTiresList.get(i));
            }
        }
        if (provv.isEmpty() == true) {
            return;
        }
        brand = new Brand(provv.get(0).getBrand());
        brandList.add(brand);
        boolean var = true;
        for (int i = 0; i < provv.size(); i++) {
            for (int j = 0; j < brandList.size(); j++) {
                if (provv.get(i).getBrand().compareToIgnoreCase(brandList.get(j).getBrand()) == 0) {
                    brandList.get(j).setQuantity(brandList.get(j).getQuantity() + 1);
                    var = false;
                }
            }
            if (var == true) {
                brand = new Brand(provv.get(i).getBrand());
                brandList.add(brand);
                brandList.get(brandList.size() - 1).setQuantity(brandList.get(brandList.size() - 1).getQuantity() + 1);
            }
            var = true;
        }
        TableColumnMarca.setCellValueFactory(new PropertyValueFactory<Brand, String>("stringTot"));
        TableViewMarca.setItems(brandList);
    }
     
     
     private void setTableColumnPnueumatici() {
        pneumaticiColumnList.clear();
        if (b_now == null) {
            return;
        }
        for (int cont = 0; cont < totalTiresList.size(); cont++) {
            if (totalTiresList.get(cont).getLength() == m_now.getLength() && totalTiresList.get(cont).getPercentage() == m_now.getPercentage() && totalTiresList.get(cont).getWheelRim_radius() == m_now.getWheelRim_radius()) {
                if (totalTiresList.get(cont).getBrand().compareToIgnoreCase(b_now.getBrand()) == 0) {
                    pneumaticiColumnList.add(totalTiresList.get(cont));
                }
            }
        }
        TableColumnPrezzo.setCellValueFactory(new PropertyValueFactory<Tire, Integer>("price"));
        TableColumn_C_V.setCellValueFactory(new PropertyValueFactory<Tire, String>("load_speed_index"));
        TableViewPneumatici.setItems(pneumaticiColumnList);
    }
    @FXML
    private void MisureTable_click(MouseEvent event) {
        
        m_now = TableViewMisure.getSelectionModel().getSelectedItem();
        brandList.clear();
        seasonList.clear();
        pneumaticiColumnList.clear();
        setTableColumnMarca();

    }
     @FXML
    private void StagionalitaTable_click(MouseEvent event) {
        stag_now=TableViewStag.getSelectionModel().getSelectedItem();
    }
    
    
   

    @FXML
    private void btn_aggiungiPneumatico_click(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("aggiungiPneumatico.fxml"));
        Scene scene = new Scene(root);
        Stage secondaryStage = new Stage();
        secondaryStage.setTitle("Gestione Magazziono//Aggiungi Pneumatico");
        secondaryStage.setScene(scene);
        secondaryStage.setResizable(false);
        secondaryStage.show();
    }

    @FXML
    private void TableViewMarca_click(MouseEvent event) {
        //pneumaticiColumnList.clear();
        seasonList.clear();
        b_now = TableViewMarca.getSelectionModel().getSelectedItem();
        b_now_test=b_now;
        //setTableColumnPnueumatici();
        setTableColumnStag();    
    }

    

    @FXML
    private void btn_venduto_click(ActionEvent event) {
        ObservableList<Tire> removeList=TableViewPneumatici.getSelectionModel().getSelectedItems();
        Global.totalTiresList.removeAll(removeList);
        try {
            Global.saveOnFile();
        } catch (Exception ex) {
        }
         Global.clearLists();
         
         setUp();
    }

    @FXML
    private void btn_modifica_click(ActionEvent event) {
        
    }

  
}
