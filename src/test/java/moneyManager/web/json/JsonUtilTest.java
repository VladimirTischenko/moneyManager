package moneyManager.web.json;

import moneyManager.CostTestData;
import moneyManager.model.Cost;
import org.junit.Test;

import java.util.List;

public class JsonUtilTest {
    @Test
    public void testReadWriteValue() {
        String json = JsonUtil.writeValue(CostTestData.ADMIN_COST1);
        System.out.println(json);
        Cost userMeal = JsonUtil.readValue(json, Cost.class);
        CostTestData.MATCHER.assertEquals(CostTestData.ADMIN_COST1, userMeal);
    }

    @Test
    public void testReadWriteValues() {
        String json = JsonUtil.writeValue(CostTestData.COSTS);
        System.out.println(json);
        List<Cost> userMeals = JsonUtil.readValues(json, Cost.class);
        CostTestData.MATCHER.assertCollectionEquals(CostTestData.COSTS, userMeals);
    }
}
