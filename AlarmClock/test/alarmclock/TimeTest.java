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
public class TimeTest {
    
    public TimeTest() 
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
    public void testStep() {
        Time time = new Time(12, 0, 0);
        time.step();
        assertEquals(time.second(), 1);
        assertEquals(time.minute(), 0);
        assertEquals(time.hour(), 12);
        time.second(59);
        time.step(1, 2, 5);
        assertEquals(time.second(), 4);
        assertEquals(time.minute(), 3);
        assertEquals(time.hour(), 13);
    }

    @Test
    public void testCompareTo() 
    {
        Time time1 = new Time(12, 0, 1);
        Time time2 = new Time(12, 0, 0);
        assertEquals(time1.compareTo(time2), 1);
        assertEquals(time2.compareTo(time1), -1);
        time1 = new Time(12, 1, 0);
        assertEquals(time1.compareTo(time2), 1);
        assertEquals(time2.compareTo(time1), -1);
        time1 = new Time(13, 0, 0);
        assertEquals(time1.compareTo(time2), 1);
        assertEquals(time2.compareTo(time1), -1);
    }

    
}
