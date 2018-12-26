package moneyManager.repository.datajpa;

import moneyManager.model.Cost;
import moneyManager.repository.CostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public class DataJpaCostRepositoryImpl implements CostRepository {
    @Autowired
    private CrudCostRepository crudCostRepository;

    @Autowired
    private CrudUserRepository crudUserRepository;

    @Override
    @Transactional
    public Cost save(Cost cost, int userId) {
        if (!cost.isNew() && get(cost.getId(), userId) == null) {
            return null;
        }
        cost.setUser(crudUserRepository.getOne(userId));
        return crudCostRepository.save(cost);
    }

    @Override
    public boolean delete(int id, int userId) {
        return crudCostRepository.delete(id, userId) != 0;
    }

    @Override
    public Cost get(int id, int userId) {
        Cost meal = crudCostRepository.findOne(id);
        return meal != null && meal.getUser().getId() == userId ? meal : null;
    }

    @Override
    public List<Cost> getAll(int userId) {
        return crudCostRepository.getAll(userId);
    }

    @Override
    public List<Cost> getBetween(LocalDateTime startDate, LocalDateTime endDate, int userId) {
        return crudCostRepository.getBetween(startDate, endDate, userId);
    }
}
