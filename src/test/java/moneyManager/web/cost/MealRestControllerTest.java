package moneyManager.web.cost;

import moneyManager.model.Cost;
import moneyManager.service.CostService;
import moneyManager.util.CostsUtil;
import moneyManager.web.AbstractControllerTest;
import moneyManager.web.json.JsonUtil;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;

import java.util.Arrays;

import static moneyManager.CostTestData.*;
import static moneyManager.UserTestData.USER;
import static moneyManager.model.BaseEntity.START_SEQ;
import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class MealRestControllerTest extends AbstractControllerTest {
    private static final String REST_URL = CostRestController.REST_URL + '/';

    @Autowired
    private CostService service;

    @Test
    public void testGet() throws Exception {
        mockMvc.perform(get(REST_URL + COST1_ID))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(MATCHER.contentMatcher(COST1));
    }

    @Test
    public void testDelete() throws Exception {
        mockMvc.perform(delete(REST_URL + COST1_ID).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
        MATCHER.assertCollectionEquals(Arrays.asList(COST6, COST5, COST4, COST3, COST2), service.getAll(START_SEQ));
    }

    @Test
    public void testUpdate() throws Exception {
        Cost updated = getUpdated();

        mockMvc.perform(put(REST_URL + COST1_ID).contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(updated)))
                .andExpect(status().isOk());

        assertEquals(updated, service.get(COST1_ID, START_SEQ));
    }

    @Test
    public void testCreate() throws Exception {
        Cost created = getCreated();
        ResultActions action = mockMvc.perform(post(REST_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(created)));

        Cost returned = MATCHER.fromJsonAction(action);
        created.setId(returned.getId());

        MATCHER.assertEquals(created, returned);
        MATCHER.assertCollectionEquals(Arrays.asList(created, COST6, COST5, COST4, COST3, COST2, COST1), service.getAll(START_SEQ));
    }

    @Test
    public void testGetAll() throws Exception {
        mockMvc.perform(get(REST_URL).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(MATCHER_WITH_EXCEED.contentListMatcher(CostsUtil.getWithExceeded(COSTS, USER.getSumPerDay())));
    }

    @Test
    public void testGetBetween() throws Exception {
        mockMvc.perform(get(REST_URL + "between?startDateTime=2015-05-30T07:00&endDateTime=2015-05-31T11:00:00"))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(MATCHER_WITH_EXCEED.contentListMatcher(
                        CostsUtil.createWithExceed(COST4, true),
                        CostsUtil.createWithExceed(COST1, false)));
    }
}
