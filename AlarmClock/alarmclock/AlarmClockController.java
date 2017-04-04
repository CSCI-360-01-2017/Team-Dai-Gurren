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
public class AlarmClockController
{
    public Clock clock;
    public Radio radio;
    public Thread clockThread;
    private Alarm alarm1;
    private Alarm alarm2;
    public SoundPlayer player;
    
    private static final int OFF = -1;
    private static final int ON = 0;
    private static final int ACTIVATED = 1;
    
    public AlarmClockController()
    {
        this.clock = new Clock(this);
        this.radio = new Radio();
        this.alarm1 = new Alarm();
        this.alarm2 = new Alarm();
        this.player = new SoundPlayer();
    }
    
    public AlarmClockController( Time clockTime, boolean run )
    {
        this.clock = new Clock( this );
        this.clock.time( clockTime );
        this.radio = new Radio();
        this.alarm1 = new Alarm();
        this.alarm2 = new Alarm();
        this.player = new SoundPlayer();
        if( run )
            this.startClock();
        
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
    
    public SoundPlayer player()
    {
        return this.player;
    }
    
    public void player(SoundPlayer player)
    {
        this.player = player;
    }
    
    public void changeChannel(int channel)
    {
        this.radio.selected(channel);
    }
    
    public void scan(int direction)
    {
        this.radio.scan(direction);
    }
    
    public Time currentTime()
    {
        return this.clock.time();
    }
    
    public void startClock()
    {
        this.clockThread = new Thread(this.clock);
        this.clockThread.start();
    }
    
    public void stopClock()
    {
        this.clock.turnOff();
    }
    
    public void setTime(int hour, int minute, int second)
    {
        this.clock = new Clock(hour, minute, second, this);
    }
    
    public void alarmCheck()
    {
        if(alarm1.state() == ON)
            if(this.clock.compareTo(this.alarm1) == 0)
            {
                this.player.sound("Alarm 1 is going off");
                this.alarm1.activate();
            }
        if(alarm2.state() == ON)
            if(this.clock.compareTo(this.alarm2) == 0)
            {
                this.player.sound("Alarm 2 is going off");
                this.alarm1.activate();
            }
    }
    
    public void snooze()
    {
        this.player.sound(this.radio.currentChannel().sound());
        if( this.alarm1.state() == ACTIVATED)
            this.alarm1.snooze();
        if( this.alarm2.state() == ACTIVATED)
            this.alarm2.snooze();
    }
   
}
