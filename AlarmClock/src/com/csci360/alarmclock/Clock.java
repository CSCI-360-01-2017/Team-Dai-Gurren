/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.csci360.alarmclock;

import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author tim
 */
public class Clock implements Runnable, Comparable
{
    private Time time;
    private boolean isRunning = false;
    private AlarmClockController controller;
    private Timer timer;
    
    private class TimerTick extends TimerTask
    {
        @Override
        public void run()
        {
            Clock.this.tick();
        }
    }
    
    public Clock()
    {
        this.time = new Time();
        this.controller = null;
    }
    public Clock(AlarmClockController controller)
    {
        this.time = new Time();
        this.controller = controller;
    }
    
    public Clock(int hour, int minute, int second, AlarmClockController controller)
    {
        this.time = new Time(hour, minute, second);
        this.controller = controller;
    }
    
    public Clock(Time time)
    {
        this.time = new Time(time);
    }
    
    public Time time()
    {
        return this.time;
    }
    
    public void time(Time time)
    {
        this.time = time;
    }
    
    public void turnOff()
    {
        this.timer.cancel();
    }
    
    public void tick()
    {
        this.time.step();
        this.controller.alarmCheck();
    }
    
    @Override
    public void run()
    {
        this.isRunning = true;
        this.timer = new Timer(true);
        this.timer.scheduleAtFixedRate(new TimerTick(), 1000L, 1000L);
    }
    
    @Override
    public int compareTo(Object object)
    {
        Alarm alarm = (Alarm) object;
        return this.time().compareTo(new Time(alarm.time().hour(), alarm.time().minute(), this.time().second()));
    }
    
}
