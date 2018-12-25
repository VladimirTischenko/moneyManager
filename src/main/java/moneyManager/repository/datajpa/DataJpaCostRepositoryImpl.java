package moneyManager.repository.datajpa;

import moneyManager.model.Cost;
import moneyManager.repository.CostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public class DataJpaCostRepositoryImpl implements CostRepository {
    @Autowired
    private CrudCostRepository crudRepository;

    @Override
    public Cost save(Cost cost, int userId) {
        return null;
    }

    @Override
    public boolean delete(int id, int userId) {
        return false;
    }

    @Override
    public Cost get(int id, int userId) {
        return null;
    }

    @Override
    public List<Cost> getAll(int userId) {
        return null;
    }

    @Override
    public List<Cost> getBetween(LocalDateTime startDate, LocalDateTime endDate, int userId) {
        return null;
    }
}
