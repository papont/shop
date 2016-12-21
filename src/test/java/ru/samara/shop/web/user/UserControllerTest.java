package ru.samara.shop.web.user;

import org.junit.Test;
import ru.samara.shop.web.WebTest;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.hamcrest.Matchers.*;

import org.springframework.test.context.ActiveProfiles;

import static ru.samara.shop.Profiles.HSQLDB;
import static ru.samara.shop.TestUtil.userHttpBasic;
import static ru.samara.shop.UserTestData.*;
import static ru.samara.shop.model.BaseEntity.START_SEQ;
import static ru.samara.shop.Profiles.DATAJPA;
import static ru.samara.shop.Profiles.POSTGRES;


@ActiveProfiles({HSQLDB, DATAJPA})
public class UserControllerTest extends WebTest {

    @Test
    public void testUserList() throws Exception {
        mockMvc.perform(get("/users"))
              //  .with(userHttpBasic(ADMIN)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("userList"))
                .andExpect(forwardedUrl("/WEB-INF/jsp/userList.jsp"));
//                .andExpect(model().attribute("userList", hasSize(2)))
//                .andExpect(model().attribute("userList", hasItem(
//                        allOf(
//                                hasProperty("id", is(START_SEQ)),
//                                hasProperty("name", is(USER.getName()))
//                        )
//                    )
//                ));
    }
}