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
public class Radio 
{
    Channel[] channels;
    private int selected;
    private static final int UP = 1;
    private static final int DOWN = -1;
    private static final int AM_FM_SWITCH = 103;
    
    public Radio()
    {
        channels = new Channel[206];
        selected = 1;
        for(int i = 1; i <= 206; i++)
        {
            channels[i-1] = new Channel("White Noise", i);
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
        return this.channels[this.selected];
    }
    
    public void toggleFrequency()
    {
        this.selected = (this.selected + (this.channels.length - AM_FM_SWITCH)) % this.channels.length;
    }
    
    public void scan( int direction )
    {
        this.selected += direction;
        while(this.selected < this.channels.length && this.selected > 0 
                && this.channel( this.selected ).sound().compareTo("White Noise") == 0)
        {
            this.selected += direction;
        }
    }
}
