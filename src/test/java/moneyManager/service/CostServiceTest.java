package moneyManager.service;

import moneyManager.Profiles;
import moneyManager.model.Cost;
import moneyManager.util.exception.NotFoundException;
import org.junit.AfterClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.rules.Stopwatch;
import org.junit.runner.Description;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.time.LocalDate;
import java.time.Month;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;

import static moneyManager.CostTestData.*;
import static moneyManager.UserTestData.ADMIN_ID;
import static moneyManager.UserTestData.USER_ID;

@ContextConfiguration({
        "classpath:spring/spring-app.xml",
        "classpath:spring/spring-db.xml"
})
@RunWith(SpringJUnit4ClassRunner.class)
@Sql(scripts = "classpath:db/populateDB.sql", config = @SqlConfig(encoding = "UTF-8"))
@ActiveProfiles(Profiles.ACTIVE_DB)
public class CostServiceTest {
    private static final Logger LOG = LoggerFactory.getLogger(CostServiceTest.class);
    private static StringBuilder results = new StringBuilder();

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Rule
    // http://stackoverflow.com/questions/14892125/what-is-the-best-practice-to-determine-the-execution-time-of-the-bussiness-relev
    public Stopwatch stopwatch = new Stopwatch() {
        @Override
        protected void finished(long nanos, Description description) {
            String result = String.format("%-25s %7d", description.getMethodName(), TimeUnit.NANOSECONDS.toMillis(nanos));
            results.append(result).append('\n');
            LOG.info(result + " ms\n");
        }
    };

    @AfterClass
    public static void printResult() {
        LOG.info("\n---------------------------------" +
                "\nTest                 Duration, ms" +
                "\n---------------------------------\n" +
                results +
                "---------------------------------\n");
    }

    @Autowired
    private CostService service;

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
