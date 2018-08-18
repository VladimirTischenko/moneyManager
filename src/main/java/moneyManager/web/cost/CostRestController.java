package moneyManager.web.cost;

import moneyManager.AuthorizedUser;
import moneyManager.model.Cost;
import moneyManager.service.CostService;
import moneyManager.to.CostWithExceed;
import moneyManager.util.CostsUtil;
import moneyManager.util.DateTimeUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import static moneyManager.util.ValidationUtil.checkIdConsistent;
import static moneyManager.util.ValidationUtil.checkNew;

/**
 * Created by Vladimir on 09.08.2018.
 */
@Controller
public class CostRestController {
    private static final Logger LOG = LoggerFactory.getLogger(CostRestController.class);

    @Autowired
    private CostService service;

    public Cost get(int id) {
        int userId = AuthorizedUser.id();
        LOG.info("get cost {} for User {}", id, userId);
        return service.get(id, userId);
    }

    public void delete(int id) {
        int userId = AuthorizedUser.id();
        LOG.info("delete cost {} for User {}", id, userId);
        service.delete(id, userId);
    }

    public List<CostWithExceed> getAll() {
        int userId = AuthorizedUser.id();
        LOG.info("getAll for User {}", userId);
        return CostsUtil.getWithExceeded(service.getAll(userId), AuthorizedUser.getSumPerDay());
    }

    public Cost create(Cost cost) {
        checkNew(cost);
        int userId = AuthorizedUser.id();
        LOG.info("create {} for User {}", cost, userId);
        return service.save(cost, userId);
    }

    public void update(Cost cost, int id) {
        checkIdConsistent(cost, id);
        int userId = AuthorizedUser.id();
        LOG.info("update {} for User {}", cost, userId);
        service.update(cost, userId);
    }

    public List<CostWithExceed> getBetween(LocalDate startDate, LocalTime startTime, LocalDate endDate, LocalTime endTime) {
        int userId = AuthorizedUser.id();
        LOG.info("getBetween dates {} - {} for time {} - {} for User {}", startDate, endDate, startTime, endTime, userId);

        return CostsUtil.getFilteredWithExceeded(
                service.getBetweenDates(
                        startDate != null ? startDate : DateTimeUtil.MIN_DATE,
                        endDate != null ? endDate : DateTimeUtil.MAX_DATE, userId),
                startTime != null ? startTime : LocalTime.MIN,
                endTime != null ? endTime : LocalTime.MAX,
                AuthorizedUser.getSumPerDay()
        );
    }
}
