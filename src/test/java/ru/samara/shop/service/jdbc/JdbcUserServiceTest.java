package ru.samara.shop.service.jdbc;

import org.springframework.test.context.ActiveProfiles;
import ru.samara.shop.service.UserServiceTest;

import static ru.samara.shop.Profiles.JDBC;
import static ru.samara.shop.Profiles.POSTGRES;

@ActiveProfiles({POSTGRES, JDBC})
public class JdbcUserServiceTest extends UserServiceTest {
}