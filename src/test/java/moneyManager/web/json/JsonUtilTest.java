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
        Cost cost = JsonUtil.readValue(json, Cost.class);
        CostTestData.MATCHER.assertEquals(CostTestData.ADMIN_COST1, cost);
    }

    @Test
    public void testReadWriteValues() {
        String json = JsonUtil.writeValue(CostTestData.COSTS);
        System.out.println(json);
        List<Cost> costs = JsonUtil.readValues(json, Cost.class);
        CostTestData.MATCHER.assertCollectionEquals(CostTestData.COSTS, costs);
    }
}
