package moneyManager.to;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDateTime;

/**
 * Created by Vladimir on 26.07.2018.
 */
public class CostWithExceed extends BaseTo{
    private final LocalDateTime dateTime;

    private final String description;

    private final int price;

    private final boolean exceed;

    public CostWithExceed(@JsonProperty("id") Integer id,
                          @JsonProperty("dateTime") LocalDateTime dateTime,
                          @JsonProperty("description") String description,
                          @JsonProperty("price") int price,
                          @JsonProperty("exceed") boolean exceed) {
        super(id);
        this.dateTime = dateTime;
        this.description = description;
        this.price = price;
        this.exceed = exceed;
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

    public boolean isExceed() {
        return exceed;
    }

    @Override
    public String toString() {
        return "CostWithExceed{" +
                "id=" + id +
                ", dateTime=" + dateTime +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", exceed=" + exceed +
                '}';
    }
}
