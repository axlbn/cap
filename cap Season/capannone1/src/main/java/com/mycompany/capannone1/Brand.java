/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.capannone1;

/**
 *
 * @author canel
 */
//this calss is used as a struct
public class Brand {
    private String brand;
    private int quantity;
    private String stringTot;
    public Brand(String marca) {
        this.brand = marca;
        this.quantity = 0;
        stringTot="";
    }

    public String getStringTot() {
        return brand+"  ("+quantity+")";
    }
    
    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "Marca{" + "marca=" + brand + ", quantit\u00e0=" + quantity + '}';
    }
    
}
