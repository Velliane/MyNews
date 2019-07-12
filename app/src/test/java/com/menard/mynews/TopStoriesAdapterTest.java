package com.menard.mynews;

import org.junit.Test;
import org.threeten.bp.OffsetDateTime;
import org.threeten.bp.format.DateTimeFormatter;

import static org.junit.Assert.*;

/**
 * TopStoriesAdapter local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class TopStoriesAdapterTest {

    /**
     * Change the format of the date
     */
    @Test
    public void changeDateFormat() {
        String date = "2019-06-21T15:25:00-08:00";
        OffsetDateTime offsetDateTime = OffsetDateTime.parse(date);
        String changedDate = offsetDateTime.format(DateTimeFormatter.ofPattern("dd/MM/yy"));
        assertEquals("21/06/19",changedDate);
    }
}