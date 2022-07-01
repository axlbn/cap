/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.capannone1;

import java.io.IOException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author AXEL
 */
public class Global {
    protected static final ObservableList<Tire> totalTiresList = FXCollections.observableArrayList();
    protected static final ObservableList<Measure> tireMeasuresList = FXCollections.observableArrayList();
    protected static String nomeFile="C:\\Users\\canel\\Documents\\NetBeansProjects\\capannone1\\src\\main\\java\\com\\mycompany\\capannone1\\backup.csv";
    protected static   ObservableList<Brand> brandList = FXCollections.observableArrayList();
    protected static ObservableList<Tire> pneumaticiColumnList = FXCollections.observableArrayList();//list that is going to populate the tableview(Pneumatici)
    protected static Measure m_now;//var that contain the mesure pinned in a centain moment
    protected static Brand b_now;//var that contain the brend pinned in a centain moment
    protected static boolean ifFromAggiungiPneumaticoController=false;//if true the user comes to "primary" from "aggiungiPneumatico" .this varuable is needed to manage the css af the tableView row
    public static void print_totalTiresList(){
        totalTiresList.forEach((t) -> { System.out.println(t.toString());});
    }
    public static void clearLists(){
        totalTiresList.clear();
        tireMeasuresList.clear();
        //pneumaticiColumnList.clear();
    }
    public static void sort_totalTiresList(){
        boolean key=true;
        while(key!=false){
            key=false;
            for(int cont=0;cont<totalTiresList.size()-1;cont++){
                if(totalTiresList.get(cont+1).getLength()<totalTiresList.get(cont).getLength()){
                    switchTire(cont);
                    key=true;
                }else{
                    if(totalTiresList.get(cont+1).getLength()==totalTiresList.get(cont).getLength()){
                        if(totalTiresList.get(cont+1).getPercentage()<totalTiresList.get(cont).getPercentage()){
                            switchTire(cont);
                            key=true;
                        }else{
                            if(totalTiresList.get(cont+1).getPercentage()==totalTiresList.get(cont).getPercentage()){
                                if(totalTiresList.get(cont+1).getWheelRim_radius()<totalTiresList.get(cont).getWheelRim_radius()){
                                    switchTire(cont);
                                    key=true;
                                }
                            }
                        }
                    }
                }
            }
        }
    }
    private static void switchTire(int cont){
                Tire scambia;
                scambia=totalTiresList.get(cont);
                totalTiresList.set(cont, totalTiresList.get(cont+1));
                totalTiresList.set(cont+1, scambia);
    }
    public static void make_tireMeasuresList(){//make the list that is gonna populate the first column(Misura)
        if(totalTiresList.size()==0)
            return;
        Measure provv=new Measure(totalTiresList.get(0).getLength(), totalTiresList.get(0).getPercentage(), totalTiresList.get(0).getWheelRim_radius());
        tireMeasuresList.add(provv);
        boolean var=true;
        for(int i=0;i<totalTiresList.size();i++){
            for(int j=0;j<tireMeasuresList.size();j++){
                if(totalTiresList.get(i).getLength()==tireMeasuresList.get(j).getLength() && totalTiresList.get(i).getPercentage()==tireMeasuresList.get(j).getPercentage() && totalTiresList.get(i).getWheelRim_radius()==tireMeasuresList.get(j).getWheelRim_radius()){
                    var=false;
                }
            }
            if(var==true){
                provv=new Measure(totalTiresList.get(i).getLength(), totalTiresList.get(i).getPercentage(), totalTiresList.get(i).getWheelRim_radius());
                tireMeasuresList.add(provv);
            }
            var=true;
        }
    }
    public static  void saveOnFile() throws IOException, Exception{
        TextFile ou = new TextFile(nomeFile, 'W');
        String s = "";
        for(int i=0; i<Global.totalTiresList.size(); i++){
            s = Global.totalTiresList.get(i).getBrand() + "," +
                String.valueOf(Global.totalTiresList.get(i).getLength()) + "," +
                String.valueOf(Global.totalTiresList.get(i).getPercentage()) + "," +
                String.valueOf(Global.totalTiresList.get(i).getWheelRim_radius()) + "," +
                Global.totalTiresList.get(i).getLoad_speed_index()+","+
                String.valueOf(Global.totalTiresList.get(i).getPrice())+","+
                Global.totalTiresList.get(i).getSeason();
            ou.toFile(s);
        }
        ou.closeFile();
    }
    public static void loadFromFile() throws Exception {
         Global.totalTiresList.clear();
        TextFile in = new TextFile(nomeFile, 'R');
        String s = "";
        while(s!=null){
            try {
                s = in.fromFile();
                if(s!=null){
                    Tire p = split(s);
                    Global.totalTiresList.add(p);
                }
            } catch (NullPointerException ex) {};
                
        }
        in.closeFile();
    }
    private static Tire split(String s) throws Exception{
        String[] campi = s.split("\\s*,\\s*");
        if(campi.length!=7)
            throw new Exception();
        
        Tire p = new Tire(campi[0],Integer.valueOf(campi[1]),Integer.valueOf(campi[2]),Integer.valueOf(campi[3]),campi[4],Float.valueOf(campi[5]),campi[6]);
        return p;
    }
    
}

