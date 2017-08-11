
import Distributor.AlarmClock;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Test;
import org.junit.runner.RunWith;

import static Distributor.DayPart.*;
import static org.junit.Assert.assertEquals;

import java.text.ParseException;


@RunWith(JUnitParamsRunner.class)
public class AlarmClockTest {
    AlarmClock alarmClock;

    public static Object[] getMorningTime() {
        return new Object[][]{
                {"06:00:00"}, {"06:00:01"}, {"06:16:16"}, {"07:45:15"}, {"08:59:59"}};
    }

    public static Object[] getDayTime() {
        return new Object[][]{
                {"09:00:00"}, {"09:00:01"}, {"10:26:30"}, {"13:13:13"}, {"18:59:59"}};
    }

    public static Object[] getEveningTime() {
        return new Object[][]{
                {"19:00:00"}, {"19:00:01"}, {"20:20:56"}, {"21:10:00"}, {"22:59:59"}};
    }

    public static Object[] getNightTime() {
        return new Object[][]{
                {"23:00:00"}, {"23:00:01"}, {"00:00:00"}, {"04:20:19"}, {"05:59:59"}};
    }


    @Test
    @Parameters(method = "getMorningTime")
    public void methodShouldShowMorningTime(String time) throws ParseException {
        alarmClock = new AlarmClock(time);
        assertEquals(MORNING, alarmClock.getTime());
    }

    @Test
    @Parameters(method = "getDayTime")
    public void methodShouldShowDayTime(String time) throws ParseException {
        alarmClock = new AlarmClock(time);
        assertEquals(DAY, alarmClock.getTime());
    }

    @Test
    @Parameters(method = "getEveningTime")
    public void methodShouldShowEveningTime(String time) throws ParseException {
        alarmClock = new AlarmClock(time);
        assertEquals(EVENING, alarmClock.getTime());
    }

    @Test
    @Parameters(method = "getNightTime")
    public void methodShouldShowNightTime(String time) throws ParseException {
        alarmClock = new AlarmClock(time);
        assertEquals(NIGHT, alarmClock.getTime());
    }
}
