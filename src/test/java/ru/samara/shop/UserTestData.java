package ru.samara.shop;

import ru.samara.shop.matcher.ModelMatcher;
import ru.samara.shop.model.BaseEntity;
import ru.samara.shop.model.Role;
import ru.samara.shop.model.User;
import ru.samara.shop.util.PasswordUtil;

import java.util.EnumSet;
import java.util.Objects;
import java.util.Set;


public class UserTestData {
    private static final LoggerWrapper LOG = LoggerWrapper.get(UserTestData.class);

    public static final TestUser USER = new TestUser(BaseEntity.START_SEQ, "User", "user@yandex.ru", "user", true, Role.ROLE_USER);
    public static final User ADMIN = new TestUser(BaseEntity.START_SEQ + 1, "Admin", "admin@gmail.com", "admin", true, Role.ROLE_ADMIN);

    public static class TestUser extends User {

        public TestUser(User u) {
            this(u.getId(), u.getName(), u.getEmail(), u.getPassword(), u.isEnabled(), u.getRoles());
        }

        public TestUser(String name, String email, String password, Role role, Role... roles) {
            this(null, name, email, password, true, EnumSet.of(role, roles));
        }

        public TestUser(Integer id, String name, String email, String password, boolean enabled, Role role, Role... roles) {
            this(id, name, email, password, enabled, EnumSet.of(role, roles));
        }

        public TestUser(Integer id, String name, String email, String password, boolean enabled, Set<Role> roles) {
            super(id, name, email, password, enabled, roles);
        }

        public TestUser(Integer id, String name, String email, String password, Role roles) {
            super(id, name, email, password, true, roles);
        }

        public User copyAsUser() {
            return new User(this);
        }

        public User asUser() {
            return new User(this);
        }

        @Override
        public String toString() {
            return "User (" +
                    "id=" + id +
                    ", email=" + email +
                    ", name=" + name +
                    ", enabled=" + enabled +
                    ", password=" + password +
//                    ", authorities=" + roles +
                    ')';
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            TestUser that = (TestUser) o;

//            return comparePassword(this.password, that.password) &&
            return Objects.equals(this.id, that.id) &&
                   Objects.equals(this.name, that.name) &&
                   Objects.equals(this.email, that.email) &&
                   Objects.equals(this.enabled, that.enabled) &&
                   Objects.equals(this.roles, that.roles);// &&
//                   Objects.equals(this.caloriesPerDay, that.caloriesPerDay);
        }
    }

    private static boolean comparePassword(String rawPassword, String password) {
        if (PasswordUtil.isEncoded(rawPassword)) {
            LOG.warn("Expected password couldn't be compared with actual");
        } else if (!PasswordUtil.isMatch(rawPassword, password)) {
            LOG.error("Password " + password + " doesn't match encoded " + password);
            return false;
        }
        return true;
    }

    public static final ModelMatcher<User, TestUser> MATCHER = new ModelMatcher<>(
            u -> ((u instanceof TestUser) ? (TestUser) u : new TestUser(u)), User.class);

}
