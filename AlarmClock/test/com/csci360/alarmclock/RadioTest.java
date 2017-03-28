/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.csci360.alarmclock;

import com.csci360.alarmclock.Radio;
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
public class RadioTest {
    
    public RadioTest() 
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
    public void testCreation() 
    {
        Radio radio = new Radio();
        assertEquals(radio.channels().length, 206);
        assertEquals(radio.selected(), 1);
        assertEquals(radio.channel(1).frequency(), 1);
        assertEquals(radio.channel(206).frequency(), 206);
    }
    
    @Test
    public void testScan()
    {
        Radio radio = new Radio();
        radio.channel(10).sound("Grey Noise");
        radio.scan(1);
        assertEquals(radio.selected(), 10);
        radio.channel(5).sound("Off-White Noise");
        radio.scan(-1);
        assertEquals(radio.selected(), 5);
    }
    
}
