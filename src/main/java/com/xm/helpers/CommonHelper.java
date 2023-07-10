package com.xm.helpers;

import java.security.SecureRandom;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public final class CommonHelper {
    private static final SecureRandom RANDOM = new SecureRandom();

    public static String getCurrentDate(String format) {
        LocalDateTime currentDate = LocalDateTime.now();
        return currentDate.format(DateTimeFormatter.ofPattern(format));
    }

    public static LocalDateTime getDateFromCurrentDateInRange(int daysRange) {
        LocalDateTime currentDate = LocalDateTime.now();
        return addDaysInRangeToDate(currentDate, daysRange);
    }

    public static String getDateFromCurrentDateInRange(int daysRange, String format) {
        LocalDateTime randomDate = getDateFromCurrentDateInRange(daysRange);
        return getDateInFormat(randomDate, format);
    }

    public static LocalDateTime addDaysInRangeToDate(LocalDateTime date, int daysRange) {
        int randomDay = RANDOM.nextInt(Math.abs(daysRange)) + 1;
        return (daysRange > 0) ? date.plusDays(randomDay) : date.minusDays(randomDay);
    }

    public static String getDateInFormat(LocalDateTime date, String format) {
        return date.format(DateTimeFormatter.ofPattern(format));
    }

    public static String getNextWeekDate(String format) {
        LocalDate currentDate = LocalDate.now();
        LocalDate nextWeek = currentDate.plusWeeks(1);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
        return nextWeek.format(formatter);
    }

    public static String getNextMonthDate(String format) {
        LocalDate currentDate = LocalDate.now();
        LocalDate nextWeek = currentDate.plusMonths(1);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
        return nextWeek.format(formatter);
    }

    public static String formatDate(String dateString) {
        DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("E MMM dd yyyy", Locale.ENGLISH);
        LocalDate date = LocalDate.parse(dateString, inputFormatter);
        DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("yyyy MMM dd", Locale.ENGLISH);
        String formattedDate = date.format(outputFormatter);
        return formattedDate;
    }

    public static String getNumberFromTheUrl(String url) {
        String[] parts = url.split("/");
        return parts[parts.length - 1];
    }

    private CommonHelper() {
        throw new UnsupportedOperationException("Not allowed to crate an instance of CommonHelper class");
    }

}

