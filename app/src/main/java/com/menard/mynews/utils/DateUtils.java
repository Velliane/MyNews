package com.menard.mynews.utils;

import androidx.annotation.Nullable;

import org.threeten.bp.LocalDate;
import org.threeten.bp.LocalDateTime;
import org.threeten.bp.OffsetDateTime;
import org.threeten.bp.format.DateTimeFormatter;

public class DateUtils {

    private static DateTimeFormatter searchedDateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ssX");
    private static DateTimeFormatter mostpopularDateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private static DateTimeFormatter requestDateTimeFormatter = DateTimeFormatter.ofPattern("yyyyMMdd");
    private static DateTimeFormatter humanDateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yy");
    private static DateTimeFormatter humanDateTimeFormatterTwo = DateTimeFormatter.ofPattern("d/M/y");

    public static String parseSearchedDate(String date){
        LocalDateTime dateTime = LocalDateTime.parse(date, searchedDateTimeFormatter);
        return dateTime.format(humanDateTimeFormatter);
    }

    public static String parseZonedDate (String date){
        OffsetDateTime offsetDateTime = OffsetDateTime.parse(date);
        return offsetDateTime.format(humanDateTimeFormatter);
    }

    public static String parseMostPopularDate (String date){
        LocalDate dateTime = LocalDate.parse(date, mostpopularDateTimeFormatter);
        return dateTime.format(humanDateTimeFormatter);
    }

    @Nullable
    public static String parseRequestDate(@Nullable String date){
        if(date == null || date.isEmpty()) {
            return null;
        }
        LocalDate localdate = LocalDate.parse(date, humanDateTimeFormatterTwo);
        return localdate.format(requestDateTimeFormatter);
    }
}
