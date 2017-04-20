/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.csci360.alarmclockgui;

import alarmclockgui.*;
import java.io.File;
import javafx.scene.media.Media;

/**
 *
 * @author tim
 */
public class Channel 
{
    public String fileString;
    public String frequency;
    public Media sound;
    
    public Channel()
    {
        this.fileString = "static.wav";
        this.frequency = "0.00";
        this.sound = new Media(new File(this.fileString).toURI().toString()); 
    }
    
    public Channel(String fileString, String frequency)
    {
        this.fileString = fileString;
        this.frequency = frequency;
        this.sound = new Media(new File(this.fileString).toURI().toString());
    }
    
    public void fileString(String fileString)
    {
        this.fileString = fileString;
        this.sound = new Media(new File(this.fileString).toURI().toString());
    }
    
    public String fileString()
    {
        return this.fileString;
    }
    
    public Media sound()
    {
        return this.sound;
    }
    
    public void frequency(String frequency)
    {
        this.frequency = frequency;
    }
    
    public String frequency()
    {
        return this.frequency;
    }
    
}