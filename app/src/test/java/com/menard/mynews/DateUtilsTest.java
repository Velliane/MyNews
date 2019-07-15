package com.menard.mynews;

import com.menard.mynews.utils.DateUtils;

import org.junit.Test;
import org.threeten.bp.OffsetDateTime;
import org.threeten.bp.format.DateTimeFormatter;

import static org.junit.Assert.*;

public class DateUtilsTest {

    @Test
    public void parseDate() {
        String date = "2019-06-21T15:25:00-08:00";

        String changedDate = DateUtils.parseDate(date);

        assertEquals("21/06/19",changedDate);
    }
}