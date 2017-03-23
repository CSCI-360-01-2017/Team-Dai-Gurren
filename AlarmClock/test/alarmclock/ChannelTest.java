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
        Channel frequency = new Channel("White Noise", 10000);
        assertEquals(frequency.sound(), "White Noise");
        assertEquals(frequency.frequency(), 10000);
        frequency.sound("Grey Noise");
        frequency.frequency(10001);
        assertEquals(frequency.sound(), "Grey Noise");
        assertEquals(frequency.frequency(), 10001);
    }
    
}
