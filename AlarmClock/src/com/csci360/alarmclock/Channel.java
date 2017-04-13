/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package alarmclockgui;

/**
 *
 * @author tim
 */
public class Channel 
{
    public String sound;
    public String frequency;
    
    public Channel()
    {
        this.sound = "White Noise";
        this.frequency = "0.00";
    }
    
    public Channel(String sound, String frequency)
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
    
    public void frequency(String frequency)
    {
        this.frequency = frequency;
    }
    
    public String frequency()
    {
        return this.frequency;
    }
    
}