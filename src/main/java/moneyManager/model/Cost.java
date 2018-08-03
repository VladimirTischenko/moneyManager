package moneyManager.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

/**
 * Created by Vladimir on 26.07.2018.
 */
public class Cost {
    private Integer id;

    private final LocalDateTime dateTime;

    private final String description;

    private final int price;

    public Cost(LocalDateTime dateTime, String description, int price) {
        this(null, dateTime, description, price);
    }

    public Cost(Integer id, LocalDateTime dateTime, String description, int price) {
        this.id = id;
        this.dateTime = dateTime;
        this.description = description;
        this.price = price;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public LocalDate getDate() {
        return dateTime.toLocalDate();
    }

    public LocalTime getTime() {
        return dateTime.toLocalTime();
    }

    public boolean isNew() {
        return id == null;
    }

    @Override
    public String toString() {
        return "Cost{" +
                "id=" + id +
                ", dateTime=" + dateTime +
                ", description='" + description + '\'' +
                ", price=" + price +
                '}';
    }
}
