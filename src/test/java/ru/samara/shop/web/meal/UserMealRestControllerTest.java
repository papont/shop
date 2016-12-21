package ru.samara.shop.web.meal;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.ResultActions;

import ru.samara.shop.model.UserMeal;
import ru.samara.shop.service.UserMealService;
import ru.samara.shop.util.exception.NotFoundException;
import ru.samara.shop.web.WebTest;
import ru.samara.shop.web.json.JsonUtil;

import javax.transaction.Transactional;
//import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.junit.Assert.*;
import static ru.samara.shop.Profiles.*;
import static ru.samara.shop.MealTestData.*;
//import static ru.samara.shop.TestUtil.userHttpBasic;
//import static ru.samara.shop.UserTestData.USER;
import static ru.samara.shop.model.BaseEntity.START_SEQ;

@ActiveProfiles({POSTGRES, DATAJPA})
@Transactional
public class UserMealRestControllerTest extends WebTest {

    public static final String REST_URL = UserMealRestController.REST_URL + "/";

    @Autowired
    private UserMealService service;

    @Test
    public void testGet() throws Exception {
        mockMvc.perform(get(REST_URL + MEAL1_ID))//.with(userHttpBasic(USER)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MATCHER.contentMatcher(MEAL1));
    }
//
//    @Test
//    public void testGetNotFound() throws Exception {
//        mockMvc.perform(get(REST_URL + (ADMIN_MEAL.getId())))
//                //.with(userHttpBasic(USER)))
//                .andExpect(status().isNotFound());
//    }
//
//    @Test
//    public void testDeleteNotFound() throws Exception {
//        mockMvc.perform(delete(REST_URL + (ADMIN_MEAL.getId()))
//                .contentType(MediaType.APPLICATION_JSON))
////                .with(userHttpBasic(USER)))
//                .andDo(print())
//                .andExpect(status().isNotFound());
//    }

    @Test
    public void testDelete() throws Exception {
        mockMvc.perform(delete(REST_URL + MEAL1_ID).contentType(MediaType.APPLICATION_JSON))
//                .with(userHttpBasic(USER)))
                .andExpect(status().isOk());
        MATCHER.assertListEquals(Arrays.asList(MEAL4, MEAL3, MEAL2), service.getAll(START_SEQ));
    }

    @Test
    public void testDeleteAll() throws Exception {
        mockMvc.perform(delete(REST_URL).contentType(MediaType.APPLICATION_JSON))
//                .with(userHttpBasic(USER)))
                .andExpect(status().isOk());
        assertEquals(0, service.getAll(START_SEQ).size());
    }

    @Test
    public void testGetAll() throws Exception {
        mockMvc.perform(get(REST_URL).contentType(MediaType.APPLICATION_JSON))
//                .with(userHttpBasic(USER)))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MATCHER.contentListMatcher(MEAL4, MEAL3, MEAL2, MEAL1));
    }

    @Test
    public void testUpdate() throws Exception {
        UserMeal updated = getUpdated();

        mockMvc.perform(put(REST_URL + MEAL1_ID).contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(updated)))
//                .with(userHttpBasic(USER)))
                .andExpect(status().isOk());

        assertEquals(updated, service.get(MEAL1_ID, START_SEQ));
    }

    @Test
    public void testCreate() throws Exception {
        UserMeal created = getCreated();
        ResultActions action = mockMvc.perform(post(REST_URL)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(JsonUtil.writeValue(created))
//                .with(userHttpBasic(USER))
        );

        UserMeal returned = MATCHER.fromJsonAction(action);
        created.setId(returned.getId());

        MATCHER.assertEquals(created, returned);
        MATCHER.assertListEquals(Arrays.asList(created, MEAL4, MEAL3, MEAL2, MEAL1), service.getAll(START_SEQ));
    }
}