package moneyManager;

import moneyManager.matcher.ModelMatcher;
import moneyManager.model.Cost;

import java.time.Month;
import java.util.Arrays;
import java.util.List;

import static java.time.LocalDateTime.of;
import static moneyManager.model.BaseEntity.START_SEQ;

public class CostTestData {
    public static final ModelMatcher<Cost> MATCHER = new ModelMatcher<>();

    public static final int COST1_ID = START_SEQ + 2;
    public static final int ADMIN_COST_ID = START_SEQ + 8;

    public static final Cost COST1 = new Cost(COST1_ID, of(2018, Month.JULY, 21,10,0), "Продукты", 800);
    public static final Cost COST2 = new Cost(COST1_ID + 1, of(2018, Month.JULY, 21,13,0), "Продукты", 100);
    public static final Cost COST3 = new Cost(COST1_ID + 2, of(2018, Month.JULY, 22,11,30), "Кафе", 150);
    public static final Cost COST4 = new Cost(COST1_ID + 3, of(2018, Month.JULY, 22,15,30), "Продукты", 70);
    public static final Cost COST5 = new Cost(COST1_ID + 4, of(2018, Month.JULY, 22,17,0), "Аренда жилья", 15000);
    public static final Cost COST6 = new Cost(COST1_ID + 5, of(2018, Month.JULY, 25,19,0), "Продукты", 450);
    public static final Cost ADMIN_COST1 = new Cost(ADMIN_COST_ID, of(2018, Month.AUGUST, 11, 11, 0), "fat", 150);
    public static final Cost ADMIN_COST2 = new Cost(ADMIN_COST_ID + 1, of(2018, Month.AUGUST, 11, 11, 30), "tea tree oil", 100);

    public static final List<Cost> COSTS = Arrays.asList(COST6, COST5, COST4, COST3, COST2, COST1);

    public static Cost getCreated() {
        return new Cost(null, of(2018, Month.DECEMBER, 1, 18, 0), "New cost", 300);
    }

    public static Cost getUpdated() {
        return new Cost(COST1_ID, COST1.getDateTime(), "Обновленный завтрак", 200);
    }
}
