package ru.samara.shop;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import ru.samara.shop.model.BaseEntity;
import ru.samara.shop.model.Role;
import ru.samara.shop.model.User;
//import sun.plugin.liveconnect.SecurityContextHelper;

import java.util.Collections;
import java.util.Objects;
import java.util.Set;

import static java.util.Objects.*;

/**
 * Mock implementation
 */
public class LoggedUser implements UserDetails{
    protected int id = BaseEntity.START_SEQ;
    protected Set<Role> roles = Collections.singleton(Role.ROLE_USER);
    protected boolean enabled = true;
    protected User user;

    public LoggedUser(User user) {
        this.user = user;
//        id = user.getId();
//        roles = user.getRoles();
//        enabled = user.isEnabled();
    }

    public static LoggedUser safeGet() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        if (auth == null) {
            return null;
        }

        Object user = auth.getPrincipal();
        return (user instanceof LoggedUser) ? (LoggedUser) user : null;
    }

    //private static LoggedUser LOGGED_USER = new LoggedUser();

    public static LoggedUser get() {
        LoggedUser user = safeGet();
        requireNonNull(user, "No authorized user found");
        return user;
    }

    public static int id() {
        return get().user.getId();
    }

    public boolean isEnabled() {
        return enabled;
    }

    @Override
    public Set<Role> getAuthorities() {
        return roles;
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getEmail();
    }

    @Override
    public boolean isAccountNonExpired() {
        return user.isEnabled();
    }

    @Override
    public boolean isAccountNonLocked() {
        return user.isEnabled();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return user.isEnabled();
    }
}
