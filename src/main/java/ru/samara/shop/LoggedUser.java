package ru.samara.shop;

import ru.samara.shop.model.Role;

import java.util.Collections;
import java.util.Set;

/**
 * @author papont
 * @date 13.11.16.
 *
 * Mock implementation
 */
public class LoggedUser {
    protected int id = 0;
    protected boolean enabled = true;
    protected Set<Role> roles = Collections.singleton(Role.ROLE_USER);


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
