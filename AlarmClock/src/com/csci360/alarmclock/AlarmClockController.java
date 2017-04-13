/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package alarmclockgui;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

/**
 *
 * @author tim
 */
public class AlarmClockController implements Initializable {
    
    public Clock clock;
    public Radio radio;
    public Thread clockThread;
    private Alarm alarm1;
    private Alarm alarm2;
    public SoundPlayer player;
    private Time displayTime;
    private boolean change;
    private Time prevTime;
    
    private static final int OFF = -1;
    private static final int ON = 0;
    private static final int ACTIVATED = 1;
    
    @FXML
    private Label time;
    @FXML
    private Label radioChannel;
    @FXML
    private Label soundPlayer;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.clock = new Clock(new Time(12, 0, 55), this);
        this.radio = new Radio();
        this.alarm1 = new Alarm();
        this.alarm2 = new Alarm();
        this.player = new SoundPlayer();
        this.displayTime = this.clock.time();
        this.change = false;
        this.radio.channel(4).sound("Muzak");
        this.startClock();
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
    
    public void change()
    {
        if(change)
        {
            this.prevTime.copy(this.displayTime);
            this.displayTime = prevTime;
            this.change = false;
        }
        else
        {
            this.change = true;
            this.prevTime = this.displayTime;
            this.displayTime = new Time(this.displayTime);
        }
    }
    
    public void step()
    {
        Platform.runLater(
            () -> {
                alarmCheck();
                time.setText(change ? blink() : displayTime.toString());
            }
        );
        try {
            Thread.sleep(500L);
        } catch (InterruptedException ex) {
            Logger.getLogger(AlarmClockController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private String blink()
    {
        return this.time.getText().compareTo("   :   :   ") == 0 ? this.displayTime.toString() : "   :   :   ";
    }
    
    public void alarmCheck()
    {
        if(alarm1.state() == ON)
            if(this.clock.compareTo(this.alarm1) == 0)
            {
                this.soundPlayer.setText("Alarm 1 is going off");
                this.alarm1.activate();
            }
        if(alarm2.state() == ON)
            if(this.clock.compareTo(this.alarm2) == 0)
            {
                this.soundPlayer.setText("Alarm 2 is going off");
                this.alarm1.activate();
            }
    }
    
    public void snooze()
    {
        if( this.alarm1.state() == ACTIVATED)
        {
            soundPlayer.setText(this.radio.currentChannel().sound());
            this.alarm1.snooze();
        }
        if( this.alarm2.state() == ACTIVATED)
        {
            soundPlayer.setText(this.radio.currentChannel().sound());
            this.alarm2.snooze();
        }
    }
    
    public void hour()
    {
        if(this.change)
            this.displayTime.step(1, 0, 0);
    }
    
    public void minute()
    {
        if(this.change)
            this.displayTime.step(0, 1, 0);
    }
    
    public void second()
    {
        if(this.change)
            this.displayTime.step();
    }
    
    public void displayAlarm(Alarm alarm)
    {
        if(change)
        {
            this.displayTime = new Time(alarm.time());
            this.prevTime = alarm.time();
        }
        else
            this.displayTime = alarm.time();
    }
    
    public void displayAlarm1()
    {
        this.displayAlarm(this.alarm1);
    }
    
    public void displayAlarm2()
    {
        this.displayAlarm(this.alarm2);
    }
    
    public void displayClock()
    {
        this.displayTime = this.clock.time();
    }
    
    public void toggleAlarm1()
    {
        this.alarm1.toggle();
    }
    
    public void toggleAlarm2()
    {
        this.alarm2.toggle();
    }
    
    public void scanUp()
    {
        this.radio.scan(1);
        this.radioChannel.setText(this.radio.currentChannel().frequency());
    }
    
    public void scanDown()
    {
        this.radio.scan(-1);
        this.radioChannel.setText(this.radio.currentChannel().frequency());
    }
    
    public void toggleAMFM()
    {
        this.radio.toggleFrequency();
        this.radioChannel.setText(this.radio.currentChannel().frequency());
    }
}
