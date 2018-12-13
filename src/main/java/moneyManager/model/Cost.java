package moneyManager.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

/**
 * Created by Vladimir on 26.07.2018.
 */
public class Cost extends BaseEntity {
    private LocalDateTime dateTime;

    private String description;

    private int price;

    public Cost() {
    }

    public Cost(LocalDateTime dateTime, String description, int price) {
        this(null, dateTime, description, price);
    }

    public Cost(Integer id, LocalDateTime dateTime, String description, int price) {
        super(id);
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

    public LocalDate getDate() {
        return dateTime.toLocalDate();
    }

    public LocalTime getTime() {
        return dateTime.toLocalTime();
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPrice(int price) {
        this.price = price;
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
