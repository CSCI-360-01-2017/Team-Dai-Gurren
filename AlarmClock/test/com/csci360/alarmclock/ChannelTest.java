/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.csci360.alarmclock;

import com.csci360.alarmclock.Channel;
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
public class ChannelTest {
    
    public ChannelTest() 
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
        Channel channel = new Channel("White Noise", 10000);
        assertEquals(channel.sound(), "White Noise");
        assertEquals(channel.frequency(), 10000);
        channel.sound("Grey Noise");
        channel.frequency(10001);
        assertEquals(channel.sound(), "Grey Noise");
        assertEquals(channel.frequency(), 10001);
    }
    
}
