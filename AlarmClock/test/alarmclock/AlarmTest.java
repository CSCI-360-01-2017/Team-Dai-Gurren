/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package alarmclock;

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
public class AlarmTest {
    
    public AlarmTest() 
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
        Alarm alarm = new Alarm(12, 30, 0, null);
        assertEquals(alarm.time().hour(), 12);
        assertEquals(alarm.time().minute(), 30);
        assertEquals(alarm.time().second(), 0);
        assertEquals(alarm.state(), 0);
        alarm.time(new Time(11, 25, 30));
        alarm.toggle();
        assertEquals(alarm.time().hour(), 11);
        assertEquals(alarm.time().minute(), 25);
        assertEquals(alarm.time().second(), 0);
        assertEquals(alarm.state(), -1);
        alarm.toggle();
        assertEquals(alarm.state(), 0);
    }
    
    @Test
    public void testComparison()
    {
        Alarm alarm1 = new Alarm(12, 30, 0, null);
        Alarm alarm2 = new Alarm(11, 30, 0, null);
        assertComparators(alarm1, alarm2);
        alarm2.time().hour(12);
        alarm1.time().minute(40);
        assertComparators(alarm1, alarm2);
        alarm1.time().minute(30);
        assertEquals(alarm1.compareTo(alarm2), 0);
    }
    
    @Test
    public void testActivation() throws InterruptedException
    {
        Clock clock = new Clock(new Time(12, 0, 59));
        clock.alarm1(new Alarm(12, 1, 0, clock));
        Thread thread = new Thread(clock);
        thread.start();
        assertEquals(clock.message(), "");
        Thread.sleep(100);
        assertEquals(clock.message(), "Alarm is going off");
        clock.turnOff();
        clock.clearMessage();
        clock.alarm2(new Alarm(13, 0, 0, clock));
        clock.time(new Time(12, 59, 59));
        thread = new Thread(clock);
        thread.start();
        assertEquals(clock.message(), "");
        Thread.sleep(100);
        assertEquals(clock.message(), "Alarm is going off");
        clock.turnOff();
    }
    
    @Test
    public void testSnooze()
    {
        Alarm alarm = new Alarm(12, 0, 1, null);
        alarm.snooze();
        assertEquals(alarm.time().minute(), 5);
        assertEquals(alarm.time().hour(), 12);
        assertEquals(alarm.state(), 0);
        alarm.time(new Time(11, 59, 1));
        alarm.snooze();
        assertEquals(alarm.time().minute(), 4);
        assertEquals(alarm.time().hour(), 12);
    }
    
    private void assertComparators(Alarm alarm1, Alarm alarm2)
    {
        assertEquals( alarm1.compareTo(alarm2), 1);
        assertEquals( alarm2.compareTo(alarm1), -1);
    }
}
