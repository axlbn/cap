/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.capannone1;

import javafx.beans.property.StringProperty;

/**
 *
 * @author AXEL
 */
public class Measure {
    private  int length;
    private  int percentage;
    private  int wheelRim_radius;
    private  String stringTot;
    public Measure(int larghezza, int percentuale, int raggio_cerchio) {
        this.length = larghezza;
        this.percentage = percentuale;
        this.wheelRim_radius = raggio_cerchio;
        stringTot=Integer.toString(larghezza)+" "+Integer.toString(percentuale)+" "+Integer.toString(raggio_cerchio);
    }

    public int getLength() {
        return length;
    }

    public int getPercentage() {
        return percentage;
    }

    public int getWheelRim_radius() {
        return wheelRim_radius;
    }

    public String getStringTot() {
        int l=0;
        for(int i=0;i<Global.totalTiresList.size();i++){
            if(Global.totalTiresList.get(i).getLength()==this.length && Global.totalTiresList.get(i).getPercentage()==this.percentage && Global.totalTiresList.get(i).getWheelRim_radius()==this.getWheelRim_radius())
                l++;
        }
        return stringTot+"  ("+l+")";
    }
    
    @Override
    public String toString() {
        return ""+length + percentage +  wheelRim_radius ;
    }
    
    
}
