package ru.samara.shop.service;

import org.junit.Before;
import org.junit.Rule;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import ru.samara.shop.repository.JpaUtil;
import ru.samara.shop.util.DbPopulator;


@ContextConfiguration({
        "classpath:spring/spring-app.xml",
        "classpath:spring/spring-db.xml"
})
@RunWith(SpringJUnit4ClassRunner.class)
abstract public class DbTest {

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Autowired
    protected UserService service;

    @Autowired
    private DbPopulator dbPopulator;


    @Autowired
    private JpaUtil jpaUtil;

    @Before
    public void setUp() throws Exception {
        dbPopulator.execute();
        service.evictCache();
        jpaUtil.clear2ndLevelHibernateCache();
    }
}
