package ru.samara.shop;

import ru.samara.shop.model.BaseEntity;
import ru.samara.shop.model.Role;

import java.util.Collections;
import java.util.Set;

/**
 * Mock implementation
 */
public class LoggedUser {
    protected int id = BaseEntity.START_SEQ;
    protected Set<Role> roles = Collections.singleton(Role.ROLE_USER);
    protected boolean enabled = true;


    private static LoggedUser LOGGED_USER = new LoggedUser();

    public static LoggedUser get() {
        return LOGGED_USER;
    }

    public static int id() {
        return get().id;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public Set<Role> getAuthorities() {
        return roles;
    }
}
