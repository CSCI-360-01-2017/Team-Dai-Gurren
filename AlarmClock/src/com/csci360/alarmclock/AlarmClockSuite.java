/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.csci360.alarmclock;

import alarmclock.*;
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
    alarmclock.AlarmTest.class,
    alarmclock.ClockTest.class,
    alarmclock.UseCasesTests.class,
    alarmclock.TimeTest.class,
    alarmclock.ChannelTest.class,
    alarmclock.RadioTest.class,
    alarmclock.AlarmClockControllerTest.class
})
public class AlarmClockSuite {

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }
    
}
