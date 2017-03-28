/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.csci360.alarmclock;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 *
 * @author tim
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({
    com.csci360.alarmclock.AlarmTest.class,
    com.csci360.alarmclock.ClockTest.class,
    com.csci360.alarmclock.UseCasesTests.class,
    com.csci360.alarmclock.TimeTest.class,
    com.csci360.alarmclock.ChannelTest.class,
    com.csci360.alarmclock.RadioTest.class,
    com.csci360.alarmclock.AlarmClockControllerTest.class
})
public class AlarmClockSuite {

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }
    
}
