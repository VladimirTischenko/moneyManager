package moneyManager.service;

import moneyManager.model.Cost;
import moneyManager.util.exception.NotFoundException;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.time.Month;
import java.util.Arrays;

import static moneyManager.CostTestData.*;
import static moneyManager.UserTestData.ADMIN_ID;
import static moneyManager.UserTestData.USER_ID;

public abstract class AbstractCostServiceTest extends AbstractServiceTest{
    @Autowired
    protected CostService service;

    @Test
    public void testDelete() {
        service.delete(COST1_ID, USER_ID);
        MATCHER.assertCollectionEquals(Arrays.asList(COST6, COST5, COST4, COST3, COST2), service.getAll(USER_ID));
    }

    @Test
    public void testDeleteNotFound() {
        thrown.expect(NotFoundException.class);
        service.delete(COST1_ID, 1);
    }

    @Test
    public void testSave() {
        Cost created = getCreated();
        service.save(created, USER_ID);
        MATCHER.assertCollectionEquals(Arrays.asList(created, COST6, COST5, COST4, COST3, COST2, COST1), service.getAll(USER_ID));
    }

    @Test
    public void testGet() {
        Cost actual = service.get(ADMIN_COST_ID, ADMIN_ID);
        MATCHER.assertEquals(ADMIN_COST1, actual);
    }

    @Test
    public void testGetNotFound() {
        thrown.expect(NotFoundException.class);
        service.get(COST1_ID, ADMIN_ID);
    }

    @Test
    public void testUpdate() {
        Cost updated = getUpdated();
        service.update(updated, USER_ID);
        MATCHER.assertEquals(updated, service.get(COST1_ID, USER_ID));
    }

    @Test
    public void testUpdateNotFound() {
        thrown.expect(NotFoundException.class);
        thrown.expectMessage("Not found entity with id=" + COST1_ID);
        service.update(COST1, ADMIN_ID);
    }

    @Test
    public void testGetAll() {
        MATCHER.assertCollectionEquals(COSTS, service.getAll(USER_ID));
    }

    @Test
    public void testGetBetween() {
        MATCHER.assertCollectionEquals(Arrays.asList(COST5, COST4, COST3),
                service.getBetweenDates(LocalDate.of(2018, Month.JULY, 22), LocalDate.of(2018, Month.JULY, 22), USER_ID));
    }
}
