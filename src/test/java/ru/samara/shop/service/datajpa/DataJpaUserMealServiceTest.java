package ru.samara.shop.service.datajpa;

import org.springframework.test.context.ActiveProfiles;
import ru.samara.shop.service.UserMealServiceTest;

import static ru.samara.shop.Profiles.DATAJPA;
import static ru.samara.shop.Profiles.POSTGRES;

@ActiveProfiles({POSTGRES, DATAJPA})
public class DataJpaUserMealServiceTest extends UserMealServiceTest{
}
