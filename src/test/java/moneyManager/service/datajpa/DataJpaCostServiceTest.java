package moneyManager.service.datajpa;

import moneyManager.UserTestData;
import moneyManager.model.Cost;
import moneyManager.service.AbstractCostServiceTest;
import moneyManager.util.exception.NotFoundException;
import org.junit.Test;
import org.springframework.test.context.ActiveProfiles;

import static moneyManager.CostTestData.*;
import static moneyManager.Profiles.DATAJPA;
import static moneyManager.UserTestData.ADMIN_ID;

@ActiveProfiles(DATAJPA)
public class DataJpaCostServiceTest extends AbstractCostServiceTest {
    @Test
    public void testGetWithUser() {
        Cost adminMeal = service.getWithUser(ADMIN_COST_ID, ADMIN_ID);
        MATCHER.assertEquals(ADMIN_COST1, adminMeal);
        UserTestData.MATCHER.assertEquals(UserTestData.ADMIN, adminMeal.getUser());
    }

    @Test(expected = NotFoundException.class)
    public void testGetWithUserNotFound() {
        service.getWithUser(COST1_ID, ADMIN_ID);
    }
}
