package ru.samara.shop.model;

import org.hibernate.validator.constraints.NotEmpty;
import ru.samara.shop.util.TimeUtil;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * @author papont
 */

@NamedQueries({
        @NamedQuery(name = UserMeal.GET, query = "SELECT m FROM UserMeal m WHERE m.id=:id AND m.user.id=:userId"),
        @NamedQuery(name = UserMeal.ALL_SORTED, query = "SELECT m FROM UserMeal m WHERE m.user.id=:userId ORDER BY m.dateTime"),
        @NamedQuery(name = UserMeal.DELETE_ALL, query = "DELETE FROM UserMeal i WHERE i.user.id=:userId"),
        @NamedQuery(name = UserMeal.DELETE, query = "DELETE FROM UserMeal i WHERE i.id=:id AND i.user.id=:userId"),
        @NamedQuery(name = UserMeal.GET_BETWEEN,
                query = "SELECT m FROM UserMeal m WHERE m.user.id=:userId " +
                        "AND m.dateTime>=:after and m.dateTime<:before ORDER BY m.dateTime DESC"),
//        @NamedQuery(name = UserMeal.UPDATE, query = "UPDATE UserMeal m SET m.dateTime=:datetime, m.calories=:calories, " +
//                                            "m.description=:desc where m.id=:id and m.user.id=:userId")
})

@Entity
@Table(name = "meals")
public class UserMeal extends BaseEntity {

    public static final String GET = "UserMeal.get";
    public static final String ALL_SORTED = "UserMeal.getAll";
    public static final String DELETE = "UserMeal.delete";
    public static final String DELETE_ALL = "UserMeal.deleteAll";
    public static final String GET_BETWEEN = "UserMeal.getBetween";
//    public static final String UPDATE = "UserMeal.update";

    @Column(name = "datetime", nullable = false)
    protected LocalDateTime dateTime;

    @Column(name = "description", nullable = false)
    @NotEmpty
    protected String description;

    @Column(name = "calories")
    protected int calories;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    public UserMeal() {
    }

    public UserMeal(UserMeal meal) {
        this(meal.id, meal.dateTime, meal.description, meal.calories);
    }

    public UserMeal(Integer id, LocalDateTime dateTime, String description, int calories) {
        super(id);
        this.dateTime = dateTime;
        this.description = description;
        this.calories = calories;
    }

    public UserMeal(LocalDateTime dateTime, String description, int calories) {
        this.dateTime = dateTime;
        this.description = description;
        this.calories = calories;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public String getDescription() {
        return description;
    }

    public int getCalories() {
        return calories;
    }

    public User getUser() {
        return user;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setCalories(int calories) {
        this.calories = calories;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "UserMeal{" +
                "id=" + id +
                ", dateTime=" + TimeUtil.toString(dateTime) +
                ", description='" + description + '\'' +
                ", calories=" + calories +
//                ", user=" + user +
                '}';
    }
}
