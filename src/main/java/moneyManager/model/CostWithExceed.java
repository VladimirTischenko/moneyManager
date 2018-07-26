package moneyManager.model;

import java.time.LocalDateTime;

/**
 * Created by Vladimir on 26.07.2018.
 */
public class CostWithExceed {
    private final LocalDateTime dateTime;

    private final String description;

    private final int price;

    private final boolean exceed;

    public CostWithExceed(LocalDateTime dateTime, String description, int price, boolean exceed) {
        this.dateTime = dateTime;
        this.description = description;
        this.price = price;
        this.exceed = exceed;
    }

    @Override
    public String toString() {
        return "CostWithExceed{" +
                "dateTime=" + dateTime +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", exceed=" + exceed +
                '}';
    }
}
