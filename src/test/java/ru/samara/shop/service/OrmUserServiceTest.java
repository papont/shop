package ru.samara.shop.service;

import org.junit.Before;
import org.springframework.beans.factory.annotation.Autowired;
import ru.samara.shop.repository.JpaUtil;

abstract public class OrmUserServiceTest extends UserServiceTest {
    @Autowired
    private JpaUtil jpaUtil;

    @Before
    public void setUp() throws Exception {
        super.setUp();
        jpaUtil.clear2ndLevelHibernateCache();
    }
}
