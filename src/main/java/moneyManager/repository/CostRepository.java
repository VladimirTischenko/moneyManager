package moneyManager.repository;

import moneyManager.model.Cost;

import java.util.Collection;

/**
 * Created by Vladimir on 03.08.2018.
 */
public interface CostRepository {
    Cost save(Cost cost);

    void delete(int id);

    Cost get(int id);

    Collection<Cost> getAll();
}
