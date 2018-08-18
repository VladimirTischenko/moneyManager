package moneyManager.service;

import moneyManager.model.Cost;
import moneyManager.util.exception.NotFoundException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Collection;

/**
 * Created by Vladimir on 09.08.2018.
 */
public interface CostService {
    Cost get(int id, int userId) throws NotFoundException;

    void delete(int id, int userId) throws NotFoundException;

    default Collection<Cost> getBetweenDates(LocalDate startDate, LocalDate endDate, int userId) {
        return getBetweenDateTimes(LocalDateTime.of(startDate, LocalTime.MIN), LocalDateTime.of(endDate, LocalTime.MAX), userId);
    }

    Collection<Cost> getBetweenDateTimes(LocalDateTime startDateTime, LocalDateTime endDateTime, int userId);

    Collection<Cost> getAll(int userId);

    Cost update(Cost cost, int userId) throws NotFoundException;

    Cost save(Cost cost, int userId);
}
