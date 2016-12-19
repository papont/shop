package ru.samara.shop.web.meal;

import org.junit.Test;
import org.springframework.test.context.ActiveProfiles;
import ru.samara.shop.web.WebTest;
import static ru.samara.shop.Profiles.POSTGRES;
import static ru.samara.shop.Profiles.DATAJPA;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.forwardedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@ActiveProfiles({POSTGRES, DATAJPA})
public class UserMealControllerTest extends WebTest {

    @Test
    public void testMealList() throws Exception {
        mockMvc.perform(get("/meals"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("mealList"))
                .andExpect(forwardedUrl("/WEB-INF/jsp/mealList.jsp"));

    }
}