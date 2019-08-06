package com.menard.mynews.utils;

import androidx.annotation.Nullable;

import org.threeten.bp.LocalDate;
import org.threeten.bp.LocalDateTime;
import org.threeten.bp.OffsetDateTime;
import org.threeten.bp.format.DateTimeFormatter;

public class DateUtils {

    private final static DateTimeFormatter searchedDateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ssX");
    private final static DateTimeFormatter mostpopularDateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private final static DateTimeFormatter requestDateTimeFormatter = DateTimeFormatter.ofPattern("yyyyMMdd");
    private final static DateTimeFormatter humanDateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yy");
    private final static DateTimeFormatter humanDateTimeFormatterTwo = DateTimeFormatter.ofPattern("d/M/y");

    /**
     * Change the format of the date from SearchedArticles
     * @param date the date to parse
     * @return the date parsed
     */
    public static String parseSearchedDate(String date){
        LocalDateTime dateTime = LocalDateTime.parse(date, searchedDateTimeFormatter);
        return dateTime.format(humanDateTimeFormatter);
    }

    /**
     * Change the format of the date from TopStories
     * @param date the date to parse
     * @return the date parsed
     */
    public static String parseZonedDate (String date){
        OffsetDateTime offsetDateTime = OffsetDateTime.parse(date);
        return offsetDateTime.format(humanDateTimeFormatter);
    }

    /**
     * Change the format of the date from MostPopular
     * @param date the date to parse
     * @return the date parsed
     */
    public static String parseMostPopularDate (String date){
        LocalDate dateTime = LocalDate.parse(date, mostpopularDateTimeFormatter);
        return dateTime.format(humanDateTimeFormatter);
    }

    /**
     * Change the format of the date from the DatePickerDialog
     * @param date the date to parse
     * @return the date parsed
     */
    @Nullable
    public static String parseRequestDate(@Nullable String date){
        if(date == null || date.isEmpty()) {
            return null;
        }
        LocalDate localdate = LocalDate.parse(date, humanDateTimeFormatterTwo);
        return localdate.format(requestDateTimeFormatter);
    }
}
