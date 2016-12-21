package ru.samara.shop.web.user;

import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import ru.samara.shop.web.WebTest;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static ru.samara.shop.Profiles.HSQLDB;
import static ru.samara.shop.Profiles.POSTGRES;
import static ru.samara.shop.Profiles.DATAJPA;

@ActiveProfiles({HSQLDB, DATAJPA})
public class ResourceControllerTest extends WebTest {

    @Test
    public void testUserList() throws Exception{
        mockMvc.perform(get("/resources/css/style.css"))
                .andDo(print())
                .andExpect(content().contentType(MediaType.valueOf("text/css")))
                .andExpect(status().isOk());
    }
}
