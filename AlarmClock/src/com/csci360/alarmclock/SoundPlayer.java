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
public class SoundPlayer 
{
    String sound;
    public SoundPlayer()
    {
        this.sound = "";
    }
    
    public String sound()
    {
        return this.sound;
    }
    
    public void sound(String sound)
    {
        this.sound = sound;
    }
}
