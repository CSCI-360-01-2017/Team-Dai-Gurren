/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.csci360.alarmclock;

/**
 *
 * @author tim
 */
public class Time 
{
    private int hour;
    private int minute;
    private int second;
    
    public Time()
    {
        this.hour = 12;
        this.minute = 0;
        this.second = 0;
    }
    
    public Time(int hour, int minute, int second)
    {
        this.hour = hour;
        this.minute = minute;
        this.second = second;
    }
    
    public Time(Time time)
    {
        this.hour = time.hour();
        this.minute = time.minute();
        this.second = time.second();
    }
    
    public void step()
    {
        step(0, 0, 1);
    }
    
    public void step(int hour, int minute, int second)
    {
        this.second += second;
        this.minute += this.second  / 60 + minute;
        this.hour += this.minute / 60 + hour;
        this.hour %= 24;
        this.minute %= 60;
        this.second %= 60;
    }
    
    public int compareTo(Time time)
    {
        return this.hour > time.hour ? 1 : 
                this.hour < time. hour ? -1 :
                this.minute > time.minute ? 1 :
                this.minute < time.minute ? -1 :
                this.second > time.second ? 1 :
                this.second < time.second ? -1 : 0;
                
    }
    
    public void hour(int hour)
    {
        this.hour = hour;
    }
    
    public int hour()
    {
        return this.hour;
    }
    
    public void minute(int minute)
    {
        this.minute = minute;
    }
    
    public int minute()
    {
        return this.minute;
    }
    
    public void second(int second)
    {
        this.second = second;
    }
    
    public int second()
    {
        return this.second;
    }
}
