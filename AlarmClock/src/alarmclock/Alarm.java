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
public class Alarm
{
    private int state;
    private Time time;
    private Clock parentClock;
    
    private static final int ACTIVATED = 1;
    private static final int ON = 0;
    private static final int OFF = -1;
    
    public Alarm()
    {
        this.time = new Time(12, 0 , 0);
        this.state = 0;
    }
    
    public Alarm( Time time )
    {
        this.time = new Time(time);
        this.state = OFF;
    }
    
    public Alarm( int hour, int minute, int state, Clock parentClock )
    {
        this.time = new Time(hour, minute, 0);
        this.state = state;
        this.parentClock = parentClock;
    }
    
    public void toggle()
    {
        this.state = this.state < 0 ? ON : OFF;
    }
    
    public int state()
    {
        return this.state;
    }
    
    public void time(Time time)
    {
        this.time = new Time(time.hour(), time.minute(), 0);
    }
    
    public Time time()
    {
        return this.time;
    }
    
    public int compareTo(Alarm alarm)
    {
        return this.time().compareTo(alarm.time());
    }
    
    public void activate()
    {
        this.parentClock.message = "Alarm is going off";
        this.state = ACTIVATED;
    }
    
    //TODO: Set it to the current Clock time + 5 minutes
    public void snooze()
    {
        this.time.step(0, 5, 0);
        this.state = 0;
    }
}
