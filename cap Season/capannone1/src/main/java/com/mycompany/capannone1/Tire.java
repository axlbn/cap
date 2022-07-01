/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.capannone1;

/**
 *
 * @author AXEL
 */
public class Tire {
    private String brand;
    private int length;
    private int percentage;
    private int wheelRim_radius;
    private String load_speed_index;
    private float price;
    private String season;//S=summer W=winter A=all season

    public Tire(String brand, int lenght, int percentage, int wheelRim_radius, String load_speed_index, float price,String season) {
        this.brand = brand;
        this.length = lenght;
        this.percentage = percentage;
        this.wheelRim_radius = wheelRim_radius;
        this.load_speed_index = load_speed_index;
        this.price = price;
        this.season=season;
    }
    

    public String getSeason() {
        return season;
    }

    public void setSeason(String season) {
        this.season = season;
    }
    
    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public int getPercentage() {
        return percentage;
    }

    public void setPercentage(int percentage) {
        this.percentage = percentage;
    }

    public int getWheelRim_radius() {
        return wheelRim_radius;
    }

    public void setWheelRim_radius(int wheelRim_radius) {
        this.wheelRim_radius = wheelRim_radius;
    }

    public String getLoad_speed_index() {
        return load_speed_index;
    }

    public void setLoad_speed_index(String load_speed_index) {
        this.load_speed_index = load_speed_index;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }
    /*public String misuraToString(){
        return length+" "+percentage+" "+load_speed_index;
    }*/
    @Override
    public String toString() {
        return "Pneumatico{" + "marca=" + brand + ", larghezza=" + length + ", percentuale=" + percentage + ", raggio_cerchio=" + wheelRim_radius + ", indice_carico_vel=" + load_speed_index + ", prezzo=" + price + '}';
    }
    
    
    
}
