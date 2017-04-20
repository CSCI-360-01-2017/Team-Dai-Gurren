/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package alarmclockgui;

import com.csci360.alarmclockgui.*;
import alarmclockgui.*;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;

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
    public MediaPlayer player;
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
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.clock = new Clock(new Time(12, 0, 50), this);
        this.radio = new Radio();
        this.radio.channel(55).fileString("salsa.wav");
        this.radio.channel(156).fileString("muzak.wav");
        this.alarm1 = new Alarm(12, 1, -1);
        this.alarm2 = new Alarm(12, 2, -1);
        this.displayTime = this.clock.time();
        this.time.setText(this.displayTime.toString());
        this.change = false;
        this.initializePlayer(this.radio.currentChannel().sound());
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
    
    public void initializePlayer(Media media)
    {
        if(this.player != null)
            this.player.pause();
        this.player = new MediaPlayer(media);
        this.player.setOnEndOfMedia(new Runnable() {
            public void run() {
              player.seek(Duration.ZERO);
            }
        });
        this.player.play();
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
                time.setText(change && this.clock.timeStep%2 == 0 ? blink() : displayTime.toString());
            }
        );
        try {
            Thread.sleep(100L);
        } catch (InterruptedException ex) {
            Logger.getLogger(AlarmClockController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private String blink()
    {
        return this.time.getText().compareTo("    :    :    ") == 0 ? this.displayTime.toString() : "    :    :    ";
    }
    
    public void alarmCheck()
    {
        if(alarm1.state() == ON)
            if(this.clock.compareTo(this.alarm1) == 0)
            {
                this.initializePlayer(this.alarm1.sound());
                this.alarm1.activate();
                
            }
        if(alarm2.state() == ON)
            if(this.clock.compareTo(this.alarm2) == 0)
            {
                this.initializePlayer(this.alarm2.sound());
                this.alarm2.activate();
            }
    }
    
    public void snooze()
    {
        if( this.alarm1.state() == ACTIVATED)
        {
            this.player.pause();
            this.alarm1.snooze();
        }
        if( this.alarm2.state() == ACTIVATED)
        {
            this.player.pause();
            this.alarm2.snooze();
        }
        if(this.radio.isOn && this.player.getMedia().getSource().compareTo(this.alarm1.sound().getSource()) == 0)
            this.initializePlayer(this.radio.currentChannel().sound());
    }
    
    public void hour()
    {
        if(this.change)
            this.displayTime.hour(this.displayTime.hour() + 1);
    }
    
    public void minute()
    {
        if(this.change)
            this.displayTime.minute(this.displayTime.minute() + 1);
    }
    
    public void second()
    {
        if(this.change)
            this.displayTime.second(this.displayTime.second() + 1);
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
        this.player.pause();
        this.initializePlayer(this.radio.currentChannel().sound());
    }
    
    public void scanDown()
    {
        this.radio.scan(-1);
        this.radioChannel.setText(this.radio.currentChannel().frequency());
        this.player.pause();
        this.initializePlayer(this.radio.currentChannel().sound());
    }
    
    public void toggleAMFM()
    {
        this.radio.toggleFrequency();
        this.radioChannel.setText(this.radio.currentChannel().frequency());
        this.player.pause();
        this.initializePlayer(this.radio.currentChannel().sound());
    }
    
    public void toggleRadio()
    {
        if(this.alarm1.state() != ACTIVATED && this.alarm2.state() != ACTIVATED)
        {
            if(this.radio.isOn)
                this.player.pause();
            else
                this.initializePlayer(this.radio.currentChannel().sound());
        }
        this.radio.toggleOn();
    }
}
