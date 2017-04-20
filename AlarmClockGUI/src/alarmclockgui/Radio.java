/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package alarmclockgui;

import com.csci360.alarmclockgui.*;
import alarmclockgui.*;

/**
 *
 * @author tim
 */
public class Radio 
{
    Channel[] channels;
    private int selected;
    private static final int UP = 1;
    private static final int DOWN = -1;
    private static final int AM_FM_SWITCH = 103;
    public boolean isOn;
    
    public Radio()
    {
        channels = new Channel[206];
        selected = 100;
        isOn = true;
        for(int i = 1; i <= 206; i++)
        {
            channels[i-1] = new Channel("static.wav", String.format("%d.00", i));
        }
    }
    
    public Channel[] channels()
    {
        return this.channels;
    }
    
    public void selected(int selected)
    {
        this.selected = selected;
    }
    
    public int selected()
    {
        return this.selected;
    }
    
    public Channel channel(int index)
    {
        return this.channels[index-1];
    }
    
    public Channel currentChannel()
    {
        return this.channels[this.selected - 1];
    }
    
    public void toggleFrequency()
    {
        this.selected = (this.selected + (this.channels.length - AM_FM_SWITCH)) % this.channels.length;
    }
    
    public void toggleOn()
    {
        this.isOn = !this.isOn;
    }
    
    public void scan( int direction )
    {
       int newChannel = this.selected + direction;
       while(newChannel < this.channels.length && newChannel > 0
               && this.channel(newChannel).fileString().compareTo("static.wav") == 0)
           newChannel += direction;
       if( newChannel > 0 && newChannel < this.channels.length)
           this.selected = newChannel;
    }
}
