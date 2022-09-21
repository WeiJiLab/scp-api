package com.thoughtworks.ssr.common.utils;

import lombok.extern.slf4j.Slf4j;

import java.text.SimpleDateFormat;
import java.time.Clock;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

@Slf4j
public class TimeUtils {

    public static final String DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";
    public static final String DATE_FORMAT = "yyyy-MM-dd";

    public static final SimpleDateFormat FORMAT_DATE_TIME = new SimpleDateFormat(DATE_TIME_FORMAT);

    public static final SimpleDateFormat FORMAT_DATE_SIMPLE = new SimpleDateFormat(DATE_FORMAT);

    public static final DateTimeFormatter DATE_TIME_FORMATTER_COLON_SEPARATE_TIME
            = DateTimeFormatter.ofPattern(DATE_TIME_FORMAT);

    public static final DateTimeFormatter DATE_TIME_FORMATTER_SIMPLE =
            DateTimeFormatter.ofPattern(DATE_FORMAT);

    public static final ZoneId defaultZoneId = ZoneId.of("+8");
    public static final ZoneOffset defaultZoneOffset = ZoneOffset.of("+8");

    public static final Clock DEFAULT_CLOCK = Clock.system(defaultZoneId);

    public static LocalDate getLocalDateNow() {
        return LocalDate.now(DEFAULT_CLOCK);
    }

    public static LocalDateTime getLocalDateTimeNow() {
        return LocalDateTime.now(DEFAULT_CLOCK);
    }

    public static OffsetDateTime getOffsetDateTimeNow() {
        return OffsetDateTime.now(DEFAULT_CLOCK);
    }

    public static OffsetDateTime getOffsetDateTime(ZonedDateTime dateTime) {
        return OffsetDateTime.ofInstant(dateTime.toInstant(), defaultZoneId);
    }

    public static int getYear() {
        return getLocalDateNow().getYear();
    }

    public static OffsetDateTime getOffsetDateTime(LocalDateTime workingTime) {
        return OffsetDateTime.of(workingTime, defaultZoneOffset);
    }

    public static String getSimpleDate(OffsetDateTime offsetDateTime) {
        return offsetDateTime == null ? "" : DATE_TIME_FORMATTER_COLON_SEPARATE_TIME.format(offsetDateTime);
    }
}


