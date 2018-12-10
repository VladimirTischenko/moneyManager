package moneyManager.repository.mock;

import moneyManager.model.Cost;
import moneyManager.repository.CostRepository;
import moneyManager.util.CostsUtil;
import moneyManager.util.DateTimeUtil;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.Collection;
import java.util.Comparator;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static moneyManager.UserTestData.ADMIN_ID;
import static moneyManager.UserTestData.USER_ID;

/**
 * Created by Vladimir on 03.08.2018.
 */
@Repository
public class InMemoryCostRepositoryImpl implements CostRepository {
    private static final Comparator<Cost> COST_COMPARATOR = Comparator.comparing(Cost::getDateTime).reversed();

    // Map  userId -> (costId-> cost)
    private Map<Integer, Map<Integer, Cost>> repository = new ConcurrentHashMap<>();
    private AtomicInteger counter = new AtomicInteger(0);

    {
        CostsUtil.COSTS.forEach(c -> save(c, USER_ID));

        save(new Cost(LocalDateTime.of(2018, Month.AUGUST, 11, 11, 0), "fat", 150), ADMIN_ID);
        save(new Cost(LocalDateTime.of(2018, Month.AUGUST, 11, 11, 30), "tea tree oil", 100), ADMIN_ID);
    }

    @Override
    public Cost save(Cost cost, int userId) {
        Objects.requireNonNull(cost);

        Map<Integer, Cost> costs = repository.computeIfAbsent(userId, ConcurrentHashMap::new);
        if (cost.isNew()) {
            cost.setId(counter.incrementAndGet());
        } else if (get(cost.getId(), userId) == null) {
            return null;
        }
        costs.put(cost.getId(), cost);
        return cost;
    }

    @Override
    public boolean delete(int id, int userId) {
        Map<Integer, Cost> costs = repository.get(userId);
        return costs != null && costs.remove(id) != null;
    }

    @Override
    public Cost get(int id, int userId) {
        Map<Integer, Cost> costs = repository.get(userId);
        return costs == null ? null : costs.get(id);
    }

    @Override
    public Collection<Cost> getAll(int userId) {
        return getAllAsStream(userId).collect(Collectors.toList());
    }

    @Override
    public Collection<Cost> getBetween(LocalDateTime startDateTime, LocalDateTime endDateTime, int userId) {
        Objects.requireNonNull(startDateTime);
        Objects.requireNonNull(endDateTime);
        return getAllAsStream(userId)
                .filter(c -> DateTimeUtil.isBetween(c.getDateTime(), startDateTime, endDateTime))
                .collect(Collectors.toList());
    }

    private Stream<Cost> getAllAsStream(int userId) {
        Map<Integer, Cost> costs = repository.get(userId);
        return costs == null ? Stream.empty() : costs.values().stream().sorted(COST_COMPARATOR);
    }
}
