package moneyManager.repository.jdbc;

import moneyManager.model.Cost;
import moneyManager.repository.CostRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public class JdbcCostRepositoryImpl implements CostRepository {
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
