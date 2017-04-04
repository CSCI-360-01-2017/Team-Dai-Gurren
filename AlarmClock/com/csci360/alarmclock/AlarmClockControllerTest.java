/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.csci360.alarmclock;

import alarmclock.*;
import alarmclock.Alarm;
import alarmclock.Time;
import alarmclock.AlarmClockController;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author tim
 */
public class AlarmClockControllerTest 
{
    
    public AlarmClockControllerTest() 
    {
    }
    
    @Before
    public void setUp() 
    {
    }
    
    @After
    public void tearDown() 
    {
    }

    @Test
    public void testClockAttribute() throws InterruptedException 
    {
        AlarmClockController acc = new AlarmClockController( new Time(), false);
        assertEquals(acc.currentTime().compareTo(new Time()), 0);
        acc.clock.tick();
        assertEquals(acc.currentTime().compareTo(new Time(12, 0, 1)), 0);
        acc.setTime(12, 0, 59);
        acc.clock.tick();
        assertEquals(acc.currentTime().compareTo(new Time(12, 1, 0)), 0);
        acc.setTime(12, 59, 59);
        acc.clock.tick();
        assertEquals(acc.currentTime().compareTo(new Time(13, 0, 0)), 0);
    }
    
    @Test
    public void testAlarmAttributes() throws InterruptedException
    {
        AlarmClockController acc = new AlarmClockController(new Time(12, 0, 59), false);
        assertEquals(acc.player().sound(), "");
        acc.alarm1(new Alarm(12, 1, 0));
        acc.alarm2(new Alarm(12, 2, 0));
        acc.clock.tick();
        assertEquals(acc.player().sound(), "Alarm 1 is going off");
        acc.player().sound("");
        acc.setTime(12, 1, 59);
        acc.clock.tick();
        assertEquals(acc.player().sound(), "Alarm 2 is going off");
    }
    
    @Test
    public void testSnoozeFunctionality() throws InterruptedException
    {
        AlarmClockController acc = new AlarmClockController(new Time(12, 0, 59), false);
        assertEquals(acc.player().sound(), "");
        acc.alarm1(new Alarm(12, 1, 0));
        acc.clock.tick();
        assertEquals(acc.player().sound(), "Alarm 1 is going off");
        acc.snooze();
        assertEquals(acc.player().sound(), "White Noise");
        acc.setTime(12, 5, 59);
        acc.clock.tick();
        assertEquals(acc.player().sound(), "Alarm 1 is going off");
    }
    
    @Test
    public void testRadioAttribute()
    {
        AlarmClockController acc = new AlarmClockController();
        assertEquals(acc.radio.selected(), 1);
        acc.changeChannel(100);
        assertEquals(acc.radio.selected(), 100);
        acc.radio.channel(110).sound("Grey Noise");
        acc.scan(1);
        assertEquals(acc.radio.selected(), 110);
        acc.radio.channel(90).sound("Off-White Noise");
        acc.scan(-1);
        assertEquals(acc.radio.selected(), 90);
    }
    
    @Test
    public void timerTest() throws InterruptedException
    {
        AlarmClockController acc = new AlarmClockController();
        acc.startClock();
        Thread.sleep(2100L);
        acc.stopClock();
        assertEquals(acc.currentTime().second(), 2);
        Thread.sleep(1100L);
        assertEquals(acc.currentTime().second(), 2);
    }
    
    @Test
    public void soundTest()
    {
        AlarmClockController acc = new AlarmClockController();
        acc.alarm1(new Alarm(12, 1, 0));
        acc.setTime(12, 0, 59);
        acc.clock.tick();
        assertEquals(acc.player().sound(), "Alarm 1 is going off");
    }
    
    @Test
    public void userSnoozesAlarmTest() throws InterruptedException
    {
        AlarmClockController acc = new AlarmClockController();
        acc.alarm1(new Alarm(12, 1, 0));
        acc.setTime(12, 0, 59);
        acc.startClock();
        Thread.sleep(1100);
        assertEquals(acc.player().sound(), "Alarm 1 is going off");
        acc.snooze();
        acc.setTime(12, 5, 59);
        assertEquals(acc.player().sound(), "White Noise");
        acc.startClock();
        Thread.sleep(1100);
        assertEquals(acc.player().sound(), "Alarm 1 is going off");
    }
    
}
