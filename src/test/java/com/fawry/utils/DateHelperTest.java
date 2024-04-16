package com.fawry.utils;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class DateHelperTest {

    @Test
    public void shouldReturnSetOfSizeThreeContainingProvidedDays() {
        String tollFreeDays = "01-01, 03-28, 03-29";
        assertEquals(3, DateHelper.getTollFreeDays(tollFreeDays).size());
    }

    @Test
    public void shouldReturnSetOfSizeTwoContainingSaturdayAndSunday() {
        String weekends = "SATURDAY, SUNDAY";
        assertEquals(2, DateHelper.getWeekends(weekends).size());
    }

    @Test
    public void shouldReturnSetOfSizeTwoContainingJulyAndJune() {
        String tollFreeMonths = "JULY, JUNE";
        assertEquals(2, DateHelper.getTollFreeMonths(tollFreeMonths).size());
    }

    @Test
    public void shouldReturnEmptySetForEmptyDays() {
        String tollFreeDays = "";
        assertEquals(0, DateHelper.getTollFreeDays(tollFreeDays).size());
    }

    @Test
    public void shouldReturnEmptySetForNullDays() {
        String tollFreeDays = null;
        assertEquals(0, DateHelper.getTollFreeDays(tollFreeDays).size());
    }

    @Test
    public void shouldReturnEmptySetForEmptyMonths() {
        String tollFreeMonths = "";
        assertEquals(0, DateHelper.getTollFreeMonths(tollFreeMonths).size());
    }

    @Test
    public void shouldReturnEmptySetForNullMonths() {
        String tollFreeMonths = null;
        assertEquals(0, DateHelper.getTollFreeMonths(tollFreeMonths).size());
    }

    @Test
    public void shouldReturnEmptySetForEmptyWeekends() {
        String weekends = "";
        assertEquals(0, DateHelper.getWeekends(weekends).size());
    }

    @Test
    public void shouldReturnEmptySetForNullWeekends() {
        String weekends = null;
        assertEquals(0, DateHelper.getWeekends(weekends).size());
    }

}
