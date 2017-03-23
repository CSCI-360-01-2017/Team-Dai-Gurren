/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package alarmclock;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author tim
 */
public class Clock implements Runnable, Comparable
{
    private Alarm alarm1;
    private Alarm alarm2;
    private Time time;
    public String message = "";
    private boolean isRunning = false;
    
    public Clock()
    {
        this.time = new Time();
        this.alarm1 = new Alarm();
        this.alarm2 = new Alarm();
    }
    
    public Clock(int hour, int minute, int second)
    {
        this.time = new Time(hour, minute, second);
        this.alarm1 = new Alarm();
        this.alarm2 = new Alarm();
    }
    
    public Clock(Time time)
    {
        this.time = new Time(time);
        this.alarm1 = new Alarm();
        this.alarm2 = new Alarm();
    }
    
    
    public void alarm1(Alarm alarm)
    {
        this.alarm1 = alarm;
    }
    
    public Alarm alarm1()
    {
        return this.alarm1;
    }
    
    public void alarm2(Alarm alarm)
    {
        this.alarm2 = alarm;
    }
    
    public Alarm alarm2()
    {
        return this.alarm2;
    }
    
    public Time time()
    {
        return this.time;
    }
    
    public void time(Time time)
    {
        this.time = time;
    }
    
    public boolean isRunning()
    {
        return this.isRunning;
    }
    
    public void turnOff()
    {
        this.isRunning = false;
    }
    
    public String message()
    {
        return this.message;
    }
    
    public void clearMessage()
    {
        this.message = "";
    }
    
    //TODO: Add static final variables ON, OFF, and ACTIVATED to make this clearer
    public void checkAlarm()
    {
        if(this.alarm1.state() == 0 && this.compareTo(this.alarm1) == 0)
            this.alarm1.activate();
        if(this.alarm2.state() == 0 && this.compareTo(this.alarm2) == 0)
            this.alarm2.activate();
    }
    
    public void snoozeAlarms()
    {
        if(this.alarm1.state() == 1)
            this.alarm1.snooze();
        if(this.alarm2.state() == 1)
            this.alarm2.snooze();
    }
    
    //TODO: Change the checks with a timer that shoots off the check and step functions
    @Override
    public void run()
    {
        this.isRunning = true;
        while(this.isRunning)
        {
            this.time.step();
            this.checkAlarm();
            try {
                Thread.sleep(1000);
            } catch (InterruptedException ex) {
                Logger.getLogger(Clock.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    @Override
    public int compareTo(Object object)
    {
        return this.time().compareTo(((Alarm) object).time());
    }
    
}
