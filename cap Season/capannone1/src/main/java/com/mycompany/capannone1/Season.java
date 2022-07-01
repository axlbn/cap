/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.capannone1;

/**
 *
 * @author canel
 */
public class Season {
    private String season;
    private int quantity;
    private String stringTot;
    public Season(String season) {
        this.season = season;
        this.quantity = 0;
        stringTot="";
    }

    public String getStringTot() {
        String s=season+"  ("+quantity+")";
        return s.toUpperCase();
    }

    public String getSeason() {
        return season;
    }

    public void setSeason(String season) {
        this.season = season;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "Season{" + "season=" + season + ", quantity=" + quantity + ", stringTot=" + stringTot + '}';
    }
    
   
}
