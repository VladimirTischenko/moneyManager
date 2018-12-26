package moneyManager.service;

import moneyManager.model.Cost;
import moneyManager.repository.CostRepository;
import moneyManager.util.ValidationUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.time.LocalDateTime;
import java.util.Collection;

import static moneyManager.util.ValidationUtil.checkNotFoundWithId;

/**
 * Created by Vladimir on 09.08.2018.
 */
@Service
public class CostServiceImpl implements CostService {
    @Autowired
    private CostRepository repository;

    @Override
    public Cost get(int id, int userId) {
        return checkNotFoundWithId(repository.get(id, userId), id);
    }

    @Override
    public void delete(int id, int userId) {
        checkNotFoundWithId(repository.delete(id, userId), id);
    }

    @Override
    public Collection<Cost> getBetweenDateTimes(LocalDateTime startDateTime, LocalDateTime endDateTime, int userId) {
        Assert.notNull(startDateTime, "startDateTime must not be null");
        Assert.notNull(endDateTime, "endDateTime  must not be null");
        return repository.getBetween(startDateTime, endDateTime, userId);
    }

    @Override
    public Collection<Cost> getAll(int userId) {
        return repository.getAll(userId);
    }

    @Override
    public Cost update(Cost cost, int userId) {
        Assert.notNull(cost, "cost must not be null");
        return checkNotFoundWithId(repository.save(cost, userId), cost.getId());
    }

    @Override
    public Cost save(Cost cost, int userId) {
        Assert.notNull(cost, "cost must not be null");
        return repository.save(cost, userId);
    }

    @Override
    public Cost getWithUser(int id, int userId) {
        return ValidationUtil.checkNotFoundWithId(repository.getWithUser(id, userId), id);
    }
}
