package ru.samara.shop;

import ru.samara.shop.model.Role;

import java.util.Set;

/**
 * Created by user on 13.07.2016.
 */
public class LoggedUser {
    protected int id;
    protected boolean enabled;
    protected Set<Role> roles;

    public int getId() {
        return id;
    }
}
