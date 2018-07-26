package moneyManager.util;

import java.time.LocalTime;

/**
 * Created by Vladimir on 26.07.2018.
 */
public class TimeUtil {
    public static boolean isBetween(LocalTime lt, LocalTime startTime, LocalTime endTime) {
        return lt.compareTo(startTime) >= 0 && lt.compareTo(endTime) <= 0;
    }
}
