package moneyManager.util;

import moneyManager.model.Cost;
import moneyManager.model.CostWithExceed;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Vladimir on 26.07.2018.
 */
public class CostUtil {
    public static void main(String[] args) {
        List<Cost> costList = Arrays.asList(
                new Cost(LocalDateTime.of(2018, Month.JULY, 21,10,0), "Продукты", 800),
                new Cost(LocalDateTime.of(2018, Month.JULY, 21,13,0), "Продукты", 100),
                new Cost(LocalDateTime.of(2018, Month.JULY, 22,11,30), "Кафе", 150),
                new Cost(LocalDateTime.of(2018, Month.JULY, 22,15,30), "Продукты", 70),
                new Cost(LocalDateTime.of(2018, Month.JULY, 22,17,0), "Аренда жилья", 15000),
                new Cost(LocalDateTime.of(2018, Month.JULY, 25,19,0), "Продукты", 450)
        );
        getFilteredWithExceeded(costList, LocalTime.of(7, 0), LocalTime.of(12,0), 2000);
//        .toLocalDate();
//        .toLocalTime();
    }

    public static List<CostWithExceed>  getFilteredWithExceeded(List<Cost> costList, LocalTime startTime, LocalTime endTime, int sumPerDay) {
        // TODO return filtered list with correctly exceeded field
        return null;
    }
}
