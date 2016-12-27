package ru.samara.shop;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import ru.samara.shop.model.BaseEntity;
import ru.samara.shop.model.Role;
import ru.samara.shop.model.User;
import ru.samara.shop.util.UserTo;
import ru.samara.shop.util.UserUtil;
//import sun.plugin.liveconnect.SecurityContextHelper;

import java.io.Serializable;
import java.util.Collections;
import java.util.Objects;
import java.util.Set;

import static java.util.Objects.*;

/**
 * Mock implementation
 */
public class LoggedUser implements UserDetails, Serializable {
//    protected int id = BaseEntity.START_SEQ;
//    protected Set<Role> roles = Collections.singleton(Role.ROLE_USER);
//    protected boolean enabled = true;


    private User user;

    public LoggedUser(User user) {
        this.user = user; //UserUtil.asTo(user);
    }

    public User getUser() {
        return user;
    }

    public UserTo getUserTo() {
        User user = get().getUser();
        return new UserTo(user.getId(), user.getName(), user.getEmail());
    }

    /**
     * noauth -> null
     * auth -> LoggedUser
     */
    public static LoggedUser safeGet() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        if (auth == null) {
            return null;
        }

        Object user = auth.getPrincipal();
        return (user instanceof LoggedUser) ? (LoggedUser) user : null;
    }

    //stub
    //private static LoggedUser LOGGED_USER = new LoggedUser();

    public static LoggedUser get() {
        LoggedUser user = safeGet();
        requireNonNull(user, "No authorized user found");
        return user;
    }

    public static int id() {
//        return BaseEntity.START_SEQ;
        return get().user.getId();
    }

    public boolean isEnabled() {
        return user.isEnabled();
    }

    @Override
    public Set<Role> getAuthorities() {
        return user.getRoles();
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
