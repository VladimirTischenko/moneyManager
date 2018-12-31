package moneyManager.service.datajpa;

import moneyManager.CostTestData;
import moneyManager.model.User;
import moneyManager.service.AbstractJpaUserServiceTest;
import moneyManager.util.exception.NotFoundException;
import org.junit.Test;
import org.springframework.test.context.ActiveProfiles;

import static moneyManager.Profiles.DATAJPA;
import static moneyManager.UserTestData.*;

@ActiveProfiles(DATAJPA)
public class DataJpaUserServiceTest extends AbstractJpaUserServiceTest {
    @Test
    public void testGetWithMeals() {
        User user = service.getWithCosts(USER_ID);
        MATCHER.assertEquals(USER, user);
        CostTestData.MATCHER.assertCollectionEquals(CostTestData.COSTS, user.getCosts());
    }

    @Test(expected = NotFoundException.class)
    public void testGetWithMealsNotFound() {
        service.getWithCosts(1);
    }
}