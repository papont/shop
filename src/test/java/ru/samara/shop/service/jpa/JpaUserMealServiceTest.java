package ru.samara.shop.service.jpa;

import org.springframework.test.context.ActiveProfiles;
import ru.samara.shop.service.UserMealServiceTest;

import static ru.samara.shop.Profiles.JPA;
import static ru.samara.shop.Profiles.POSTGRES;

@ActiveProfiles({POSTGRES, JPA})
public class JpaUserMealServiceTest extends UserMealServiceTest {
}
