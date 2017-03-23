/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package alarmclock;

/**
 *
 * @author tim
 */
public class Radio 
{
    Channel[] channels;
    int selected;
    
    public Radio()
    {
        channels = new Channel[206];
        selected = 1;
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
}
