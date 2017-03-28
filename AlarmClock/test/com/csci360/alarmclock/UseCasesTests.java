/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.csci360.alarmclock;

import com.csci360.alarmclock.AlarmClockController;
import com.csci360.alarmclock.Alarm;
import com.csci360.alarmclock.Time;
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
public class UseCasesTests {
    
    public UseCasesTests() {
    }
    
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void changingWavelength()
    {
        AlarmClockController acc = new AlarmClockController();
        assertEquals(acc.radio.selected(), 1);
        acc.radio.toggleFrequency();
        assertEquals(acc.radio.selected(), 104);
    }
    
    @Test
    public void settingAlarms()
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
    public void snoozingDayAway()
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
    public void turningOffAlarm()
    {
        AlarmClockController acc = new AlarmClockController(new Time(12, 0, 59), false);
        assertEquals(acc.player().sound(), "");
        acc.alarm1(new Alarm(12, 1, 0));
        acc.alarm1().toggle();
        acc.clock.tick();
        assertEquals(acc.player().sound(), "");
    }
    
    @Test
    public void changingTime()
    {
        AlarmClockController acc = new AlarmClockController( new Time(), false);
        acc.setTime(12, 0, 59);
        assertEquals(acc.currentTime().compareTo(new Time(12, 0, 59)), 0);
        acc.clock.tick();
        assertEquals(acc.currentTime().compareTo(new Time(12, 1, 0)), 0);
        acc.setTime(12, 59, 59);
        acc.clock.tick();
        assertEquals(acc.currentTime().compareTo(new Time(13, 0, 0)), 0);
    }
}
