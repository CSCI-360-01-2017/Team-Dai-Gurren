/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.csci360.alarmclock;

import alarmclock.*;
import alarmclock.Alarm;
import alarmclock.Time;
import alarmclock.Clock;
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
public class ClockTest {
    
    public ClockTest() 
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
    public void testAttributes()
    {
        Clock clock = new Clock(12, 30, 30, null);
        assertEquals(clock.time().hour(), 12);
        assertEquals(clock.time().minute(), 30);
        assertEquals(clock.time().second(), 30);
        clock.time(new Time(11, 25, 40));
        assertEquals(clock.time().hour(), 11);
        assertEquals(clock.time().minute(), 25);
        assertEquals(clock.time().second(), 40);
    }
    
    @Test
    public void testAlarmComparison()
    {
        Clock clock = new Clock(12, 30, 30, null);
        Alarm alarm = new Alarm(11, 30, 1);
        runAlarmComparators(clock, alarm);
        alarm.time().hour(12);
        clock.time().minute(40);
        runAlarmComparators(clock, alarm);
        clock.time().minute(30);
        clock.time().second(0);
        assertEquals(clock.compareTo(alarm), 0);
    }
    
    @Test
    public void testStep()
    {
        Clock clock = new Clock();
        assertEquals( clock.time().second(), 0);
        clock.time().step();
        assertEquals( clock.time().second(), 1);
        clock.time().second(59);
        clock.time().step();
        assertEquals( clock.time().second(), 0);
        assertEquals( clock.time().minute(), 1);
        clock.time().second(59);
        clock.time().minute(59);
        clock.time().step();
        assertEquals(clock.time().hour(), 13);
    }
    
    private void runAlarmComparators(Clock clock, Alarm alarm)
    {
        assertEquals( clock.compareTo(alarm), 1);
        Clock tempClock = new Clock(alarm.time());
        Alarm tempAlarm = new Alarm(clock.time());
        assertEquals( tempClock.compareTo(tempAlarm), -1);
    }
    
}
