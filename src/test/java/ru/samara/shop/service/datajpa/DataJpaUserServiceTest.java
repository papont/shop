package ru.samara.shop.service.datajpa;

import org.springframework.test.context.ActiveProfiles;
import ru.samara.shop.service.OrmUserServiceTest;


import static ru.samara.shop.Profiles.DATAJPA;
import static ru.samara.shop.Profiles.POSTGRES;

@ActiveProfiles({POSTGRES, DATAJPA})
public class DataJpaUserServiceTest extends OrmUserServiceTest {
}
