package moneyManager.web;

import org.junit.Test;

import static moneyManager.TestUtil.userAuth;
import static moneyManager.UserTestData.ADMIN;
import static moneyManager.UserTestData.USER;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class RootControllerTest extends AbstractControllerTest {
    @Test
    public void testUsers() throws Exception {
        mockMvc.perform(get("/users")
                .with(userAuth(ADMIN)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("users"))
                .andExpect(forwardedUrl("/WEB-INF/jsp/users.jsp"));
    }

    @Test
    public void testUnAuth() throws Exception {
        mockMvc.perform(get("/users"))
                .andDo(print())
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("http://localhost/login"));
    }

    @Test
    public void testCosts() throws Exception {
        mockMvc.perform(get("/costs")
                .with(userAuth(USER)))
                .andDo(print())
                .andExpect(view().name("costs"))
                .andExpect(forwardedUrl("/WEB-INF/jsp/costs.jsp"));
    }
}
