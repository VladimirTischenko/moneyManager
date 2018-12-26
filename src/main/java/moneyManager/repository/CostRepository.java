package moneyManager.repository;

import moneyManager.model.Cost;

import java.time.LocalDateTime;
import java.util.Collection;

/**
 * Created by Vladimir on 03.08.2018.
 */
public interface CostRepository {
    // null if updated cost do not belong to userId
    Cost save(Cost cost, int userId);

    // false if cost do not belong to userId
    boolean delete(int id, int userId);

    // null if cost do not belong to userId
    Cost get(int id, int userId);

    // ORDERED dateTime
    Collection<Cost> getAll(int userId);

    // ORDERED dateTime
    Collection<Cost> getBetween(LocalDateTime startDate, LocalDateTime endDate, int userId);

    default Cost getWithUser(int id, int userId) {
        throw new UnsupportedOperationException();
    }
}
