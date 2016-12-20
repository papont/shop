package ru.samara.shop.web;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.FilterChainProxy;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

//import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;
import ru.samara.shop.service.UserService;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;

import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;

@ContextConfiguration({
        "classpath:spring/spring-app.xml",
        "classpath:spring/spring-mvc.xml",
        "classpath:spring/spring-db.xml"
})
@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
abstract public class WebTest {
    protected MockMvc mockMvc;

    @Autowired
    protected UserService service;

    @Autowired
    private WebApplicationContext webApplicationContext;

//    @Autowired
//    private FilterChainProxy restSecurityFilterChain;

    @PostConstruct
    void postConstruct() {
//        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).addFilters(restSecurityFilterChain).build();
//        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).apply(springSecurity()).build();
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Before
    public void setUp() {
       service.evictCache();
    }


}