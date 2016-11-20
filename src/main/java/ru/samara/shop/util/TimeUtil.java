package ru.samara.shop.util;

import java.time.LocalTime;

/**
 * @author papont
 * @date 13.11.16.
 */
public class TimeUtil {
    public static boolean isBetween(LocalTime lt, LocalTime startTime, LocalTime endTime) {
        return lt.compareTo(startTime) >= 0 && lt.compareTo(endTime) <= 0;
    }
}
