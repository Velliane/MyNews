package com.menard.mynews;

import com.menard.mynews.utils.DateUtils;

import org.junit.Test;

import java.util.Date;

import static org.junit.Assert.*;

public class DateUtilsTest {

    @Test
    public void parseSearchedDate() {
        String date = "2019-06-21T15:25:00+0000";

        String changedDate = DateUtils.parseSearchedDate(date);

        assertEquals("21/06/19",changedDate);
    }

    @Test
    public void parseZonedDate(){
        String date = "2019-05-15T17:12:00-08:00";

        String changedDate = DateUtils.parseZonedDate(date);

        assertEquals("15/05/19", changedDate);
    }

    @Test
    public void parseMostPopularDate(){
        String date = "2019-06-21";

        String changedDate = DateUtils.parseMostPopularDate(date);

        assertEquals("21/06/19", changedDate);
    }

    @Test
    public void parseRequestDate(){
        String date = "2/7/2019";

        String changedDate = DateUtils.parseRequestDate(date);

        assertEquals("20190702", changedDate);
    }
}