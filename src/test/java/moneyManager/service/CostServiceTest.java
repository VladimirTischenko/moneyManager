package moneyManager.service;

import moneyManager.model.Cost;
import moneyManager.util.DbPopulator;
import moneyManager.util.exception.NotFoundException;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.time.LocalDate;
import java.time.Month;
import java.util.Arrays;

import static moneyManager.CostTestData.*;
import static moneyManager.UserTestData.ADMIN_ID;
import static moneyManager.UserTestData.USER_ID;

@ContextConfiguration({
        "classpath:spring/spring-app.xml",
        "classpath:spring/spring-db.xml"
})
@RunWith(SpringJUnit4ClassRunner.class)
public class CostServiceTest {
    @Autowired
    private CostService service;

    @Autowired
    private DbPopulator dbPopulator;

    @Before
    public void setUp() {
        dbPopulator.execute();
    }

    @Test
    public void testDelete() {
        service.delete(COST1_ID, USER_ID);
        MATCHER.assertCollectionEquals(Arrays.asList(COST6, COST5, COST4, COST3, COST2), service.getAll(USER_ID));
    }

    @Test(expected = NotFoundException.class)
    public void testDeleteNotFound() {
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

    @Test(expected = NotFoundException.class)
    public void testGetNotFound() {
        service.get(COST1_ID, ADMIN_ID);
    }

    @Test
    public void testUpdate() {
        Cost updated = getUpdated();
        service.update(updated, USER_ID);
        MATCHER.assertEquals(updated, service.get(COST1_ID, USER_ID));
    }

    @Test(expected = NotFoundException.class)
    public void testUpdateNotFound() {
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
