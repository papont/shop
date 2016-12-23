package ru.samara.shop.web.user;

import org.junit.Test;
import ru.samara.shop.LoggedUser;
import ru.samara.shop.TestUtil;
import ru.samara.shop.UserTestData;
import ru.samara.shop.model.Role;
import ru.samara.shop.model.User;
import ru.samara.shop.web.WebTest;

import static org.junit.Assert.*;

import org.springframework.test.context.ActiveProfiles;

import static ru.samara.shop.Profiles.DATAJPA;
import static ru.samara.shop.Profiles.HSQLDB;
import static ru.samara.shop.Profiles.POSTGRES;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import ru.samara.shop.service.UserService;
import ru.samara.shop.web.json.JsonUtil;

import java.util.Arrays;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

//import static ru.samara.shop.TestUtil.userHttpBasic;
import static ru.samara.shop.TestUtil.userHttpBasic;
import static ru.samara.shop.UserTestData.*;
import static  ru.samara.shop.UserTestData.MATCHER;
import static  ru.samara.shop.UserTestData.USER;
import static  ru.samara.shop.UserTestData.ADMIN;

@ActiveProfiles({HSQLDB, DATAJPA})
public class ProfileRestControllerTest extends WebTest {
    //    public static final String REST_URL = UserRestControllerTest.REST_URL + '/';
    public static final String REST_URL = "/rest/profile/";

    @Autowired
    private UserService service;

    @Test
    public void testGet() throws Exception {
        TestUtil.print(mockMvc.perform(get(REST_URL)
                        .with(userHttpBasic(USER)))
                        .andExpect(status().isOk())
                        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                        .andExpect(MATCHER.contentMatcher(USER))

        );
    }


    @Test
    public void testDelete() throws Exception {
        mockMvc.perform(delete(REST_URL)
                .with(userHttpBasic(USER))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
        MATCHER.assertListEquals(Arrays.asList(ADMIN), service.getAll());
    }

    @Test

    public void testUpdate() throws Exception {
        TestUser updated = new TestUser(LoggedUser.id(), "newName", "newEmail", "newPassword", Role.ROLE_USER);
        mockMvc.perform(put(REST_URL)
                .with(userHttpBasic(USER))
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(updated)))
                .andDo(print())
                .andExpect(status().isOk());

        MATCHER.assertEquals(updated, new User(service.getByEmail("newEmail")));
    }

}