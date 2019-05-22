package moneyManager.model;

import moneyManager.util.DateTimeUtil;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.Range;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

/**
 * Created by Vladimir on 26.07.2018.
 */
@SuppressWarnings("JpaQlInspection")
@NamedQueries({
        @NamedQuery(name = Cost.ALL_SORTED, query = "SELECT c FROM Cost c WHERE c.user.id=:userId ORDER BY c.dateTime DESC"),
        @NamedQuery(name = Cost.DELETE, query = "DELETE FROM Cost c WHERE c.id=:id AND c.user.id=:userId"),
        @NamedQuery(name = Cost.GET_BETWEEN, query = "SELECT c FROM Cost c " +
                "WHERE c.user.id=:userId AND c.dateTime BETWEEN :startDate AND :endDate ORDER BY c.dateTime DESC"),
//        @NamedQuery(name = Cost.UPDATE, query = "UPDATE Cost c SET c.dateTime = :datetime, c.price= :price," +
//                "c.description=:desc where c.id=:id and c.user.id=:userId")
})
@Entity
@Table(name = "costs", uniqueConstraints = {@UniqueConstraint(columnNames = {"user_id", "date_time"}, name = "costs_unique_user_datetime_idx")})
public class Cost extends BaseEntity {
    public static final String ALL_SORTED = "Cost.getAll";
    public static final String DELETE = "Cost.delete";
    public static final String GET_BETWEEN = "Cost.getBetween";

    @Column(name = "date_time", nullable = false)
    @NotNull
    @DateTimeFormat(pattern = DateTimeUtil.DATE_TIME_PATTERN)
    private LocalDateTime dateTime;

    @Column(name = "description", nullable = false)
    @NotBlank
    private String description;

    @Column(name = "price", nullable = false)
    @Range(min = 10, max = 5000)
    @NotNull
    private Integer price;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private User user;

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

    public void setPrice(Integer price) {
        this.price = price;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Cost{" +
                "id=" + getId() +
                ", dateTime=" + dateTime +
                ", description='" + description + '\'' +
                ", price=" + price +
                '}';
    }
}
