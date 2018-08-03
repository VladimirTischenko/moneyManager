package moneyManager.util;

import moneyManager.model.Cost;
import moneyManager.model.CostWithExceed;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by Vladimir on 26.07.2018.
 */
public class CostsUtil {
    public static final List<Cost> COSTS = Arrays.asList(
            new Cost(LocalDateTime.of(2018, Month.JULY, 21,10,0), "Продукты", 800),
            new Cost(LocalDateTime.of(2018, Month.JULY, 21,13,0), "Продукты", 100),
            new Cost(LocalDateTime.of(2018, Month.JULY, 22,11,30), "Кафе", 150),
            new Cost(LocalDateTime.of(2018, Month.JULY, 22,15,30), "Продукты", 70),
            new Cost(LocalDateTime.of(2018, Month.JULY, 22,17,0), "Аренда жилья", 15000),
            new Cost(LocalDateTime.of(2018, Month.JULY, 25,19,0), "Продукты", 450)
    );

    public static final int DEFAULT_SUM_PER_DAY = 2000;

    public static void main(String[] args) {
        List<CostWithExceed> costsWithExceeded = getFilteredWithExceeded(COSTS, LocalTime.of(7, 0), LocalTime.of(12, 0), DEFAULT_SUM_PER_DAY);
        costsWithExceeded.forEach(System.out::println);

        System.out.println(getFilteredWithExceededByCycle(COSTS, LocalTime.of(7, 0), LocalTime.of(12, 0), DEFAULT_SUM_PER_DAY));
    }

    public static List<CostWithExceed> getWithExceeded(Collection<Cost> costs, int sumPerDay) {
        return getFilteredWithExceeded(costs, LocalTime.MIN, LocalTime.MAX, sumPerDay);
    }

    public static List<CostWithExceed> getFilteredWithExceeded(Collection<Cost> costs, LocalTime startTime, LocalTime endTime, int sumPerDay) {
        Map<LocalDate, Integer> sumByDate = costs.stream().collect(Collectors.groupingBy(Cost::getDate, Collectors.summingInt(Cost::getPrice)));

        return costs.stream()
                .filter(cost -> DateTimeUtil.isBetween(cost.getTime(), startTime, endTime))
                .map(cost -> createWithExceed(cost, sumByDate.get(cost.getDate()) > sumPerDay))
                .collect(Collectors.toList());
    }

    private static List<CostWithExceed> getFilteredWithExceededByCycle(List<Cost> costs, LocalTime startTime, LocalTime endTime, int sumPerDay) {
        Map<LocalDate, Integer> sumByDate = new HashMap<>();
        costs.forEach(cost -> sumByDate.merge(cost.getDate(), cost.getPrice(), Integer::sum));

        List<CostWithExceed> costsWithExceed = new ArrayList<>();
        costs.forEach(cost -> {
            if(DateTimeUtil.isBetween(cost.getTime(), startTime, endTime)) {
                costsWithExceed.add(createWithExceed(cost, sumByDate.get(cost.getDate()) > sumPerDay));
            }
        });

        return costsWithExceed;
    }

    private static CostWithExceed createWithExceed(Cost cost, boolean exceeded) {
        return new CostWithExceed(cost.getId(), cost.getDateTime(), cost.getDescription(), cost.getPrice(), exceeded);
    }
}
