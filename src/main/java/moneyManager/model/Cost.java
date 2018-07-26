package moneyManager.model;

import java.time.LocalDateTime;

/**
 * Created by Vladimir on 26.07.2018.
 */
public class Cost {
    private final LocalDateTime dateTime;

    private final String description;

    private final int price;

    public Cost(LocalDateTime dateTime, String description, int price) {
        this.dateTime = dateTime;
        this.description = description;
        this.price = price;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public String getDescription() {
        return description;
    }

    public int getPrice() {
        return price;
    }
}
