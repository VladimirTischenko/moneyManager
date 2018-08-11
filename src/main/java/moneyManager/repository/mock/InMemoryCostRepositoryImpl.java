package moneyManager.repository.mock;

import moneyManager.model.Cost;
import moneyManager.repository.CostRepository;
import moneyManager.util.CostsUtil;

import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by Vladimir on 03.08.2018.
 */
public class InMemoryCostRepositoryImpl implements CostRepository {
    private Map<Integer, Cost> repository = new ConcurrentHashMap<>();
    private AtomicInteger counter = new AtomicInteger(0);

    {
        CostsUtil.COSTS.forEach(this::save);
    }

    @Override
    public Cost save(Cost cost) {
        if (cost.isNew()) {
            cost.setId(counter.incrementAndGet());
        }
        repository.put(cost.getId(), cost);
        return cost;
    }

    @Override
    public void delete(int id) {
        repository.remove(id);
    }

    @Override
    public Cost get(int id) {
        return repository.get(id);
    }

    @Override
    public Collection<Cost> getAll() {
        return repository.values();
    }
}
