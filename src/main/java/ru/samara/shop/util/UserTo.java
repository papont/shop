package ru.samara.shop.util;

import ru.samara.shop.model.Role;
import ru.samara.shop.model.User;

import java.io.Serializable;


public class UserTo implements AbstractUser, Serializable {
    protected int id;
    protected String name;
    protected String email;
    protected String password;

    public UserTo() {
    }

    public UserTo(int id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = (id == null ? 0 : id);
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public String toString() {
        return "UserTo{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

    @Override
    public void updateUser(User user) {
        user.setName(name);
        user.setEmail(email);
        user.setPassword(password);
    }

    @Override
    public User asNewUser() {
        return new User(null, name, email, password, true, Role.ROLE_USER);
    }
}