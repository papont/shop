package ru.samara.shop.web.user;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.ResultActions;
import ru.samara.shop.TestUtil;
import ru.samara.shop.UserTestData.TestUser;
import ru.samara.shop.model.Role;
import ru.samara.shop.model.User;
import ru.samara.shop.service.UserService;
import ru.samara.shop.util.exception.NotFoundException;
import ru.samara.shop.web.WebTest;
import ru.samara.shop.web.json.JsonUtil;

import java.util.Arrays;
import java.util.Collections;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static ru.samara.shop.Profiles.HSQLDB;
import static ru.samara.shop.Profiles.POSTGRES;
import static ru.samara.shop.Profiles.DATAJPA;
import static ru.samara.shop.TestUtil.userHttpBasic;
import static  ru.samara.shop.UserTestData.MATCHER;
import static  ru.samara.shop.UserTestData.ADMIN;
import static  ru.samara.shop.UserTestData.USER;
import static ru.samara.shop.model.BaseEntity.START_SEQ;

@ActiveProfiles({HSQLDB, DATAJPA})
public class AdminRestControllerTest extends WebTest {

    public static final String REST_URL = AdminRestController.REST_URL + '/';

    @Autowired
    private UserService service;

    @Test
    public void testGet() throws Exception {
        mockMvc.perform(get(REST_URL + (START_SEQ + 1))
                .with(userHttpBasic(ADMIN)))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MATCHER.contentMatcher(ADMIN));
    }


    @Test //(expected =NotFoundException.class )
    public void testGetNotFound() throws Exception {
        mockMvc.perform(get(REST_URL + (1))
                .with(userHttpBasic(ADMIN)))
                .andExpect(status().isNotFound())
                .andDo(print())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    public void testGetUnauth() throws Exception {
        mockMvc.perform(get(REST_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .with(userHttpBasic(USER)))
                .andExpect(status().isForbidden());
    }

    @Test
    public void testGetByEmail() throws Exception {
        mockMvc.perform(get(REST_URL + "by?email=" + USER.getEmail())
                .with(userHttpBasic(ADMIN)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MATCHER.contentMatcher(USER));
    }

    @Test
    public void testDelete() throws Exception {
        mockMvc.perform(delete(REST_URL + START_SEQ).contentType(MediaType.APPLICATION_JSON)
                .with(userHttpBasic(ADMIN)))
                .andDo(print())
                .andExpect(status().isOk());
        MATCHER.assertListEquals(Collections.singletonList(ADMIN), service.getAll());
    }

    @Test
    public void testUpdate() throws Exception {
        User updated = USER.copyAsUser();
        updated.setName("UpdatedName");
        updated.setRoles(Role.ROLE_ADMIN);
        TestUtil.print(mockMvc.perform(put(REST_URL + START_SEQ)
                .contentType(MediaType.APPLICATION_JSON)
                .with(userHttpBasic(ADMIN))
                .content(JsonUtil.writeValue(updated))))
                .andDo(print())
                .andExpect(status().isOk());

        MATCHER.assertEquals(updated, service.get(START_SEQ));
    }

    @Test
    public void testCreate() throws Exception {
        TestUser expected = new TestUser("New", "new@gmail.com", "newPass", Role.ROLE_USER, Role.ROLE_ADMIN);
        ResultActions action = mockMvc.perform(post(REST_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .with(userHttpBasic(ADMIN))
                .content(JsonUtil.writeValue(expected.copyAsUser())))
                .andExpect(status().isCreated());

        User returned = MATCHER.fromJsonAction(action);
        expected.setId(returned.getId());

        MATCHER.assertEquals(expected, returned);
        MATCHER.assertListEquals(Arrays.asList(ADMIN, expected, USER), service.getAll());
    }

    @Test
    public void testGetAll() throws Exception {
        TestUtil.print(mockMvc.perform(get(REST_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .with(userHttpBasic(ADMIN)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MATCHER.contentListMatcher(ADMIN, USER)));
    }


}