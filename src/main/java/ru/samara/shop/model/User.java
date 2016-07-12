package ru.samara.shop.model;

import java.util.Date;
import java.util.LinkedList;
import java.util.Set;

/**
 * Created by admin on 13.07.16.
 */
public class User extends NamedEntity {
    private String email;

    //Length(min = 5)
    private String password;

    private boolean enabled = true;

    private Date registered = new Date();

    private Set<Role> autorities;

//    private List<TodoItem> todoItemList = new LinkedList<>();

    public User() {
    }

    public User(String name, String email, String password, boolean enabled, Date registered, Set<Role> autorities, List<TodoItem> todoItemList) {
        super(name);
        this.email = email;
        this.password = password;
        this.enabled = enabled;
        this.registered = registered;
        this.autorities = autorities;
        this.todoItemList = todoItemList;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public Date getRegistered() {
        return registered;
    }

    public void setRegistered(Date registered) {
        this.registered = registered;
    }

    public Set<Role> getAutorities() {
        return autorities;
    }

    public void setAutorities(Set<Role> autorities) {
        this.autorities = autorities;
    }

    @Override
    public String toString() {
        return "User{" +
                "name=" + name +
                "email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", enabled=" + enabled +
                ", registered=" + registered +
                '}';
    }
}
