/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.capannone1;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 *
 * @author canel
 */
public class TextFile {
    private char mode;
    private BufferedReader reader;
    private BufferedWriter writer;

    public TextFile(String filename, char mode) throws IOException{
        this.mode = 'R';
        if(mode=='W' || mode=='w'){
            this.mode = 'W';
            writer = new BufferedWriter(new FileWriter(filename));
        }else{
            reader = new BufferedReader(new FileReader(filename));
        }
    }
    
    public void toFile(String line) throws IOException, Exception{
        if(this.mode == 'R')
            throw new Exception("Read only");
        writer.write(line);
        writer.newLine();
    }
    
    public String fromFile() throws IOException, Exception{
        String tmp;
        if(this.mode == 'W')
            throw new Exception("Write only");
        tmp = reader.readLine();
        return tmp;
    }
    
    public void closeFile() throws IOException{
        if(this.mode == 'W')
            writer.close();
        else
            reader.close();
    }
}

