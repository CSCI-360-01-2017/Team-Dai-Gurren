/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.csci360.alarmclock;

import alarmclock.*;

/**
 *
 * @author tim
 */
public class Channel 
{
    public String sound;
    public int frequency;
    
    public Channel()
    {
        this.sound = "White Noise";
        this.frequency = 0;
    }
    
    public Channel(String sound, int frequency)
    {
        this.sound = sound;
        this.frequency = frequency;
    }
    
    public void sound(String sound)
    {
        this.sound = sound;
    }
    
    public String sound()
    {
        return this.sound;
    }
    
    public void frequency(int frequency)
    {
        this.frequency = frequency;
    }
    
    public int frequency()
    {
        return this.frequency;
    }
    
}