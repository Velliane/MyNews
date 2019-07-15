package com.menard.mynews.utils;

import org.threeten.bp.LocalDateTime;
import org.threeten.bp.OffsetDateTime;
import org.threeten.bp.format.DateTimeFormatter;

public class DateUtils {

    private static DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ssX");
    private static DateTimeFormatter humanDateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yy");

    public static String parseDate (String date){
        LocalDateTime dateTime = LocalDateTime.parse(date, dateTimeFormatter);
        return dateTime.format(humanDateTimeFormatter);
    }

    public static String parseZonedDate (String date){
        OffsetDateTime offsetDateTime = OffsetDateTime.parse(date);
        return offsetDateTime.format(humanDateTimeFormatter);
    }
}
