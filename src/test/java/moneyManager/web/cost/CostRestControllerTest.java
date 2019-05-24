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
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;

import static moneyManager.CostTestData.*;
import static moneyManager.TestUtil.userHttpBasic;
import static moneyManager.UserTestData.ADMIN;
import static moneyManager.UserTestData.ADMIN_ID;
import static moneyManager.UserTestData.USER;
import static moneyManager.model.BaseEntity.START_SEQ;
import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class CostRestControllerTest extends AbstractControllerTest {
    private static final String REST_URL = CostRestController.REST_URL + '/';

    @Autowired
    private CostService service;

    @Test
    public void testGet() throws Exception {
        mockMvc.perform(get(REST_URL + ADMIN_COST_ID)
                .with(userHttpBasic(ADMIN)))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(MATCHER.contentMatcher(ADMIN_COST1));
    }

    @Test
    public void testGetUnauth() throws Exception {
        mockMvc.perform(get(REST_URL + COST1_ID))
                .andExpect(status().isUnauthorized());
    }

    @Test
    public void testGetNotFound() throws Exception {
        mockMvc.perform(get(REST_URL + ADMIN_COST_ID)
                .with(userHttpBasic(USER)))
                .andExpect(status().isUnprocessableEntity());
    }

    @Test
    public void testDeleteNotFound() throws Exception {
        mockMvc.perform(delete(REST_URL + ADMIN_COST_ID)
                .with(userHttpBasic(USER)))
                .andDo(print())
                .andExpect(status().isUnprocessableEntity());
    }

    @Test
    @Transactional
    public void testDelete() throws Exception {
        mockMvc.perform(delete(REST_URL + COST1_ID)
                .with(userHttpBasic(USER)))
                .andExpect(status().isOk());
        MATCHER.assertCollectionEquals(Arrays.asList(COST6, COST5, COST4, COST3, COST2), service.getAll(START_SEQ));
    }

    @Test
    @Transactional
    public void testUpdate() throws Exception {
        Cost updated = getUpdated();

        mockMvc.perform(put(REST_URL + COST1_ID)
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(updated))
                .with(userHttpBasic(USER)))
                .andExpect(status().isOk());

        assertEquals(updated, service.get(COST1_ID, START_SEQ));
    }

    @Test
    public void testUpdateInvalid() throws Exception {
        Cost invalid = new Cost(COST1_ID, null, null, 6000);
        mockMvc.perform(put(REST_URL + COST1_ID)
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(invalid))
                .with(userHttpBasic(USER)))
                .andDo(print())
                .andExpect(status().isUnprocessableEntity());
    }

    @Test
    @Transactional
    public void testCreate() throws Exception {
        Cost created = getCreated();
        ResultActions action = mockMvc.perform(post(REST_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(created))
                .with(userHttpBasic(ADMIN)));

        Cost returned = MATCHER.fromJsonAction(action);
        created.setId(returned.getId());

        MATCHER.assertEquals(created, returned);
        MATCHER.assertCollectionEquals(Arrays.asList(created, ADMIN_COST2, ADMIN_COST1), service.getAll(ADMIN_ID));
    }

    @Test
    public void testCreateInvalid() throws Exception {
        Cost invalid = new Cost(null, null, "Dummy", 200);
        mockMvc.perform(post(REST_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(invalid))
                .with(userHttpBasic(ADMIN)))
                .andDo(print())
                .andExpect(status().isUnprocessableEntity());
    }

    @Test
    public void testGetAll() throws Exception {
        mockMvc.perform(get(REST_URL)
                .with(userHttpBasic(USER)))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(MATCHER_WITH_EXCEED.contentListMatcher(CostsUtil.getWithExceeded(COSTS, USER.getSumPerDay())));
    }

    @Test
    public void testGetBetween() throws Exception {
        mockMvc.perform(get(REST_URL + "between?startDateTime=2018-07-21T07:00&endDateTime=2018-07-22T12:00:00")
                .with(userHttpBasic(USER)))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(MATCHER_WITH_EXCEED.contentListMatcher(
                        CostsUtil.createWithExceed(COST3, true),
                        CostsUtil.createWithExceed(COST1, false)));
    }

    @Test
    public void testFilter() throws Exception {
        mockMvc.perform(get(REST_URL + "filter")
                .param("startDate", "2018-07-21").param("startTime", "07:00")
                .param("endDate", "2018-07-22").param("endTime", "12:00")
                .with(userHttpBasic(USER)))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(MATCHER_WITH_EXCEED.contentListMatcher(
                        CostsUtil.createWithExceed(COST3, true),
                        CostsUtil.createWithExceed(COST1, false)));
    }

    @Test
    public void testFilterAll() throws Exception {
        mockMvc.perform(get(REST_URL + "filter?startDate=&endTime=")
                .with(userHttpBasic(USER)))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(MATCHER_WITH_EXCEED.contentListMatcher(
                        CostsUtil.getWithExceeded(Arrays.asList(COST6, COST5, COST4, COST3, COST2, COST1), USER.getSumPerDay())));
    }

    @Test
    public void testUpdateDuplicate() throws Exception {
        Cost invalid = new Cost(COST1_ID, COST2.getDateTime(), "Dummy", 200);
        mockMvc.perform(put(REST_URL + COST1_ID)
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(invalid))
                .with(userHttpBasic(USER)))
                .andDo(print())
                .andExpect(status().isConflict());
    }

    @Test
    public void testCreateDuplicate() throws Exception {
        Cost invalid = new Cost(null, ADMIN_COST1.getDateTime(), "Dummy", 200);
        mockMvc.perform(post(REST_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(invalid))
                .with(userHttpBasic(ADMIN)))
                .andDo(print())
                .andExpect(status().isConflict());
    }
}
