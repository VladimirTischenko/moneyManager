package moneyManager.web;

import org.junit.Test;

import static moneyManager.CostTestData.COST1;
import static moneyManager.CostTestData.COST1_ID;
import static moneyManager.UserTestData.USER;
import static moneyManager.model.BaseEntity.START_SEQ;
import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class RootControllerTest extends AbstractControllerTest {
    @Test
    public void testUsers() throws Exception {
        mockMvc.perform(get("/users"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("users"))
                .andExpect(forwardedUrl("/WEB-INF/jsp/users.jsp"))
                .andExpect(model().attribute("users", hasSize(2)))
                .andExpect(model().attribute("users", hasItem(
                        allOf(
                                hasProperty("id", is(START_SEQ)),
                                hasProperty("name", is(USER.getName()))
                        )
                )));
    }

    @Test
    public void testCosts() throws Exception {
        mockMvc.perform(get("/costs"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("costs"))
                .andExpect(forwardedUrl("/WEB-INF/jsp/costs.jsp"))
                .andExpect(model().attribute("costs", hasSize(6)))
                .andExpect(model().attribute("costs", hasItem(
                        allOf(
                                hasProperty("id", is(COST1_ID)),
                                hasProperty("description", is(COST1.getDescription()))
                        )
                )));
    }
}
